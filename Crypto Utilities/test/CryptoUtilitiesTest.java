import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Junyu Xue
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_18() {
        NaturalNumber n = new NaturalNumber2(18);
        NaturalNumber nExpected = new NaturalNumber2(18);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }
    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_2_2_11() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber pExpected = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(11);
        NaturalNumber mExpected = new NaturalNumber2(11);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    @Test
    public void isWitnessToCompositeness_5_8() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(8);
        boolean Expected = true;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(Expected, result);
    }

    @Test
    public void isWitnessToCompositeness_2_11() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(11);
        boolean Expected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(Expected, result);
    }

    /*
     * Tests of isPrime1
     */

    @Test
    public void isPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void isPrime1_4() {
        NaturalNumber n = new NaturalNumber2(4);
        boolean nExpected = false;
        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void isPrime1_11() {
        NaturalNumber n = new NaturalNumber2(11);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, result);
    }

    /*
     * Tests of isPrime2
     */

    @Test
    public void isPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void isPrime2_4() {
        NaturalNumber n = new NaturalNumber2(4);
        boolean nExpected = false;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void isPrime2_11() {
        NaturalNumber n = new NaturalNumber2(11);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */

    @Test
    public void generateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void generateNextLikelyPrime_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void generateNextLikelyPrime_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
