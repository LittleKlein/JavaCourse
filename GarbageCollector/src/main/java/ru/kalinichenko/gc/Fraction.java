package ru.kalinichenko.gc;

public class Fraction implements Fractionable {
    private int num;
    private int denum;

    public Fraction(int num, int denum) {
        setNum(num);
        setDenum(denum);
    }

    @Mutator
    public void setNum(int num) {
        System.out.println("     set numerator");
        this.num = num;
    }

    @Mutator
    public void setDenum(int denum) {
        System.out.println("     set denumerator");
        this.denum = denum;
    }

    @Cache(timeout=1000)
    @Override
    public double doubleValue() {
        System.out.println("    invoke double value");
        return (double) num/denum;
    }
}
