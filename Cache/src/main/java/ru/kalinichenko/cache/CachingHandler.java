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

    private int callCacheCount;

    public <T> CachingHandler(T objectIncome) {
        this.target = objectIncome;
        callCacheCount = 0;

        for(Method method: target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    public int getCallCacheCount() {
        return callCacheCount;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res;
        Method classMeth;

        classMeth = target.getClass().getMethod(method.getName(), method.getParameterTypes());

        if(classMeth.isAnnotationPresent(Cache.class)) {
            if(isChanged)
            {
                res = method.invoke(target, args);
                tmp = res;
                isChanged = false;
            }
            else{
                res = tmp;
                callCacheCount++;
            }
        }
        else{
            res =  method.invoke(target, args);
            if(classMeth.isAnnotationPresent(Mutator.class)) {
                isChanged = true;
            };
        };

        return res;
    }
}