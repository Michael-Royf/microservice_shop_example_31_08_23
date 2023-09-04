package com.michael.paymentService.service.impl;

import com.michael.clients.payment.PaymentResponse;
import com.michael.paymentService.entity.TransactionDetails;
import com.michael.paymentService.exceoptions.payload.PaymentNotFoundException;
import com.michael.paymentService.payload.request.PaymentRequest;
import com.michael.paymentService.repository.PaymentRepository;
import com.michael.paymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDay(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        transactionDetails = paymentRepository.save(transactionDetails);

        log.info("Transaction Completed With Id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
        log.info("Getting Payment Details For The Order Id: {}", orderId);
        return mapper.map(getTransactionDetailsFromDB(orderId), PaymentResponse.class);
    }


    private TransactionDetails getTransactionDetailsFromDB(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Transaction Details with order id %s not found", orderId)));
    }
}
