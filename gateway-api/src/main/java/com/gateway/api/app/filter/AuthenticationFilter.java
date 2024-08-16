package com.gateway.api.app.filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.gateway.api.app.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Check if the Authorization header is present
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return this.onError(exchange, "Missing authorization header", 401);
            }

            // Extract and validate the token
            String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                return this.onError(exchange, "Invalid authorization token", 401);
            }

            // If token is valid, proceed with the request
            return chain.filter(exchange);
        };
    }

    // Method to handle errors and send appropriate responses
    private Mono<Void> onError(ServerWebExchange exchange, String err, int status) {
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.valueOf(status));
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}
