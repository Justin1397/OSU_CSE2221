import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Asking user to enter 4 personal numbers and a constant μ. Then use de Jager
 * formula to calculate the exponents that minimizes the error of the
 * approximation of μ. Then, print the exponents, best approximation, and
 * corresponding relative error.
 *
 * @author Junyu Xue
 *
 */
public final class ABCDGuesser2 {
    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {

    }

    /**
     * This can help to transfer a value into percentage.
     */
    static final double PERCENT = 100;
    /**
     * This is the array that storing the value of a,b,c,d.
     */
    static final double[] ABCD_ARRAY = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
            -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.print("Enter a positive real number: ");
        String userInput = in.nextLine();
        //First check if it is a real number. If not, keep asking until get one.
        while (!FormatChecker.canParseDouble(userInput)) {
            out.print("Enter a positive real number: ");
            userInput = in.nextLine();

        }
        double userNum = Double.parseDouble(userInput);
        //Check if it is a positive number. If not, keep asking until get one.
        while (userNum <= 0) {
            out.print("Enter a positive real number: ");
            String userInput2 = in.nextLine();
            //test if it is real number
            while (!FormatChecker.canParseDouble(userInput2)) {
                out.print("Enter a positive real number: ");
                userInput2 = in.nextLine();

            }
            userNum = Double.parseDouble(userInput2);
        }
        return userNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.print("Enter a positive real number(not one): ");
        String userInput = in.nextLine();
        //First check if it is a real number. If not, keep asking until get one.
        while (!FormatChecker.canParseDouble(userInput)) {
            out.print("Enter a positive real number(not one): ");
            userInput = in.nextLine();

        }
        double userNum = Double.parseDouble(userInput);
        //Check if it is a positive number and is not one.If not,keep asking.
        while (userNum <= 0 || userNum == 1.0) {
            out.print("Enter a positive real number(not one): ");
            String userInput2 = in.nextLine();
            //test if it is real number
            while (!FormatChecker.canParseDouble(userInput2)) {
                out.print("Enter a positive real number(not one): ");
                userInput2 = in.nextLine();

            }
            userNum = Double.parseDouble(userInput2);
        }
        return userNum;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //Asking user for the value of a constant want to test.
        out.println("Enter a value of μ");
        double constant = getPositiveDouble(in, out);
        //Asking user for 4 personal number which should be positive not one.
        out.println("Enter 4 personal number");
        out.println("Enter 1st personal number");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("Enter 2nd personal number");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("Enter 3rd personal number");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("Enter 4th personal number");
        double z = getPositiveDoubleNotOne(in, out);

        //Create an double array to store all the possible values of a,b,c,d.
        double[] abcdNum = ABCD_ARRAY;
        //Create a String array to store all the possible values of a,b,c,d.
        String[] abcdString = { "-5", "-4", "-3", "-2", "-1", "-1/2", "-1/3",
                "-1/4", "0", "1/4", "1/3", "1/2", "1", "2", "3", "4", "5" };
        //Initialize the values needed.
        //used to store the values of the position in array of a,b,c,d
        int a = 0, b = 0, c = 0, d = 0;
        //used to store the values of a,b,c,d to print out at end.
        String aNum = abcdString[a];
        String bNum = abcdString[b];
        String cNum = abcdString[c];
        String dNum = abcdString[d];
        //used to store the value and print out at end.
        double finalNum = Math.pow(w, abcdNum[a]) * Math.pow(x, abcdNum[b])
                * Math.pow(y, abcdNum[c]) * Math.pow(z, abcdNum[d]);
        //The loops that go over all possible values to find the closest estimate value.
        for (a = 0; a < abcdNum.length; a++) {
            for (b = 0; b < abcdNum.length; b++) {
                for (c = 0; c < abcdNum.length; c++) {
                    for (d = 0; d < abcdNum.length; d++) {
                        //calculate the value of estimate value
                        double estimateNum = Math.pow(w, abcdNum[a])
                                * Math.pow(x, abcdNum[b])
                                * Math.pow(y, abcdNum[c])
                                * Math.pow(z, abcdNum[d]);
                        //if the estimate value is closer,then replace final value with it
                        if (Math.abs(estimateNum - constant) < Math
                                .abs(finalNum - constant)) {
                            //replace the value
                            finalNum = estimateNum;
                            //take note of what is a,b,c,d
                            aNum = abcdString[a];
                            bNum = abcdString[b];
                            cNum = abcdString[c];
                            dNum = abcdString[d];
                        }
                    }
                    d = 0;
                }
                c = 0;
            }
            b = 0;
        }
        //print out a,b,c,d
        out.println("The a is " + aNum);
        out.println("The b is " + bNum);
        out.println("The c is " + cNum);
        out.println("The d is " + dNum);
        //print out the best approximation
        out.print("This is the best approximation: ");
        out.println(finalNum, 2, false);
        //print out the relative error
        out.print("This is the corresponding relative error: ");
        out.print(Math.abs((finalNum - constant) / constant) * PERCENT, 2,
                false);
        out.println("%");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
