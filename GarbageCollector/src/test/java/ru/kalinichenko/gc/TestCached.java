package ru.kalinichenko.gc;

public class TestCached implements Cacheable{
    private int value;

    public TestCached() {
        this.value = 0;
    }

    @Cache
    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        value = val;
    }

    @Mutator
    public void resetValue(int val) {
        value = val;
    }

}
