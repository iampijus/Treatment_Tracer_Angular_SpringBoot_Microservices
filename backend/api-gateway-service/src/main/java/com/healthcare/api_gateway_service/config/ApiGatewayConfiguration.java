package com.healthcare.api_gateway_service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authentication-service", p -> p.path("/healthcare/auth/**")
                        .uri("http://localhost:8085"))
                .route("treatment-service", p -> p.path("/healthcare/v1/**")
                        .uri("http://localhost:8082"))
                .route("bookmark-service", p -> p.path("/healthcare/v3/**")
                        .uri("http://localhost:8083"))
                .route("userprofile-service", p -> p.path("/healthcare/v2/**")
                        .uri("http://localhost:8084"))
                .build();
    }

}
