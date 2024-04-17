package ru.kalinichenko.cache;

/* Kalinichenko 17.04.2024 */

class FractionTest {

    @org.junit.jupiter.api.Test
    void doubleValue() {

        Fraction frTest = new Fraction(2,3);
        System.out.println("create proxy object");
        Fractionable numTest = Utils.cache(frTest);
        System.out.println("call to doubleValue #1");
        numTest.doubleValue();// sout сработал
        System.out.println("call to doubleValue #2");
        numTest.doubleValue();// sout молчит
        System.out.println("call to doubleValue #3");
        numTest.doubleValue();// sout молчит
        System.out.println("call to setNum");
        numTest.setNum(5);
        System.out.println("call to doubleValue #4");
        numTest.doubleValue();// sout сработал
        System.out.println("call to doubleValue #5");
        numTest.doubleValue();// sout молчит


    }
}