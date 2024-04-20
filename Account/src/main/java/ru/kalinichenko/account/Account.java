package ru.kalinichenko.account;

import java.util.*;

public class Account implements Cloneable
{
    private String owner;
    private Map<Currency, Number> balance = new HashMap<>();
    private Deque<Undo> undos;               // история для отмен

    public Account (String owner) {
        if(owner==null||owner.isEmpty()) throw new IllegalArgumentException("Owner can not be empty !");
        this.undos = new ArrayDeque<>();
        this.balance = new HashMap<>();
        this.setOwner(owner);
    }

    public void setOwner(String owner)  {
        String prevOwner = this.owner;
        undos.push(() -> this.owner = prevOwner);
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setBalance(Currency cur, double saldo)  {
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

    public Save save() {
        return new AccountCopy();
    }


    private class AccountCopy implements Save {
        private String owner;
        private Map<Currency, Number> balance;
        private Date timeStamp;

        public AccountCopy() {
            this.owner = Account.this.owner;
            this.timeStamp = new Date();
            this.balance = new HashMap<>(Account.this.balance);
        }

        public Date getTimeStamp(){
            return timeStamp;
        }

        public void load(){
            Account.this.owner = owner;
            Account.this.balance.clear();
            Account.this.balance.putAll(balance);
        }
        @Override
        public String toString() {
            return "Copied Account{" +
                    "Owner='" + owner + '\'' +
                    ", Balance=" + balance +
                    '}';
        }
    }
}

interface Undo{
    void undo();
}

public interface Save{
    public void load();
}