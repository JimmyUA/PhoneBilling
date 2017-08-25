import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class FactorialTest {

    private Factorial factorial;

    @Before
    public void init(){
        factorial = new Factorial();
    }

    @Test
    public void shouldReturnCorrectValue(){
        BigInteger input = BigInteger.valueOf(6);
        BigInteger expectedResult = BigInteger.valueOf(720);

        assertEquals(expectedResult, factorial.calculateFactorial(input));

        input = BigInteger.valueOf(6);
        expectedResult = BigInteger.valueOf(720);

        assertEquals(expectedResult, factorial.calculateFactorial(input));
    }

    @Test
    public void zeroInputShouldReturnOne(){
        BigInteger input = BigInteger.valueOf(0);
        BigInteger expectedResult = BigInteger.valueOf(1);

        assertEquals(expectedResult, factorial.calculateFactorial(input));
    }

    @Test(timeout = 15)
    public void shouldCalculateQuickEnough(){
        BigInteger bigInput = BigInteger.valueOf(1000000);
        factorial.calculateFactorial(bigInput);
    }


}