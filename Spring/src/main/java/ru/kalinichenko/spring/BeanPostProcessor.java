package ru.kalinichenko.spring;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(LogTransformation.class)) {
            LogTransformation ann = (LogTransformation)beanClass.getAnnotation(LogTransformation.class);
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new LoggerProxy(ann.logFileName(), bean));
        }
        return bean;
    }
}
