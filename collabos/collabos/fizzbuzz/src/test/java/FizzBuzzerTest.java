import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzerTest {

    @Test
    public void resultArrayLengthEqualsInput(){
        int input = 6;
        int expectedLength = input;

        int actualLength = new FizzBuzzer(input).getResult().length;

        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void firstElementEqualsTwo(){
        int input = 6;
        String expectedElement = "2";

        String firstElement = new FizzBuzzer(input).getResult()[1];

        assertEquals(expectedElement, firstElement);
    }

    @Test
    public void secondElementEqualsFizz(){
        int input = 6;
        String expectedElement = "Fizz";

        String secondElement = new FizzBuzzer(input).getResult()[2];

        assertEquals(expectedElement, secondElement);
    }

    @Test
    public void forthElementEqualsBuzz(){
        int input = 6;
        String expectedElement = "Buzz";

        String forthElement = new FizzBuzzer(input).getResult()[4];

        assertEquals(expectedElement, forthElement);
    }

    @Test
    public void fourteenthElementEqualsFizzBuzz(){
        int input = 15;
        String expectedElement = "FizzBuzz";

        String forthElement = new FizzBuzzer(input).getResult()[14];

        assertEquals(expectedElement, forthElement);
    }

}
