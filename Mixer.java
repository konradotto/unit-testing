package math;

public class Mixer {
    private int num1;
    private int num2;
    static int total = 0;

    public Mixer() {
        this.num1 = 1;
        this.num2 = 2;
        total = total + num1 + num2;
        System.out.println("The value of total in the constructor is " + total);
    }

    public static void mix(int num1, int num2) {
        num1 = num1 + num2;
        num2 = num1 - num2;
        num1 = num1 - num2;
        System.out.println("The value of num1 in mix is " + num1);
    }

    public static void mixMixers(Mixer m1, Mixer m2) {
        System.out.println("The value of total in mixMixers is " + total);
        Mixer.total = m1.num1 + m1.num2;
        System.out.println("The value of total is " + total);
        m1 = m2;
        m1.num2 = 3;
        System.out.println("The value of m2.num1 in mixMixers is " + m2.num1);
    }

    public static void main(String[] args) {
        Mixer m1 = new Mixer();
        Mixer m2 = new Mixer();
        int num1 = 7;
        int num2 = 8;
        mixMixers(m1, m2);
        mix(num1, num2);
        System.out.println("The value of num2 in main is " + num2);
        m2 = m1;
        mixMixers(m1, m2);
    }
}