package com.michael.clients.product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PutMapping("/api/v1/products/reduceQuantity/{productId}")
    ResponseEntity<Void> reduceQuantity(@PathVariable(name = "productId") Long productId,
                                        @RequestParam(name = "quantity") Long quantity);


    @CircuitBreaker(name = "product-service", fallbackMethod = "getDefaultProduct")
    @GetMapping("/api/v1/products/get/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId);

    default ResponseEntity<ProductResponse> getDefaultProduct(Long productId, Exception exception) {
        return new ResponseEntity<>(ProductResponse.builder()
                .productName("Default product Name")
                .quantity(0L)
                .price(0L)
                .build(), HttpStatus.NO_CONTENT);
    }
}
