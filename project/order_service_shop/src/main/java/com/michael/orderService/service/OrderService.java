package com.michael.orderService.service;

import com.michael.orderService.payload.request.OrderRequest;
import com.michael.orderService.payload.response.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetailsById(Long orderId);
}
