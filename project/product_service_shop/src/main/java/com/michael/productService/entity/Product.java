package com.michael.productService.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String productName;
    @Column(name = "PRICE", nullable = false)
    private Long price;
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;
}
