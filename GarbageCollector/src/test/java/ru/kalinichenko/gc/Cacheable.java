package ru.kalinichenko.gc;

interface Cacheable {

    int getValue();

    void setValue(int val);

    void resetValue(int val);
}
