package ru.kalinichenko.gc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

/*
    Список проверок:
    1) Кэш кэширует
    1.1) Кэш найдётся
    1.2) Первый кэш всегда оригинальный
    1.3) Второй и остальные для всех методов ходят внутрь
    2) Мутатор сбрасывает
    2.1) После вызова мутатора кэш на ВСЕ методы сбрасывается
    2.2) Мутатор отрабатывает как положено
    3) Обычные методы не кэшируются и ничего не сбрасывают
    3.1) Метод вызовется
    3.2) Метод не закэшируется
    3.3) Метод не сбросит кэш
    4) Если истек срок жизни кэша, то кэшируем заново
 */

class CacheTest {
    @Test
    @DisplayName("1. Кэш кэширует")
    public void test3_1()
    {
        TestFraction fraction = new TestFraction(1, 2);
        Fractionable fractionProxy = Utils.cache(fraction);
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(fraction.count, 1);
    }

    @Test
    @DisplayName("2. Мутатор сбрасывает")
    public void test3_2()
    {
        TestFraction fraction = new TestFraction(1, 2);
        Fractionable fractionProxy = Utils.cache(fraction);
        fractionProxy.doubleValue();
        fractionProxy.setNum(8);
        fractionProxy.doubleValue();
        Assertions.assertEquals(fraction.count, 2);
    }

    @Test
    @DisplayName("3. Обычные методы не кэшируются и ничего не сбрасывают")
    public void test3_3()
    {
        TestFraction fraction = new TestFraction(1, 2);
        Fractionable fractionProxy = Utils.cache(fraction);
        fractionProxy.doubleValue();
        System.out.println(fractionProxy.toString());
        fractionProxy.doubleValue();
        System.out.println(fractionProxy.toString());
        Assertions.assertEquals(fraction.count, 1);
        Assertions.assertEquals(fraction.countOther, 2);
    }

    @Test
    @DisplayName("4. Если истек срок жизни кэша, то кэшируем заново")
    public void test3_4() throws InterruptedException {
        TestFraction fraction = new TestFraction(1, 2);
        Fractionable fractionProxy = Utils.cache(fraction);
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Thread.sleep(1500);
        fractionProxy.doubleValue();
        Assertions.assertEquals(fraction.count, 2);
    }

}