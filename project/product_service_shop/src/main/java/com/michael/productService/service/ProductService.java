package com.michael.productService.service;

import com.michael.productService.payload.request.ProductRequest;
import com.michael.productService.payload.response.MessageResponse;
import com.michael.productService.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    ProductResponse getProductByProductName(String productName);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    MessageResponse deleteProduct(Long productId);


    void reduceQuantity(Long productId, Long quantity);
}
