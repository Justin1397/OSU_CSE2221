import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * Test for the Glossary's methods.
 *
 * @author Junyu Xue
 *
 */
public class GlossaryTest {

    /*
     * Test for processString
     */
    /**
     * Test for the common condition that the string is in one word with one
     * line of definition.
     *
     */
    @Test
    public void processStringTest1() {
        SimpleReader input = new SimpleReader1L("processStringTest1.txt");
        Map<String, String> wordsWithDefine = new Map1L<String, String>();
        Queue<String> words = new Queue1L<String>();
        Set<String> linkCheck = new Set1L<String>();
        Glossary.processString(input, wordsWithDefine, words, linkCheck);
        Map<String, String> wordsWithDefineExpect = new Map1L<String, String>();
        wordsWithDefineExpect.add("ABC", "ABC is three simple letters");
        Queue<String> wordsExpect = new Queue1L<String>();
        wordsExpect.enqueue("ABC");
        Set<String> linkCheckExpect = new Set1L<String>();
        linkCheckExpect.add("ABC");
        assertEquals(wordsWithDefine, wordsWithDefineExpect);
        assertEquals(words, wordsExpect);
        assertEquals(linkCheck, linkCheckExpect);

        input.close();
    }

    /**
     * Test for the special condition that the string is in one word with two
     * lines of definition.
     *
     */
    @Test
    public void processStringTest2() {
        SimpleReader input = new SimpleReader1L("processStringTest2.txt");
        Map<String, String> wordsWithDefine = new Map1L<String, String>();
        Queue<String> words = new Queue1L<String>();
        Set<String> linkCheck = new Set1L<String>();
        Glossary.processString(input, wordsWithDefine, words, linkCheck);
        Map<String, String> wordsWithDefineExpect = new Map1L<String, String>();
        wordsWithDefineExpect.add("ABC",
                "They are letters, and they are connected");
        Queue<String> wordsExpect = new Queue1L<String>();
        wordsExpect.enqueue("ABC");
        Set<String> linkCheckExpect = new Set1L<String>();
        linkCheckExpect.add("ABC");
        assertEquals(wordsWithDefine, wordsWithDefineExpect);
        assertEquals(words, wordsExpect);
        assertEquals(linkCheck, linkCheckExpect);

        input.close();
    }

    /*
     * Test for generateElements.
     */
    /**
     * Test for common condition for ' ' and ','.
     *
     */
    @Test
    public void generateElementsTest1() {
        Set<Character> separatorSet = new Set1L<>();
        String separatorStr = " ,";
        Glossary.generateElements(separatorStr, separatorSet);
        Set<Character> separatorSetExpected = new Set1L<>();
        separatorSetExpected.add(' ');
        separatorSetExpected.add(',');
        assertEquals(separatorSet, separatorSetExpected);
    }

    /**
     * Test for special condition for '\n' and other marks.
     *
     */
    @Test
    public void generateElementsTest2() {
        Set<Character> separatorSet = new Set1L<>();
        String separatorStr = "\n!?";
        Glossary.generateElements(separatorStr, separatorSet);
        Set<Character> separatorSetExpected = new Set1L<>();
        separatorSetExpected.add('\n');
        separatorSetExpected.add('!');
        separatorSetExpected.add('?');
        assertEquals(separatorSet, separatorSetExpected);
    }

    /**
     * Test for special condition for there is two or more same elements.
     *
     */
    @Test
    public void generateElementsTest3() {
        Set<Character> separatorSet = new Set1L<>();
        String separatorStr = "   ,";
        Glossary.generateElements(separatorStr, separatorSet);
        Set<Character> separatorSetExpected = new Set1L<>();
        separatorSetExpected.add(',');
        separatorSetExpected.add(' ');
        assertEquals(separatorSet, separatorSetExpected);
    }

    /*
     * Test for nextWordOrSeparator
     */
    /**
     * Test for the common condition for getting a word.
     *
     */
    @Test
    public void nextWordOrSeparatorTest1() {
        String text = "Please separat this sentence";
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        String result = Glossary.nextWordOrSeparator(text, 0, separators);
        String resultExpect = "Please";
        assertEquals(result, resultExpect);
    }

    /**
     * Test for the common condition for getting a separator.
     *
     */
    @Test
    public void nextWordOrSeparatorTest2() {
        String text = "Please separat this sentence";
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        final int six = 6;
        String result = Glossary.nextWordOrSeparator(text, six, separators);
        String resultExpect = " ";
        assertEquals(result, resultExpect);
    }

    /**
     * Test for the special condition for getting double separators.
     *
     */
    @Test
    public void nextWordOrSeparatorTest3() {
        String text = "Please, separat this sentence";
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add(',');
        final int six = 6;
        String result = Glossary.nextWordOrSeparator(text, six, separators);
        String resultExpect = ", ";
        assertEquals(result, resultExpect);
    }

}
