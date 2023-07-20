import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The test for the methods in StringReassembly.
 *
 * @author Junyu Xue
 *
 */
public class StringReassemblyTest {

    /*
     * Test for combination method
     */

    /**
     * Test for overlap one char condition.
     */
    @Test
    public void combination_abc_ccd() {
        String str1 = "abc";
        String str2 = "ccd";
        String combine = StringReassembly.combination(str1, str2, 1);
        String combineExpected = "abccd";
        assertEquals(combineExpected, combine);
    }

    /**
     * Test for overlap two char condition.
     */
    @Test
    public void combination_abcc_ccd() {
        String str1 = "abcc";
        String str2 = "ccd";
        String combine = StringReassembly.combination(str1, str2, 2);
        String combineExpected = "abccd";
        assertEquals(combineExpected, combine);
    }

    /**
     * Test for overlap no char condition.
     */
    @Test
    public void combination_ab_cd() {
        String str1 = "ab";
        String str2 = "cd";
        String combine = StringReassembly.combination(str1, str2, 0);
        String combineExpected = "abcd";
        assertEquals(combineExpected, combine);
    }

    /*
     * Test for addToSetAvoidingSubstrings method
     */

    /**
     * Test for no substring condition.
     */
    @Test
    public void addToSetAvoidingSubstrings_five_four() {
        Set<String> strSet = new Set1L<>();
        strSet.add("five");
        String str = "four";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("five");
        strSetExpected.add("four");
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Test for str is substring condition.
     */
    @Test
    public void addToSetAvoidingSubstrings_five_ive() {
        Set<String> strSet = new Set1L<>();
        String str = "five";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        String str2 = "ive";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str2);
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("five");
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Test for element of set is substring condition.
     */
    @Test
    public void addToSetAvoidingSubstrings_ive_five() {
        Set<String> strSet = new Set1L<>();
        String str = "ive";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        String str2 = "five";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str2);
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("five");
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Test for there is two elements in set condition.
     */
    @Test
    public void addToSetAvoidingSubstrings_five_four_ive() {
        Set<String> strSet = new Set1L<>();
        strSet.add("five");
        String str = "four";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        String str2 = "ive";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str2);
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("five");
        strSetExpected.add("four");
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Test for empty set condition.
     */
    @Test
    public void addToSetAvoidingSubstrings_five() {
        Set<String> strSet = new Set1L<>();
        String str = "five";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("five");
        assertEquals(strSetExpected, strSet);
    }

    /*
     * Test for linesFromInput method
     */
    /**
     * Test for no substring condition.
     */
    @Test
    public void linesFromInputTest1() {
        Set<String> expected = new Set1L<>();
        expected.add("five");
        expected.add("four");
        SimpleReader inFile = new SimpleReader1L("linesFromInputTest1.txt");
        Set<String> result = StringReassembly.linesFromInput(inFile);
        assertEquals(expected, result);
    }

    /**
     * Test for both str is substring and element in set is substring condition.
     */
    @Test
    public void linesFromInputTest2() {
        Set<String> expected = new Set1L<>();
        expected.add("fiveAndfour");
        SimpleReader inFile = new SimpleReader1L("linesFromInputTest2.txt");
        Set<String> result = StringReassembly.linesFromInput(inFile);
        assertEquals(expected, result);
    }

    /*
     * Test for printWithLineSeparators method
     */

    /**
     * Test for one line change condition.
     */
    @Test
    public void printWithLineSeparatorsTest1() {
        SimpleWriter out = new SimpleWriter1L(
                "printWithLineSeparatorsTest1.txt");
        StringReassembly.printWithLineSeparators("a~b", out);
        SimpleReader in1 = new SimpleReader1L(
                "printWithLineSeparatorsTest1.txt");
        SimpleReader in2 = new SimpleReader1L(
                "printWithLineSeparatorsTest1Expected.txt");
        while (!in1.atEOS() || !in2.atEOS()) {
            assertEquals(in1.nextLine(), in2.nextLine());
        }
    }

    /**
     * Test for more than one line change condition.
     */
    @Test
    public void printWithLineSeparatorsTest2() {
        SimpleWriter out = new SimpleWriter1L(
                "printWithLineSeparatorsTest2.txt");
        StringReassembly.printWithLineSeparators("a~~~b", out);
        SimpleReader in1 = new SimpleReader1L(
                "printWithLineSeparatorsTest2.txt");
        SimpleReader in2 = new SimpleReader1L(
                "printWithLineSeparatorsTest2Expected.txt");
        while (!in1.atEOS() || !in2.atEOS()) {
            assertEquals(in1.nextLine(), in2.nextLine());
        }
    }
}
