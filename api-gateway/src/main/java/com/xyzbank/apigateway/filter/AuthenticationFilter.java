package com.xyzbank.apigateway.filter;

import com.xyzbank.apigateway.exception.GatewayException;
import com.xyzbank.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new GatewayException("Authorization error", HttpStatus.UNAUTHORIZED.value(),
                            "Authorization header is missing in request");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                    String userName = jwtUtil.decodeToken(authHeader);
                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .header("userName", userName)
                            .build();
                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                } catch (Exception e) {
                    throw new GatewayException("Authorization error", HttpStatus.UNAUTHORIZED.value(),
                            "Invalid token: required access is missing");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}