package com.michael.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column
    private Long quantity;
    @Column(name = "order_date")
    private Instant orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column
    private Long amount;

}
