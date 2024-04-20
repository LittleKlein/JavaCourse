package ru.kalinichenko.cache;

public class TestCached implements Cacheable{
    private int value;

    public TestCached() {
        this.value = 0;
    }

    @Cache
    public int getValue() {
        //System.out.println("    Cached: call to getValue");
        return value;
    }

    public void setValue(int val) {
        value = val;
        //System.out.println("    Cached: call to setValue");
    }

    @Mutator
    public void resetValue(int val) {
        value = val;
        //System.out.println("    Cached: call to resetValue");
    }

}
