import java.util.Stack;

public class ValidateExpressionPDA 
{
    // use PDA to check if expression is valid, if it is then we will calculate the expression
    // stack will be used to make sure parentheses are valid
    // PDA will then determine from each point if the expression is valid from any point
    // how will the program be able to tell when a number is over? use the DFA from validateStringDFA
    // to check? if it's valid then move on, if it's invalid then the expression itself must be invalid?
    // if expression is valid then use method from EvaluateExpression.java

    // will likely have to modify how input is received, must be changed from project 1 since we 
    // are taking on a whole string expression and not just a string of a float

    public static boolean validateExpression(String input)
    {
        int validity = -1;
        int count = 0;

        // if 1 is returned, input is accepted.
        // if 0 is returned, input is rejected.
        // call start state similar to the DFA
        // filler code for now
        validity = -1;

        if (validity == 1) 
        {
            System.out.println("Input accepted.");
            System.out.println("Result: " + EvaluateExpression.evaluateExpression(input));
            return true;
        }

        else if (validity == 0) 
        {
            System.out.println("Input rejected.");
            return false;
        }

        else 
        {
            System.out.println("ERROR: This should not have been reached.");
            return false;
        }
    }

    private static int startState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }

        char c = input.charAt(count);

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                return wholeNumberState(input, count + 1, stack);

            case '+': case '-': case '_': case 'e': case 'E': case 'f':
            case 'F': case 'd': case 'D': case '*': case '/': case ')':
                return invalidState(input, count + 1, stack);

            case '.':
                return decimalNoWholeNumberState(input, count + 1, stack);

            case '(':
                return parenthesisState(input, count + 1, stack);

            default:
                return invalidState(input, count + 1);
        }
    }
}
