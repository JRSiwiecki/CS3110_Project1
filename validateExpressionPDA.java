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
        Stack<Character> parentheses = new Stack<Character>();
        validity = startState(input, count, parentheses);

        if (validity == 1 && parentheses.isEmpty()) 
        {
            System.out.println("Input accepted.");
            System.out.println("Result: " + EvaluateExpression.evaluateExpression(input));
            return true;
        }

        else if (validity == 0 || !parentheses.isEmpty()) 
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
                return openParenthesisState(input, count + 1, stack);

            default:
                return invalidState(input, count + 1, stack);
        }
    }

    // non final-state
    private static int wholeNumberState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length())
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;    
    
        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = wholeNumberState(input, count + 1, stack);
                break;
                
            case '_':
                result = underscoreWholeNumberState(input, count + 1, stack);
                break;

            case '.':
                result = decimalWholeNumberState(input, count + 1, stack);
                break;
            
            case 'e': case 'E':
                result = exponentState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;
            
            case '+': case '-':
                result = invalidState(input, count + 1, stack);
                break;

            default: 
                result = invalidState(input, count + 1, stack);
                break;
        }      
        
        return result;
    }

    // non-final state
    private static int invalidState(String input, int count, Stack<Character> stack)
    {
        // any input here returns 0
        return 0;
    }

    // non-final state
    private static int decimalNoWholeNumberState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionState(input, count + 1, stack);
                break;
            
            case '+': case '-': case '_': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D': case '.':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreWholeNumberState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = wholeNumberState(input, count + 1, stack);
                break;

            case '+': case '-':
                result = invalidState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreWholeNumberState(input, count + 1, stack);
                break;

            case '.': case 'e': case 'E': case 'd': case 'D':
            case 'f': case 'F':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }
        
        return result;
    }

    // final state
    private static int decimalWholeNumberState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 1;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionWithWholeState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;

            case '+': case '-': case '_': case 'e': case 'E':
            case '.':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // non-final state
    private static int exponentState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
            case '-': case '+':
                result = exponentNumberState(input, count + 1, stack);
                break;

            case '.': case 'e': case 'E': case 'f': case 'F': 
            case 'd': case 'D':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // final state
    private static int suffixState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 1;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = invalidState(input, count + 1, stack);
                break;

            case '+': case '-': case '*': case '/':
                result = operatorState(input, count + 1, stack);
                break;

            case ')':
                result = closeParenthesisState(input, count + 1, stack);
                break;

            case '(':
                result = openParenthesisState(input, count + 1, stack);
                break;
            
            case '.':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // final state
    private static int fractionState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 1;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionState(input, count + 1, stack);
                break;

            case '+': case '-': case '.':
                result = invalidState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreFractionState(input, count + 1, stack);
                break;

            case 'e': case 'E':
                result = exponentState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // final state
    private static int fractionWithWholeState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 1;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionWithWholeState(input, count + 1, stack);
                break;

            case '+': case '-': case '.':
                result = invalidState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreWholeFraction(input, count + 1, stack);
                break;

            case 'e': case 'E':
                result = exponentState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;

            case '(':
                result = openParenthesisState(input, count + 1, stack);
                break;
            
            case ')':
                result = closeParenthesisState(input, count + 1, stack);
                break;
            
            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // final state
    private static int exponentNumberState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 1;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = exponentNumberState(input, count + 1, stack);
                break;

            case '+': case '-': case '.': case 'e': case 'E':
                result = invalidState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreExponent(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreFractionState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreFractionState(input, count + 1, stack);
                break;
            
            case '+': case '-': case '.': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // non-final state
    private static int underscoreWholeFraction(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c) 
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionWithWholeState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreWholeFraction(input, count + 1, stack);
                break;

            case '+': case '-': case '.': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreExponent(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = exponentNumberState(input, count + 1, stack);
                break;

            case '_':
                result = underscoreExponent(input, count + 1, stack);
                break;

            case '+': case '-': case '.': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // non-final state
    private static int openParenthesisState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        stack.push(c);

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = wholeNumberState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D': case '+': 
            case '-': case '_': case 'e': case 'E':
                result = invalidState(input, count + 1, stack);
                break;

            case '.':
                result = decimalNoWholeNumberState(input, count + 1, stack);
                break;
            
            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // final state
    private static int closeParenthesisState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            stack.pop();
            return 1;
        }
        
        char c = input.charAt(count);
        int result = -1;

        stack.pop();

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionWithWholeState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;

            case '+': case '-': case '_': case 'e': case 'E':
            case '.':
                result = invalidState(input, count + 1, stack);
                break;

            case '(':
                result = openParenthesisState(input, count + 1, stack);
                break;
            
            case ')':
                result = closeParenthesisState(input, count + 1, stack);
                break;
            
            case ' ':
                result = whiteSpaceState(input, count + 1, stack);
                break;
            
            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // non-final state
    private static int whiteSpaceState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        stack.push(c);

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = fractionWithWholeState(input, count + 1, stack);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1, stack);
                break;

            case '+': case '-': case '_': case 'e': case 'E':
            case '.':
                result = invalidState(input, count + 1, stack);
                break;

            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }

    // non-final state
    private static int operatorState(String input, int count, Stack<Character> stack)
    {
        if (count >= input.length()) 
        {
            return 0;
        }
        
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                result = wholeNumberState(input, count + 1, stack);
                break;

            case '_':
                result = invalidState(input, count + 1, stack);
                break;
            
            case '+': case '-': case 'e': case 'E': case '*':
            case '/': case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1, stack);
                break;

            case '.':
                result = fractionState(input, count + 1, stack);
                break;
            
            default:
                return invalidState(input, count + 1, stack);
        }

        return result;
    }
}
