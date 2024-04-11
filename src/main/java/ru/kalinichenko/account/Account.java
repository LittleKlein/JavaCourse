package ru.kalinichenko.account;

import java.util.HashMap;
import java.util.Stack;

public class Account implements Cloneable
{
    protected String owner;
    protected HashMap<Currency, Number> balance = new HashMap<>();
    protected Stack<Account> history;

    private Account(String owner) {
        this.owner = owner;
        history = new Stack<>();
    }
    public static Account withOwner(String owner) {
        if(owner==null||owner.isEmpty()) throw new IllegalArgumentException("Owner can not be empty !");
        return new Account(owner);
    }

    public void setOwner(String owner) throws CloneNotSupportedException {
        this.owner = owner;
        this.save();
    }

    public String getOwner() {
        return owner;
    }

    public HashMap<Currency, Number> getBalance() {
        return balance;
    }

    public void setBalance(Currency cur, double saldo) throws CloneNotSupportedException {
        if(cur==null) throw new IllegalArgumentException("Currency can not be empty !");
        if(saldo < 0) throw new IllegalArgumentException("Saldo can not be less than zero !");
        balance.put(cur, saldo);
        this.save();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Account accCopy = null;
        try {
            accCopy = (Account) super.clone();
        } catch (CloneNotSupportedException e) {
            accCopy = new Account(this.getOwner());
        }
        accCopy.balance = (HashMap<Currency, Number>) this.balance.clone();
        return accCopy;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Owner='" + owner + '\'' +
                ", Balance=" + balance +
                '}';
    }

    public void undo()
    {
        if(history.isEmpty()){ throw new ArrayIndexOutOfBoundsException("There is no more saved objects !"); }
        Account prevAccountState = history.pop();
        System.out.println("     Popped: "+prevAccountState);
        this.owner = prevAccountState.getOwner();
        this.balance = (HashMap<Currency, Number>) prevAccountState.balance.clone();
        System.out.println("              this.owner: "+this.owner);
        System.out.println("              this.balance: "+this.balance);
    }

    public Account save() throws CloneNotSupportedException {
        Account savedAccount = (Account) this.clone();
        history.push(savedAccount);
        System.out.println("  Pushed: "+savedAccount);
        return savedAccount;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Account acc1 = Account.withOwner("Kalinichenko Vyacheslav");
        acc1.setBalance(Currency.RUB, 1);
        acc1.setBalance(Currency.EUR, 2);
        System.out.println("acc1 = "+acc1.toString());
        acc1.setBalance(Currency.USD, 3);
        System.out.println("acc1 = "+acc1.toString());
        acc1.undo();
        System.out.println("acc1 = "+acc1.toString());
        acc1.undo();
        acc1.undo();
        System.out.println("acc1 = "+acc1.toString());
        //acc1.undo();
    }
}
