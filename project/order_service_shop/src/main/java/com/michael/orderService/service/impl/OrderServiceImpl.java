package com.michael.orderService.service.impl;

import com.michael.orderService.entity.Order;
import com.michael.orderService.payload.request.OrderRequest;
import com.michael.orderService.payload.response.OrderResponse;
import com.michael.orderService.repository.OrderRepository;
import com.michael.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderStatus("CREATED")
                .amount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .build();
        order = orderRepository.save(order);
        log.info("Order Places successfully with id: {}", order.getId());


        return 1L;
    }

    @Override
    public OrderResponse getOrderDetailsById(Long orderId) {
        return null;
    }
}
