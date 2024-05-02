package ru.kalinichenko.gc;

public class Starter {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("create main object");
        Fraction fr = new Fraction(2,3);
        System.out.println("create proxy object");
        Fractionable num = Utils.cache(fr);
        System.out.println("call to doubleValue #1 (ниже должен быть sout)");
        num.doubleValue();// sout сработал
        System.out.println("call to doubleValue #2 (ниже НЕ должен быть sout)");
        num.doubleValue();// sout молчит
        System.out.println("call to setNum 5");
        num.setNum(5);
        System.out.println("call to doubleValue #3 (ниже должен быть sout)");
        num.doubleValue();// sout сработал
        System.out.println("call to doubleValue #4 (ниже НЕ должен быть sout)");
        num.doubleValue();// sout молчит
        System.out.println("call to setNum 2");
        num.setNum(2);
        System.out.println("call to doubleValue #5 (ниже должен быть sout)");
        num.doubleValue();// sout сработал
        System.out.println("call to doubleValue #6 (ниже должен НЕ быть sout)");
        num.doubleValue();// sout молчит
        System.out.println("call to sleep");
        Thread.sleep(1500);
        System.out.println("call to doubleValue #7 (ниже должен быть sout)");
        num.doubleValue();// sout сработал
        System.out.println("call to doubleValue #8 (ниже НЕ должен быть sout)");
        num.doubleValue();// sout молчит
    }
}
