package com.besstore.bmiService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main microservice class for the BMI service.
 * This class is responsible for starting microservice.
 */
@SpringBootApplication
public class AppBMI {
    public static void main(String[] args) {
        SpringApplication.run(AppBMI.class, args);
    }
}
