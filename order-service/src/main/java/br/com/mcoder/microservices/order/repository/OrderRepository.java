package br.com.mcoder.microservices.order.repository;

import br.com.mcoder.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
