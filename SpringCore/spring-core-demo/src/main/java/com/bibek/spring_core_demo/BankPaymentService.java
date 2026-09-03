package com.bibek.spring_core_demo;

import org.springframework.stereotype.Component;

@Component
public class BankPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paid by bank");
    }
}