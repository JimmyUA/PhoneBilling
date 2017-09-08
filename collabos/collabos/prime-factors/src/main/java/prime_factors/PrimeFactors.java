package prime_factors;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public static List<Integer> factorsOf(int number) {
        final List<Integer> factors = new ArrayList<Integer>();
        int remainder = number;
        int divider = 2;
        while (remainder > 1) {
            while (remainder % divider == 0) {
                factors.add(divider);
                remainder /= divider;
            }
            divider++;
        }
        return factors;
    }
}
