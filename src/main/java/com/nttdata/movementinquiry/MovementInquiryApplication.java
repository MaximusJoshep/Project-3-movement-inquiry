package com.nttdata.movementinquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MovementInquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovementInquiryApplication.class, args);
	}

}
