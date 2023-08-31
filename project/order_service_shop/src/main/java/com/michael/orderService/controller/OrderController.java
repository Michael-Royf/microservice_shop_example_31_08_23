package com.michael.orderService.controller;

import com.michael.orderService.payload.request.OrderRequest;
import com.michael.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1]/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), CREATED);
    }


}
