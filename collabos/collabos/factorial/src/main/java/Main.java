import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter an integer number:");

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
        }catch (NumberFormatException | InputMismatchException e){
            System.out.println("You have entered not an integer!");
            System.exit(0);
        }
        BigInteger result = new Factorial(userInput).getResult();

        System.out.println("Factorial of " + userInput + " is: " + result);
    }
}
