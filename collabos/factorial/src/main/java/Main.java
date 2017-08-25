import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BigInteger userInput = askNumber();
        BigInteger factorial = new Factorial().calculateFactorial(userInput);

        System.out.println("Factorial from " + userInput + " is : " + factorial);
    }

    private static BigInteger askNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number:");

        BigInteger userInut = BigInteger.valueOf(scanner.nextLong());

        return userInut;
    }
}
