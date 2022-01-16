package com.ies.blueberry;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class BlueberryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueberryApplication.class, args); 
	}

}
