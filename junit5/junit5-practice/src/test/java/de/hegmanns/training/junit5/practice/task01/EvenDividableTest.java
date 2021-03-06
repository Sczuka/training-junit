package de.hegmanns.training.junit5.practice.task01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Checks for {@link CalculatorForDivide#isDividableIntoEventParts(int)}.
 *
 * <p>
 *     Possible inputs for test:<br/>
 *     <ul>
 *         <li>8 must result in <i>true</i></li>
 *     </ul>
 * </p>
 */
public class EvenDividableTest {

    @Test
    public void amountOf8IsEvenDividable() {
        // arrange

        // act
        boolean isEvenDividable = CalculatorForDivide.isDividableIntoEventParts(8);

        // assert
        Assertions.assertEquals(true, isEvenDividable);
    }
}
