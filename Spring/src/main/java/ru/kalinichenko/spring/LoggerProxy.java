package ru.kalinichenko.spring;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class LoggerProxy implements InvocationHandler {
    private String logFile;
    private Object bean;

    public LoggerProxy(String logFile, Object bean) {
        this.logFile = logFile;
        this.bean   = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object val = method.invoke(bean, args);
        try {
            String s = System.getProperty("user.dir") + "\\src\\main\\resources\\logMethod\\"+logFile;

            FileWriter writer = new FileWriter(s, true);
            String str = (new Date())+" "+bean.getClass().getName()+" "+(val!=null?val.toString():"")+" "+ Arrays.toString(args);
            writer.write(str+"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }
        return val;
    }
}
