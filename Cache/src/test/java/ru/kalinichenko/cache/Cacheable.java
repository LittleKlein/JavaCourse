package ru.kalinichenko.cache;

interface Cacheable{

    int getValue();

    void setValue(int val);

    void resetValue(int val);
}
