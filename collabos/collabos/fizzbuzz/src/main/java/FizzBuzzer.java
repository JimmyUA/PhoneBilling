public class FizzBuzzer {
    private String[] result;

    public FizzBuzzer(int input) {
        result = new String[input];
        fillResultArray(input);
    }

    private void fillResultArray(int value){

        int arrayIndex;
        for (int i = 1; i <= value; i++) {
            String element = "";
            if (isDividableByThree(i)){
                element += "Fizz";
            }
            if (isDividableByFive(i)){
                element += "Buzz";
            }
            if(!isDividableByThree(i) && !isDividableByFive(i)){
                element += i;
            }

            arrayIndex = i - 1;
            result[arrayIndex] = element;
        }
    }

    private boolean isDividableByFive(int number) {
        return number % 5 == 0;
    }

    private boolean isDividableByThree(int number) {
        return number % 3 == 0;
    }

    public String[] getResult() {
        return result;
    }
}
