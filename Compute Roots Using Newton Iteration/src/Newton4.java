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
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
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
        //loop used to keep asking user to enter a double
        while (true) {
            //ask user to enter a double
            out.print("Enter a double: ");
            double double1 = in.nextDouble();
            //test if user enter a negative double
            if (double1 < 0) {
                //if it is negative, then quit
                break;
            }
            //if it is positive, the program proceed
            double sqrt1 = sqrt(double1, error1);
            out.println("The squre root is: " + sqrt1);
        }
        out.print("All finish, bye.");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
