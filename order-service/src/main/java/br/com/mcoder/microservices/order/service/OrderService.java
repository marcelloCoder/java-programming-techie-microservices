package br.com.mcoder.microservices.order.service;

import br.com.mcoder.microservices.order.client.InventoryClient;
import br.com.mcoder.microservices.order.dto.OrderRequest;
import br.com.mcoder.microservices.order.event.OrderPlacedEvent;
import br.com.mcoder.microservices.order.model.Order;
import br.com.mcoder.microservices.order.repository.OrderRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        log.info("Verificando estoque para o código SKU: {}", orderRequest.skuCode());
        if (!isProductInStock) {
            log.error("Estoque indisponível para o código SKU: {}", orderRequest.skuCode());
            throw new RuntimeException("Produto com código SKU " + orderRequest.skuCode() + " não está no estoque");
        }
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orderRepository.save(order);

            //Mandar mensagem para Kafka topico
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails().email());
            log.info("Start - Sending OrderPlaced Event {} to kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlaced Event {} to kafka topic order-placed", orderPlacedEvent);


        }else {
            throw new RuntimeException("Produto com codigo sku " + orderRequest.skuCode() + " não esta no estoque");
        }
    }
}
