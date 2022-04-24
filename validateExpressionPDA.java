public class ValidateExpressionPDA 
{
    // use PDA to check if expression is valid, if it is then we will calculate the expression
    // stack will be used to make sure parentheses are valid
    // PDA will then determine from each point if the expression is valid from any point
    // how will the program be able to tell when a number is over? use the DFA from validateStringDFA
    // to check? if it's valid then move on, if it's invalid then the expression itself must be invalid?
    // if expression is valid then use method from calculateExpression.java

    // will likely have to modify how input is received, must be changed from project 1 since we 
    // are taking on a whole expression and not just a string of a float

    public static boolean validateExpression(String input)
    {
        int validity = -1;
        int count = 0;

        // if 1 is returned, input is accepted.
        // if 0 is returned, input is rejected.
        // call start state similar to the DFA
        // filler code for now
        validity = -1;

        if (validity == 1) {
            System.out.println("Input accepted.");
            System.out.println("Result: " + EvaluateExpression.evaluateExpression(input));
            return true;
        }

        else if (validity == 0) {
            System.out.println("Input rejected.");
            return false;
        }

        else {
            System.out.println("ERROR: This should not have been reached.");
            return false;
        }
    }
}
