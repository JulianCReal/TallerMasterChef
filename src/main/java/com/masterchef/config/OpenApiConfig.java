package com.masterchef.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI masterChefOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Master Chef Celebrity API")
                        .description("API de gestión de recetas para el programa Máster Chef Celebrity")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("DOSW Company")
                                .email("support@dosw.com")));
    }
}