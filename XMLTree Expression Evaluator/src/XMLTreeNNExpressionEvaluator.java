import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Junyu Xue
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        //create all NaturalNumbers needed
        NaturalNumber finalValue = new NaturalNumber2(0);
        NaturalNumber numOne = new NaturalNumber2(0);
        NaturalNumber numTwo = new NaturalNumber2(0);

        //if it is plus
        if (exp.label().equals("plus")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            //store the value to numOne
            if (exp.child(0).label().equals("number")) {
                NaturalNumber valueOne = new NaturalNumber2(
                        exp.child(0).attributeValue("value"));
                numOne.copyFrom(valueOne);
            } else {
                numOne.copyFrom(evaluate(exp.child(0)));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            //store the value to numTwo
            if (exp.child(1).label().equals("number")) {
                NaturalNumber valueTwo = new NaturalNumber2(
                        exp.child(1).attributeValue("value"));
                numTwo.copyFrom(valueTwo);
            } else {
                numTwo.copyFrom(evaluate(exp.child(1)));
            }
            //calculate as numOne add numTwo
            numOne.add(numTwo);
            finalValue.copyFrom(numOne);

        }
        //if it is minus
        if (exp.label().equals("minus")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            //store the value to numOne
            if (exp.child(0).label().equals("number")) {
                NaturalNumber valueOne = new NaturalNumber2(
                        exp.child(0).attributeValue("value"));
                numOne.copyFrom(valueOne);

            } else {
                numOne.copyFrom(evaluate(exp.child(0)));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            //store the value to numtwo
            if (exp.child(1).label().equals("number")) {
                NaturalNumber valueTwo = new NaturalNumber2(
                        exp.child(1).attributeValue("value"));
                numTwo.copyFrom(valueTwo);
            } else {
                numTwo.copyFrom(evaluate(exp.child(1)));
            }
            //test if numTwo is larger than numOne because NaturalNumber is non-negative
            if (numOne.compareTo(numTwo) < 0) {
                Reporter.fatalErrorToConsole("NaturalNumber can't be negative");
            }
            //calculate as numOne minus numTwo
            numOne.subtract(numTwo);
            finalValue.copyFrom(numOne);
        }
        //if it is times
        if (exp.label().equals("times")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            //store the value to numOne
            if (exp.child(0).label().equals("number")) {
                NaturalNumber valueOne = new NaturalNumber2(
                        exp.child(0).attributeValue("value"));
                numOne.copyFrom(valueOne);

            } else {
                numOne.copyFrom(evaluate(exp.child(0)));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            //store the value to numTwo
            if (exp.child(1).label().equals("number")) {
                NaturalNumber valueTwo = new NaturalNumber2(
                        exp.child(1).attributeValue("value"));
                numTwo.copyFrom(valueTwo);
            } else {
                numTwo.copyFrom(evaluate(exp.child(1)));
            }
            //calculate as numOne multiply numTwo
            numOne.multiply(numTwo);
            finalValue.copyFrom(numOne);
        }
        //if it is divide
        if (exp.label().equals("divide")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            //store the value to numOne
            if (exp.child(0).label().equals("number")) {
                NaturalNumber valueOne = new NaturalNumber2(
                        exp.child(0).attributeValue("value"));
                numOne.copyFrom(valueOne);

            } else {
                numOne.copyFrom(evaluate(exp.child(0)));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            //store the value to numTwo
            if (exp.child(1).label().equals("number")) {
                NaturalNumber valueTwo = new NaturalNumber2(
                        exp.child(1).attributeValue("value"));
                numTwo.copyFrom(valueTwo);
            } else {
                numTwo.copyFrom(evaluate(exp.child(1)));
            }
            //test if numTwo is zero because number can't divide by zero
            if (numTwo.isZero()) {
                Reporter.fatalErrorToConsole("Can't divide by zero");
            }
            //calculate as numOne divide by numTwo
            numOne.divide(numTwo);
            finalValue.copyFrom(numOne);
        }

        return finalValue;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
