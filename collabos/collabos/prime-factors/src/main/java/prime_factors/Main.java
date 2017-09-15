package prime_factors;

import java.util.InputMismatchException;
import java.util.List;
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

        List<Integer> result = PrimeFactors.factorsOf(userInput);
        System.out.println("Prime factors of " + userInput + " is: " + result);
    }
}
