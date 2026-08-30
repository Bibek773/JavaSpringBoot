package com.bibek.AutowiredDemo;


import org.springframework.stereotype.Component;

@Component
public class ProductServices {

    public void showMessage() {
        System.out.println("ProductService is working");
    }
}