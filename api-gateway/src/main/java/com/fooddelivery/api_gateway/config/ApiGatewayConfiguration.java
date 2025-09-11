package com.fooddelivery.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rule for the User Service
                .route("user-service", p -> p.path("/api/users/**")
                        .uri("lb://USER-SERVICE"))
                
                // Rule for the Restaurant Service
                .route("restaurant-service", p -> p.path("/api/restaurants/**")
                        .uri("lb://RESTAURANT-SERVICE"))

                // Rule for the Order Service
                .route("order-service", p -> p.path("/api/orders/**")
                        .uri("lb://ORDER-SERVICE"))

                // Rule for the Delivery Service
                .route("delivery-service", p -> p.path("/api/delivery/**")
                        .uri("lb://DELIVERY-SERVICE"))
                
                .build();
    }
}