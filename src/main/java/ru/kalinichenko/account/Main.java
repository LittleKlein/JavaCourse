package ru.kalinichenko.account;

import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Account acc1 = new Account("Kalinichenko Vyacheslav");
        acc1.setBalance(Currency.RUB, 1);
        acc1.setBalance(Currency.EUR, 2);
        System.out.println("acc1 firstly created (RUB, EUR) = "+acc1.toString());
        acc1.setBalance(Currency.USD, 3);
        System.out.println("acc1 added USD = "+acc1.toString());
        acc1.setOwner("Мигратор тоже человек");
        System.out.println("acc1 after change owner = "+acc1.toString());
        acc1.undo();
        System.out.println("acc1 (after first undo) = "+acc1.toString());
        acc1.undo();
        acc1.undo();
        System.out.println("acc1 (after another pair of undo) = "+acc1.toString());

        Save acc2 = acc1.save();
        System.out.println("  Save: " + acc2);
    }
}
