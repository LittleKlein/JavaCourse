package ru.kalinichenko.spring.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kalinichenko.spring.service.CheckerInt;
import ru.kalinichenko.spring.service.impl.*;
import ru.kalinichenko.spring.service.ReadDataInt;
import ru.kalinichenko.spring.service.SaveDataInt;

@Configuration
public class Config {
    @Bean
    public ReadDataInt<LogData> beanReadData() {
        return new ReadData();
    }
    @Bean
    public SaveDataInt<LogData> beanSaveData() {
        return new SaveData();
    }
    @Bean
    public CheckerInt beanRefactorDateInterface() {
        return new CheckerDate();
    }
    @Bean
    public CheckerInt beanRefactorFioInterface() {
        return new CheckerFio();
    }
    @Bean
    public CheckerInt beanRefactorTypeAppInterface() {
        return new CheckerTypeApp();
    }
}
