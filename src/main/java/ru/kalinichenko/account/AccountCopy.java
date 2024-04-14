package ru.kalinichenko.account;

import java.util.HashMap;

public class AccountCopy extends Account {
    public AccountCopy(String owner, HashMap<Currency, Number> balance) throws CloneNotSupportedException {
        super(owner);   // вызываем конструктор родителя
        for(HashMap.Entry<Currency, Number> entry: balance.entrySet()) {   // копируем хэш-мэп
            Currency key = entry.getKey();
            Number val = entry.getValue();
            this.balance.put(key, val);
        }
    }

    @Override
    public void undo (){
        return;
    }
    public void setBalance(Currency cur, double saldo) throws CloneNotSupportedException {
        return;
    }
}
