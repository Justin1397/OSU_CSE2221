import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The Glossary code to make terms and definition into a HTML files folder.
 *
 * @author Junyu Xue
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
        // no code needed here
    }

    /**
     * Output the html for the main page.
     *
     * @param words
     *            the queue of terms in the given file
     *
     * @param wordsWithDefine
     *            the map with terms with their corresponding define
     *
     * @param outputFolder
     *            the folder that all html file stored
     *
     * @param linkCheck
     *            the set of words to test for link needed
     *
     */
    public static void outputMain(Queue<String> words,
            Map<String, String> wordsWithDefine, String outputFolder,
            Set<String> linkCheck) {
        //create the file
        String fileName = outputFolder + "/index.html";
        SimpleWriter indexPage = new SimpleWriter1L(fileName);

        //print the format of the main page
        indexPage.println("<html>");
        indexPage.println("<head>");
        indexPage.println("<title>Sample Glossary</title>");
        indexPage.println("</head>");
        indexPage.println("<body>");
        indexPage.println("<h2>Sample Glossary</h2>");
        indexPage.println("<hr />");
        indexPage.println("<h3>Index</h3>");

        //print the words' links part of the main page
        indexPage.println("<ul>");
        while (words.length() > 0) {
            //get the word
            String word = words.dequeue();
            //output the html file for term's definition
            outputSub(word, wordsWithDefine, outputFolder, linkCheck);
            indexPage.print("<li>");
            indexPage.print("<a href=\"" + word + ".html\">");
            indexPage.println(word + "</a></li>");
        }
        indexPage.println("</ul>");
        indexPage.println("</body>");
        indexPage.println("</html>");

        indexPage.close();
    }

    /**
     * Output the html for the word's page.
     *
     * @param word
     *            the term to create the html file
     *
     * @param wordsWithDefine
     *            the map with terms with their corresponding define
     *
     * @param outputFolder
     *            the folder that all html file stored
     *
     * @param linkCheck
     *            the set of words to test for link needed
     *
     */
    public static void outputSub(String word,
            Map<String, String> wordsWithDefine, String outputFolder,
            Set<String> linkCheck) {
        //create the file
        String fileName = outputFolder + "/" + word + ".html";
        SimpleWriter wordPage = new SimpleWriter1L(fileName);

        //print the format of the word page
        wordPage.println("<html>");
        wordPage.println("<head>");
        wordPage.println("<title>" + word + "</title>");
        wordPage.println("</head>");
        wordPage.println("<body>");
        wordPage.println("<h2><b><i><font color=\"red\">" + word
                + "</font></i></b></h2>");
        //get the define from the map
        String define = wordsWithDefine.value(word);

        //test if there is other words need to have link in define
        String separatorStr = " ,.!?";
        Set<Character> separatorSet = new Set1L<>();
        //get the separators into a set
        generateElements(separatorStr, separatorSet);
        String actualDefine = "";
        int position = 0;
        //go through each word and separator in the define
        while (position < define.length()) {
            //get the next word or separator
            String token = nextWordOrSeparator(define, position, separatorSet);
            if (separatorSet.contains(token.charAt(0))) {
                //if it is a separator, just add it
                actualDefine += token;
            } else {
                //if it is a word, test if it is also another word with define link
                Set<String> temp = linkCheck.newInstance();
                temp.transferFrom(linkCheck);
                int count = 0;
                //go through the words stored in linkCheck
                while (temp.size() > 0) {
                    String x = temp.removeAny();
                    //if word is the same as the word with link, add it with the link
                    if (token.equals(x)) {
                        actualDefine += "<a href=\"" + x + ".html\">" + token
                                + "</a>";
                        count++;
                    }
                    //restore the linkCheck for next test
                    linkCheck.add(x);
                }
                //if the word don't need link, then just add it
                if (count == 0) {
                    actualDefine += token;
                }
            }
            //change the position to test for next word or separator
            position += token.length();
        }

        //print the format of the word page
        wordPage.println("<blockquote>" + actualDefine + "</blockquote>");
        wordPage.println("<hr />");
        wordPage.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        wordPage.println("</body>");
        wordPage.println("</html>");

        wordPage.close();
    }

    /**
     * Process one pair of word and define in the file given by the user and
     * store them.
     *
     * @param input
     *            the input file which user gives
     *
     * @param wordsWithDefine
     *            the map with terms with their corresponding define
     *
     * @param words
     *            the queue of terms in the given file
     * @param linkCheck
     *            the set of words to test for link needed
     *
     */
    public static void processString(SimpleReader input,
            Map<String, String> wordsWithDefine, Queue<String> words,
            Set<String> linkCheck) {
        String word = input.nextLine();
        String define = "";
        String next = input.nextLine();
        //test if there is next line for definition
        while (!next.equals("")) {
            define += next;
            next = input.nextLine();
            //if the line after next line also has information add a " "
            if (!next.equals("")) {
                define += " ";
            }
        }
        //store the words and define in map and queue and set for future using
        wordsWithDefine.add(word, define);
        words.enqueue(word);
        linkCheck.add(word);

    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        charSet.clear();
        //go through the str
        for (int i = 0; i < str.length(); i++) {
            //test if set already has the element
            if (!charSet.contains(str.charAt(i))) {
                //if not, add it
                charSet.add(str.charAt(i));
            }
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int i = position;
        String words = "";
        //to test if it is separator
        if (separators.contains(text.charAt(i))) {
            //if yes, get next until the next is not a separator
            while (i < text.length() && separators.contains(text.charAt(i))) {
                words = words + text.charAt(i);
                i++;

            }
        } else {
            //if no, get next until the next is a separator
            while (i < text.length() && !separators.contains(text.charAt(i))) {
                words = words + text.charAt(i);
                i++;
            }
        }

        return words;
    }

    /**
     * Compare {@code String}s in lexicographic order.
     *
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        //ask user to get the input and output source
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        out.print("Enter the input file name: ");
        String inputFile = in.nextLine();
        out.print("Enter the output file folder name: ");
        String outputFolder = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFile);
        //the map used to store words with corresponding define
        Map<String, String> wordsWithDefine = new Map1L<String, String>();
        //the queue used to work as keys to get the value in map
        Queue<String> words = new Queue1L<String>();
        //the set used to work as test if the word has a link with it
        Set<String> linkCheck = new Set1L<String>();
        //process the given file
        while (!input.atEOS()) {
            processString(input, wordsWithDefine, words, linkCheck);
        }

        //change the order of the queue to make sure it is in alphabetical order
        Comparator<String> order = new StringLT();
        words.sort(order);

        //output the main page: index.html
        outputMain(words, wordsWithDefine, outputFolder, linkCheck);

        out.close();
        in.close();
        input.close();
    }

}
