package ru.kalinichenko.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kalinichenko.spring.service.CheckerInt;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "ru.kalinichenko.spring")
public class Main
    {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class);
    }

}
