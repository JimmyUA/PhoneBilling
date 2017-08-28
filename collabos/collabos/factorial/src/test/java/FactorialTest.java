import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class FactorialTest {

    @Test
    public void shouldReturnOne(){
        int input = 0;
        BigInteger expected = BigInteger.valueOf(1);

        BigInteger result = new Factorial(input).getResult();

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturn120(){
        int input = 5;
        BigInteger expected = BigInteger.valueOf(120);

        BigInteger result = new Factorial(input).getResult();

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCorrectValue(){
        int input = 20;
        BigInteger expected = BigInteger.valueOf(2432902008176640000L);

        BigInteger result = new Factorial(input).getResult();

        assertEquals(expected, result);
    }

}
