import java.math.BigInteger;

public class Factorial {

    
    public BigInteger calculateFactorial(BigInteger userInput){
        BigInteger result = BigInteger.valueOf(1);

        for (int i = 0; BigInteger.valueOf(i).compareTo(userInput) < 0; i++) {
            result = result.multiply(userInput.subtract(BigInteger.valueOf(i)));
        }

        return result;
    }
}
