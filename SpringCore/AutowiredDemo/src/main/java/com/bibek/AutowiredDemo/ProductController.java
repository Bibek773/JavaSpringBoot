package com.bibek.AutowiredDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductController {

    private final ProductServices productService;

    @Autowired // this is working as dependency injection, it will inject the ProductServices bean into this constructor
    public ProductController(ProductServices productService) {
        this.productService = productService;
    }

    public void test() {
        productService.showMessage();
    }
}
