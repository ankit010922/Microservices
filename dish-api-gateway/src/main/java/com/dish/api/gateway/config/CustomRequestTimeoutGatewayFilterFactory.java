//package com.dish.api.gateway.config;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//public class CustomRequestTimeoutGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomRequestTimeoutGatewayFilterFactory.Config> {
//
//    private final WebClient.Builder webClientBuilder;
//
//    public CustomRequestTimeoutGatewayFilterFactory(WebClient.Builder webClientBuilder) {
//        super(Config.class);
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            // Create a WebClient instance with the specified timeout
//            WebClient webClient = webClientBuilder
//                    .responseTimeout(config.getRequestTimeout())
//                    .build();
//
//            // Use the WebClient to make the downstream service request
//            return webClient
//                    .method(exchange.getRequest().getMethod())
//                    .uri(exchange.getRequest().getURI())
//                    .headers(headers -> headers.putAll(exchange.getRequest().getHeaders()))
//                    .bodyValue(exchange.getRequest().getBody())
//                    .exchange()
//                    .flatMap(response -> {
//                        // Delegate handling of the downstream service response to the next filter in the chain
//                        exchange.getAttributes().put("response", response);
//                        return chain.filter(exchange);
//                    });
//        };
//    }
//
//    public static class Config {
//        private long requestTimeout;
//
//        public long getRequestTimeout() {
//            return requestTimeout;
//        }
//
//        public void setRequestTimeout(long requestTimeout) {
//            this.requestTimeout = requestTimeout;
//        }
//    }
//}
