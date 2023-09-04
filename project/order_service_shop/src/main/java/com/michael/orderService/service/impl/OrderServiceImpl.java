package com.michael.orderService.service.impl;

import com.michael.clients.payment.PaymentClient;
import com.michael.clients.payment.PaymentRequest;
import com.michael.clients.payment.PaymentResponse;
import com.michael.clients.product.ProductClient;
import com.michael.clients.product.ProductResponse;
import com.michael.orderService.entity.Order;
import com.michael.orderService.exceptions.OrderNotFoundException;
import com.michael.orderService.payload.request.OrderRequest;
import com.michael.orderService.payload.response.OrderResponse;
import com.michael.orderService.repository.OrderRepository;
import com.michael.orderService.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
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
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        productClient.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating Order With Status CREATED");
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderStatus("CREATED")
                .amount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .build();
        order = orderRepository.save(order);

        log.info("Calling Payment Service to complete the payment");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus = null;
        try {
            paymentClient.doPayment(paymentRequest);
            log.info("Payment done successfully, Changing the Order status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        log.info("Order Places successfully with Order Id: {}", order.getId());
        return order.getId();
    }


    @Override
    public OrderResponse getOrderDetailsById(Long orderId) {
        log.info("Get order details for Order id: {}", orderId);
        Order order = getOrderFromDb(orderId);

        log.info("Invoking Product service to fetch the product for id: {}", order.getProductId());
        ProductResponse productResponse = productClient.getProductById(order.getProductId()).getBody();

        log.info("Getting payment information form the payment Service");
        PaymentResponse paymentResponse = paymentClient.fetchPaymentDetailsByOrderId(orderId).getBody();

        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        orderResponse.setPaymentResponse(paymentResponse);
        orderResponse.setProductResponse(productResponse);
        return orderResponse;
    }


    private Order getOrderFromDb(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %s not found", orderId)));
    }


}
