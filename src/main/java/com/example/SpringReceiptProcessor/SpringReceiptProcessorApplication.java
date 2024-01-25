package com.example.SpringReceiptProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringReceiptProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReceiptProcessorApplication.class, args);
	}

}
