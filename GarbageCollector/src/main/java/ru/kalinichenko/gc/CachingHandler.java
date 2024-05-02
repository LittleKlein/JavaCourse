package ru.kalinichenko.gc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CachingHandler<T> implements InvocationHandler {
    private T currentObject;
    //
    private Map<String, CacheEntity> cache = new ConcurrentHashMap<>();
    private Map<String, String> state = new ConcurrentHashMap<>();

    // внутренний класс, хранящий связку "кэшированное значение" + "время жизни"
    class CacheEntity{
        private Object value;   // Само кэшируемое значение
        private long time;      // Время помещение в кэш
        private long timeout; // Время жизни текущего объекта в миллисекундах

        public CacheEntity(Object value, long timeout) {
            this.value = value;
            this.timeout = timeout;
            resetTime();
        }

        public Object getValue() {
            return value;
        }

        public long getTime() {
            return time;
        }
        public long getTimeout() {
            return timeout;
        }

        public void resetTime() {
            time = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            Date date = new Date(time);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss,SSS");
            String strDate = simpleDateFormat.format(date);

            return "Cache Entity{" +
                    "value='" + value + '\'' +
                    ", time=" + strDate + '}';
        }
    }

    public CachingHandler(T currentObject) {
        this.currentObject = currentObject;
    }

    // Метод очистки который будет вызываеться извне потоком GarbageCollector
    public void doCleaning()
    {
        long now;
        now = System.currentTimeMillis();
        for (Map.Entry<String, CacheEntity> entry : cache.entrySet())
        {
            String key = entry.getKey();
            CacheEntity value = entry.getValue();
            // Если время жизни текущего объекта в кэше истекло, то удаляем его
            if (now - value.getTime() >= value.getTimeout())
            {
                cache.remove(key);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object objectResult;
        Method currentMethod;
        CacheEntity cacheEntity;
        String stateKey, stateValue, cacheKey;

        currentMethod = currentObject.getClass().getMethod(method.getName(), method.getParameterTypes());

        if (currentMethod.isAnnotationPresent(Cache.class)) {
            cacheKey = currentObject.getClass().getSimpleName() + "." + currentMethod.getName() + "$" + state.hashCode();
            if (cache.containsKey(cacheKey)) {
                cacheEntity = cache.get(cacheKey);
                objectResult = cacheEntity.getValue();
                cacheEntity.resetTime();
                // Каждый раз при обращению к значению кэша, его время жизни обнуляется и начинает отсчитывать снова
                cache.put(cacheKey, cacheEntity);
                return objectResult;
            }
            objectResult = method.invoke(currentObject, args);
            Cache annotation = currentMethod.getAnnotation(Cache.class);
            cache.put(cacheKey, new CacheEntity(objectResult, annotation.timeout()));
            return objectResult;
        }
        if (currentMethod.isAnnotationPresent(Mutator.class)) {
            // Если вызван мутатор, то обновляем текущее "состояние" объекта
            stateKey = currentObject.getClass().getSimpleName() + "." + currentMethod.getName();
            if (args == null || args.length == 0) {
                stateValue = "NULL";
            } else {
                stateValue = args.toString();
            }
            // Безусловно либо вставляем новую запись, либо перезатираем существующую с тем-же ключом
            state.put(stateKey, stateValue);
        }
        return method.invoke(currentObject, args);
    }
}