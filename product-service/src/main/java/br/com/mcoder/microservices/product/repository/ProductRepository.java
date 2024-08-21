package br.com.mcoder.microservices.product.repository;

import br.com.mcoder.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
