package com.bibek.AutowiredDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutowiredDemoApplication {
	public static void main(String[] args) {

		ApplicationContext context =
				SpringApplication.run(AutowiredDemoApplication.class, args);

		ProductController controller =
				context.getBean(ProductController.class);

		controller.test();
	}
}
