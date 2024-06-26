package ru.kalinichenko.cache;

/* Kalinichenko 17.04.2024 */

public class Fraction implements Fractionable {
    private int num;
    private int denum;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
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

    @Cache
    @Override
    public double doubleValue() {
        System.out.println("    invoke double value");
        return (double) num/denum;
    }
}
