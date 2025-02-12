package com.msc.parking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final Logger logger = LoggerFactory.getLogger(OpenAPIConfig.class);

    @Bean
    public OpenAPI customOpenAPI() {
        logger.info("Loading OpenAPI configuration");
        return new OpenAPI()
                .info(new Info()
                        .title("Parking API")
                        .version("1.0")
                        .description("API documentation for the Parking application"));
    }
}