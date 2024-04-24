package com.dish.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		String[] pathsToSkip = {
				"/artisan/*/swagger-ui/**",
				"/artisan/product-service/api/v1/category/**",
				"/artisan/product-service/api/v1/product/bestSelling**",
				"/artisan/product-service/api/v1/product/featured**",
				"/artisan/product-service/api/v1/product/fetchProducts**",
				"/artisan/product-service/api/v1/product/productDetails/**",
				"/artisan/product-service/api/v1/product/search/**",
				"/artisan/device-service/api/v1/inventory**",
				"/artisan/user-service/api/v1/users/signup",
				"/artisan/user-service/api/v1/users/login",
				"/artisan/user-service/api/v1/users/sendRegisterOTP",
				"/artisan/user-service/api/v1/users/resetPassword",
				"/artisan/user-service/api/v1/users/validateOTP",
				"/artisan/user-service/api/v1/users/sendResetOTP",
				"/artisan/user-service/api/v1/users/verifyOTP",
				"/artisan/user-service/api/v1/admin/users/sendRegisterOTP",
				"/artisan/user-service/api/v1/admin/users/sendVerifyOTP",
				"/artisan/user-service/api/v1/admin/users/verifyOTP",
				"/artisan/user-service/api/v1/state/**",
				"/artisan/user-service/api/v1/users/delete/**",
				"/artisan/user-service/api/v1/users/getUserById/**",
				"/artisan/file-service/api/v1/document/files",
				"/artisan/product-service/api/v1/category/all",
				"/artisan/support-service/api/v1/contact_us/**"
		};

		http
				.csrf(CsrfSpec::disable)
				.authorizeExchange(exchanges -> exchanges
						.pathMatchers("/**").permitAll()
						.anyExchange().authenticated()) // This ensures that all other paths not explicitly matched will
														// require authentication
				.oauth2ResourceServer(jwt -> jwt.jwt(Customizer.withDefaults())); // Use JWT for OAuth2 resource server
		return http.build();
	}
//	@Bean
//	public CustomRequestTimeoutGatewayFilterFactory customRequestTimeoutGatewayFilterFactory() {
//		return new CustomRequestTimeoutGatewayFilterFactory();
//	}
}
