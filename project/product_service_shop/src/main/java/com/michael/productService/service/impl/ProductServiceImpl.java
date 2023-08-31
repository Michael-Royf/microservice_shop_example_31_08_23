package com.michael.productService.service.impl;

import com.michael.productService.entity.Product;
import com.michael.productService.exceptions.payload.ProductAlreadyExistsException;
import com.michael.productService.exceptions.payload.ProductNotFoundException;
import com.michael.productService.payload.request.ProductRequest;
import com.michael.productService.payload.response.MessageResponse;
import com.michael.productService.payload.response.ProductResponse;
import com.michael.productService.repository.ProductRepository;
import com.michael.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String INSUFFICIENT_QUANTITY = "Product does not have sufficient Quantity";

    private final ModelMapper mapper;
    private final ProductRepository productRepository;


    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("Adding product...");

        if (productRepository.findByProductName(productRequest.getProductName()).isPresent()) {
            throw new ProductAlreadyExistsException(String.format("Product with name: %s already exists", productRequest.getProductName()));
        }
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        product = productRepository.save(product);
        log.info("Product created");
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return mapper.map(getProductFromDb(productId), ProductResponse.class);
    }


    @Override
    public ProductResponse getProductByProductName(String productName) {
        Product product = productRepository
                .findByProductName(productName)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with product name: %s not found", productName)));
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        return null;
    }

    @Override
    public MessageResponse deleteProduct(Long productId) {
        Product product = getProductFromDb(productId);
        productRepository.delete(product);
        return new MessageResponse(String.format("Product with id: %s was deleted", productId));
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {

    }


    private Product getProductFromDb(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %s not found", productId)));
    }

}
