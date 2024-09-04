package br.com.mcoder.microservices.order.service;

import br.com.mcoder.microservices.order.client.InventoryClient;
import br.com.mcoder.microservices.order.dto.OrderRequest;
import br.com.mcoder.microservices.order.model.Order;
import br.com.mcoder.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orderRepository.save(order);
        }else {
            throw new RuntimeException("Produto com codigo sku " + orderRequest.skuCode() + " não esta no estoque");
        }
    }
}
