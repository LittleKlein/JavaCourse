package ru.kalinichenko.gc;

public class TestNotCached implements Cacheable{
    private int value;

    public TestNotCached() {
        this.value = 0;
    }

    public int getValue() {
        //System.out.println("    NotCached: call to getValue");
        return value;
    }

    public void setValue(int val) {
        value = val;
        //System.out.println("    NotCached: call to setValue");
    }

    @Mutator
    public void resetValue(int val) {
        value = val;
        //System.out.println("    NotCached: call to resetValue");
    }

}
