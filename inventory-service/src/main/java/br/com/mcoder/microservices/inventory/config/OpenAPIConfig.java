package br.com.mcoder.microservices.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI inventoryServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("INVENTORY SERVICE API")
                        .description("ESSE E UM REST API PARA inventory service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Voce pode consultar a documentação de Inventory Service Wiki")
                        .url("http://inventory-service-dummy-url.com/docs"));
    }
}
