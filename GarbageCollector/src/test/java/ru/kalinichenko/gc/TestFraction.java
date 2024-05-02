package ru.kalinichenko.gc;

public class TestFraction implements Fractionable{
    int num, denum;
    int count, countOther;

    public TestFraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
        this.count = 0;
        this.countOther = 0;
    }

    @Override
    @Cache(timeout = 1000)
    public double doubleValue() {
        count++;
        return (double) num/denum;
    }

    @Override
    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    @Mutator
    public void setDenum(int denum) {

        this.denum = denum;
    }

    @Override
    public String toString() {
        countOther++;
        return "TestFraction{" + "num=" + num + ",denum= " + denum + ",count=" + count + ",countOther=" + countOther + "}" ;
    }
}
