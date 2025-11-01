package com.example.Practice.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PracticeController1Application {
	public static void main(String[] args) {
		SpringApplication.run(PracticeController1Application.class, args);
	}
}
