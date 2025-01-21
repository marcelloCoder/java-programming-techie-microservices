package br.com.mcoder.microservices.order.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    //MENSAGEM SE OS SERVIÇOS ESTIVEREM DESLIGADOS
    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        String message = String.format("AVISO: Não foi possível obter inventário para skucode %s, motivo da falha: %s", code, throwable.getMessage());
        log.info(message);
        return false;
    }

}