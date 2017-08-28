import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter an integer number:");

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
        }catch (NumberFormatException e){
            System.out.println("You have entered not an integer!");
        }
        String[] result = new FizzBuzzer(userInput).getResult();

        System.out.println(Arrays.toString(result));
    }
}
