package com.example.demospring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller","dao","model"})
public class DemoSpring2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpring2Application.class, args);
    }

}
