import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * To ask user to enter a double and calculate the square root.
 *
 * @author Junyu Xue
 *
 */
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error.
     *
     * @param x
     *            positive number to compute square root of
     * @param y
     *            the error typed in by user
     * @return estimate of square root
     */
    private static double sqrt(double x, double y) {
        double r = x;
        //test if double that user entered is zero.
        if (r != 0.0) {
            //the loop used to update x until the square root is within the error.
            while (true) {
                //update the square root value.
                r = (r + (x / r)) / 2;
                //test if it is within the error
                if ((Math.abs(Math.pow(r, 2) - x) / x) < y * y) {
                    break;
                }
            }
        } else {
            //if it is zero, then return zero.
            r = 0;
        }

        return r;
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
        //ask user to enter an error.
        out.print("Enter an error: ");
        double error1 = in.nextDouble();
        //ask user to enter a double.
        out.print("Enter a double: ");
        double double1 = in.nextDouble();
        //use the sqrt() method to calculate the square root
        double sqrt1 = sqrt(double1, error1);
        out.println("The squre root is: " + sqrt1);
        //the loop used to keep asking if user need to calculate another one.
        while (true) {
            // ask user to enter y or anything else.
            out.print("Do you want to calculate another one? ");
            String answer = in.nextLine();
            //test if user enter "y"
            if (answer.equals("y")) {
                //if answer is "y", the program proceed
                out.print("Enter a double: ");
                double double2 = in.nextDouble();
                double sqrt2 = sqrt(double2, error1);
                out.println("The squre root is: " + sqrt2);
            } else {
                //if answer is anything else, then quit
                break;
            }
        }
        out.print("All finish, bye.");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
