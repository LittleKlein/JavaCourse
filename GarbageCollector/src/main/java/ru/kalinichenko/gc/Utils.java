package ru.kalinichenko.gc;

import java.lang.reflect.Proxy;

public class Utils {

    private static CachingHandler cachingHandler;
    private static GC gc;

    public static <T> T cache (T objectIncome) {
        cachingHandler = new CachingHandler(objectIncome);

        gc = new GC();
        gc.setName("My Garbage Collector");
        gc.setCachingHandler(cachingHandler);
        gc.start();

        return (T) Proxy.newProxyInstance(
                objectIncome.getClass().getClassLoader(),
                objectIncome.getClass().getInterfaces(),
                cachingHandler);
    }
}

