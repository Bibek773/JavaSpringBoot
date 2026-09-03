package com.bibek.spring_core_demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary

public class CashPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paid by cash");
    }
}