package ru.kalinichenko.account;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Account implements Cloneable
{
    protected String owner;
    protected HashMap<Currency, Number> balance = new HashMap<>();
    protected ArrayDeque<Undo> undos;               // история для отмен

    public Account (String owner) throws CloneNotSupportedException {
        if(owner==null||owner.isEmpty()) throw new IllegalArgumentException("Owner can not be empty !");
        this.undos = new ArrayDeque<>();
        this.balance = new HashMap<>();
        this.setOwner(owner);
    }

    public void setOwner(String owner) throws CloneNotSupportedException {
        String prevOwner = this.owner;
        undos.push(() -> this.owner = prevOwner);
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public HashMap<Currency, Number> getBalance() {
        return balance;
    }

    public void setBalance(Currency cur, double saldo) throws CloneNotSupportedException {
        if(cur == null) throw new IllegalArgumentException("Currency can not be empty !");
        if(saldo < 0) throw new IllegalArgumentException("Saldo can not be less than zero !");

        Number lastVal = balance.get(cur);
        if(lastVal == null)
            undos.push(() -> balance.remove(cur));
        else
            undos.push(() -> balance.put(cur, lastVal));
        balance.put(cur, saldo);
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
        if(undos.isEmpty()){ throw new ArrayIndexOutOfBoundsException("There is no more saved objects !"); }
        undos.pop().undo();
    }
}

interface Undo{
    void undo();
}