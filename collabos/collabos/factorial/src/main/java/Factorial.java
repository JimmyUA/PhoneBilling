import java.math.BigInteger;

public class Factorial {

    private BigInteger result = BigInteger.valueOf(1);

    public Factorial(int input) {
        calculate(input);
    }

    private void calculate(int value){
        for (int i = 1; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
    }

    public BigInteger getResult() {
        return result;
    }
}
