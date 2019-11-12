package math;

public class Calculator {

    public int add(int a, int b) throws IntegerOverflowException {
        if (a > 0 && a > b && a+b < 0) {
            throw new IntegerOverflowException("An overflow has occurred.");
        }
        if (a < 0 && b > a && a + b > 0) {
            throw new IntegerOverflowException("An underflow has occurred.");
        }
        return a+b;
    }

    public int substract(int a, int b){
        return a-b;
    }

}
