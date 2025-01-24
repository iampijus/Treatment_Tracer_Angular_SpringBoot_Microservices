package com.healthcare.api_gateway_service.config;


import com.healthcare.api_gateway_service.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtFilter implements WebFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> EXCLUDED_PATHS = List.of("/healthcare/auth/login", "/healthcare/v2/register", "/healthcare/v1/treatments");


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        // Excludes these paths
        String path = exchange.getRequest().getURI().getPath();
        if (EXCLUDED_PATHS.contains(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        String username = null;
        String token = null;

        // Extract the jwt token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }


        // Validate token
        if (username != null && jwtUtil.validateToken(token, username)) {
            // If token is valid, set the authentication in the context
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));  // Set authentication context
        }

        // If validation fails, respond with Unauthorized
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();

    }
}
