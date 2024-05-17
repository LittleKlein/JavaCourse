package ru.spring.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MainWeb {
    public static void main(String[] args) {
        SpringApplication.run(MainWeb.class, args);
    }
}
