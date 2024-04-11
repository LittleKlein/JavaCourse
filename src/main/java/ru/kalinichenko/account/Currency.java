package ru.kalinichenko.account;

public enum Currency {
    USD("Доллар США"),
    EUR("Евро"),
    RUB("Рубль");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Currency{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
