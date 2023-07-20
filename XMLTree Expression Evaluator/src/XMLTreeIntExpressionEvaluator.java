import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Junyu Xue
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        //create all variables needed
        int finalValue = 0;
        int numOne = 0;
        int numTwo = 0;

        //if it is plus
        if (exp.label().equals("plus")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            if (exp.child(0).label().equals("number")) {
                numOne = Integer.parseInt(exp.child(0).attributeValue("value"));
            } else {
                numOne = evaluate(exp.child(0));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            if (exp.child(1).label().equals("number")) {
                numTwo = Integer.parseInt(exp.child(1).attributeValue("value"));
            } else {
                numTwo = evaluate(exp.child(1));
            }
            //calculate as numOne plus numTwo
            finalValue = numOne + numTwo;
        }
        //if it is minus
        if (exp.label().equals("minus")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            if (exp.child(0).label().equals("number")) {
                numOne = Integer.parseInt(exp.child(0).attributeValue("value"));
            } else {
                numOne = evaluate(exp.child(0));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            if (exp.child(1).label().equals("number")) {
                numTwo = Integer.parseInt(exp.child(1).attributeValue("value"));
            } else {
                numTwo = evaluate(exp.child(1));
            }
            //calculate as numOne minus numTwo
            finalValue = numOne - numTwo;
        }
        //if it is times
        if (exp.label().equals("times")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            if (exp.child(0).label().equals("number")) {
                numOne = Integer.parseInt(exp.child(0).attributeValue("value"));
            } else {
                numOne = evaluate(exp.child(0));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            if (exp.child(1).label().equals("number")) {
                numTwo = Integer.parseInt(exp.child(1).attributeValue("value"));
            } else {
                numTwo = evaluate(exp.child(1));
            }
            //calculate as numOne multiply numTwo
            finalValue = numOne * numTwo;
        }
        //if it is divide
        if (exp.label().equals("divide")) {
            //if it's child one has a number can be calculated, if no, recurse to get one
            if (exp.child(0).label().equals("number")) {
                numOne = Integer.parseInt(exp.child(0).attributeValue("value"));
            } else {
                numOne = evaluate(exp.child(0));
            }
            //if it's child two has a number can be calculated, if no, recurse to get one
            if (exp.child(1).label().equals("number")) {
                numTwo = Integer.parseInt(exp.child(1).attributeValue("value"));
            } else {
                numTwo = evaluate(exp.child(1));
            }
            //calculate as numOne divide by numTwo
            finalValue = numOne / numTwo;
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
