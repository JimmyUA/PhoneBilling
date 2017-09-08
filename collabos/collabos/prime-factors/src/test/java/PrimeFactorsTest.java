import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;

import static prime_factors.PrimeFactors.factorsOf;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;

public class PrimeFactorsTest {

    @Test
    public void factors() {
        assertThat(factorsOf(1), CoreMatchers.is(Collections.<Integer>emptyList()));
        assertThat(factorsOf(2), CoreMatchers.is(asList(2)));
        assertThat(factorsOf(3), CoreMatchers.is(asList(3)));
        assertThat(factorsOf(4), CoreMatchers.is(asList(2, 2)));
        assertThat(factorsOf(5), CoreMatchers.is(asList(5)));
        assertThat(factorsOf(6), CoreMatchers.is(asList(2, 3)));
        assertThat(factorsOf(7), CoreMatchers.is(asList(7)));
        assertThat(factorsOf(8), CoreMatchers.is(asList(2, 2, 2)));
        assertThat(factorsOf(9), CoreMatchers.is(asList(3, 3)));
        assertThat(factorsOf(10), CoreMatchers.is(asList(2, 5)));
        assertThat(
                factorsOf(2 * 3 * 3 * 5 * 7 * 11 * 13 * 17),
                CoreMatchers.is(asList(2, 3, 3, 5, 7, 11, 13, 17)));
    }

}
