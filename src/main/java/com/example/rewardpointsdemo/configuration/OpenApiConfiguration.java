package com.example.rewardpointsdemo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    //API documentation configuration
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rewards Points API")
                        .description("This API demonstrates the basic rewards program")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .email("demo@demo.com")
                                .name("Demo Name")));
    }
}
