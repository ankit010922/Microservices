package com.dish.api.gateway.config;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Configuration
@Slf4j
public class AuthenticationFilter implements GlobalFilter{

	@Value("${jwt.signing.key}")
    private String signingKey;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            try {
                byte[] publicKeyBytes = Base64.getDecoder().decode(signingKey);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
                RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(rsaPublicKey)
                        .build()
                        .parseClaimsJws(jwtToken)
                        .getBody();

                List<String> userRoles = claims.get("roles", List.class); // Extract roles from JWT

                String path = exchange.getRequest().getPath().toString();
                // Now check if the roles match the required roles for the path
                if (!isAuthorized(path, userRoles)) {
                	log.info("#### User is Not Valid for this API ####");
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }else
                {
                	log.info("#### Valid User for this API ####");
                }

                // If authorized, forward the request with added headers
                exchange.getRequest().mutate()
                        .header("x-user-id", claims.getSubject())
                        .header("x-first-name", claims.get("given_name", String.class))
                        .header("x-last-name", claims.get("family_name", String.class))
                        .header("x-user-email", claims.get("email", String.class))
                        .header("x-user-role", String.join(",", userRoles))
                        .build();
            } catch (Exception e) {
                log.info("Failed to decode token", e);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } else {
            log.info("Token is null or not starting with Bearer");
        }
        return chain.filter(exchange);
    }

    private boolean isAuthorized(String path, List<String> userRoles) {
    	if(path.contains("/admin") || path.contains("/au"))
		{
		    return userRoles.contains("ADMIN") || userRoles.contains("SUPER_ADMIN");
		}else if(path.contains("/su"))
		{
			return userRoles.contains("SUPER_ADMIN") || userRoles.contains("ADMIN");
		}
        return true;
    }
    
    @Bean
    public AuthenticationFilter customHeadersFilter() {
        return new AuthenticationFilter();
    }
    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        // Decode RSA public key from Base64 string
        byte[] publicKeyBytes = Base64.getDecoder().decode(signingKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            
            // Create and return ReactiveJwtDecoder bean
            return NimbusReactiveJwtDecoder.withPublicKey(rsaPublicKey).build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ReactiveJwtDecoder", e);
        }
    }
}
