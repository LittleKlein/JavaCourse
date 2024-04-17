package ru.kalinichenko.cache;

/* Kalinichenko 17.04.2024 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class CachingHandler implements InvocationHandler {
    private Object target;
    private final Map<String, Method> methods = new HashMap<>();
    boolean isChanged = true;
    private Object tmp;

    public <T> CachingHandler(T objectIncome) {
        this.target = objectIncome;

        for(Method method: target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res;

        if(method.isAnnotationPresent(Cache.class)) {
            if(isChanged)
            {
                res = method.invoke(target, args);
                tmp = res;
                isChanged = false;
            }
            else{
                res = tmp;
            }
        }
        else{
            res =  method.invoke(target, args);
            if(method.isAnnotationPresent(Mutator.class)) {
                isChanged = true;
            };
        };

        return res;
    }
}