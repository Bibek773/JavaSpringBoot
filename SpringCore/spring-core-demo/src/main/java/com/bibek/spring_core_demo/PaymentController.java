package com.bibek.spring_core_demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            @Qualifier("bankPaymentService")
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    public void makePayment() {
        paymentService.pay();
    }
}