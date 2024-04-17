package ru.kalinichenko.cache;

/* Kalinichenko 17.04.2024 */

public class Starter {
    public static void main(String[] args) {
        System.out.println("create main object");
        Fraction fr = new Fraction(2,3);
        System.out.println("create proxy object");
        Fractionable num = Utils.cache(fr);
        System.out.println("call to doubleValue #1");
        num.doubleValue();// sout сработал
        System.out.println("call to doubleValue #2");
        num.doubleValue();// sout молчит
        System.out.println("call to doubleValue #3");
        num.doubleValue();// sout молчит
        System.out.println("call to setNum");
        num.setNum(5);
        System.out.println("call to doubleValue #4");
        num.doubleValue();// sout сработал
        System.out.println("call to doubleValue #5");
        num.doubleValue();// sout молчит
    }
}
