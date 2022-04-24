// Name: Joseph Siwiecki
// Class: CS 3110.02
// Professor: Qichao Dong
// Project 1
// Date: 4/13/2022
// Due Date: 4/13/2022
// File Name: validateStringDFA.java
// Description: Uses a DFA to validate string input by checking if it is a valid floating-point literal.  
//      Accepts: 
//                  Digits . [Digits] [ExponentPart] [FloatTypeSuffix]
//                  . Digits [ExponentPart] [FloatTypeSuffix]
//                  Digits ExponentPart [FloatTypeSuffix]
//                  Digits [ExponentPart] FloatTypeSuffix
//                      ** Underscores are allowed as separators 
//                         between digits that denote the whole-number part, and between digits 
//                         that denote the fraction part, and between digits that denote the exponent.
//                      ** If we receive any input symbols that is not part of the alphabet, the input is rejected. 

public class ValidateStringDFA 
{ 
    public static boolean validateInput(String input) 
    {
        int validity = 0;
        int count = 0;

        // if 1 is returned, input is accepted. 
        // if 0 is returned, input is rejected.
        validity = startState(input, count);

        if (validity == 1)
        {
            System.out.println("Input accepted.");
            System.out.println("Float: " + StringToFloat.convertStringToFloat(input));
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

    // input validation is done through method calls and recursively going through specific paths.
    // methods represent one state, each method takes in the input string
    // and a count to keep track of where we are in the string.
    // count is increased by one each time we transition to another state.
    // switch statements are used to represent transitions for each state.
    // only the startState method needs to return the final value since it's the one we begin on
    // and recursion will only return the value for the first method that was called.
    // if we reach the end of the input string when we first get to a method, then
    // we return 0 from that method if the state is a non-final state, and if
    // it's a final state, we return 1.
    
    // non-final state
    private static int startState(String input, int count)
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
                return wholeNumberState(input, count + 1);
                
            case '+': case '-': case '_': case 'e': case 'E': case 'f':
            case 'F': case 'd': case 'D':
                return invalidState(input, count + 1);
                
            case '.':
                return decimalNoWholeNumberState(input, count + 1);

            default:
                return invalidState(input, count + 1);
        }
    }

    // non final-state
    private static int wholeNumberState(String input, int count)
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
                result = wholeNumberState(input, count + 1);
                break;
                
            case '_':
                result = underscoreWholeNumberState(input, count + 1);
                break;

            case '.':
                result = decimalWholeNumberState(input, count + 1);
                break;
            
            case 'e': case 'E':
                result = exponentState(input, count + 1);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1);
                break;
            
            case '+': case '-':
                result = invalidState(input, count + 1);
                break;

            default: 
                result = invalidState(input, count + 1);
                break;
        }      
        
        return result;
    }

    // non-final state
    private static int invalidState(String input, int count)
    {
        // any input here returns 0
        return 0;
    }

    // non-final state
    private static int decimalNoWholeNumberState(String input, int count)
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
                result = fractionState(input, count + 1);
                break;
            
            case '+': case '-': case '_': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D': case '.':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreWholeNumberState(String input, int count)
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
                result = wholeNumberState(input, count + 1);
                break;

            case '+': case '-':
                result = invalidState(input, count + 1);
                break;

            case '_':
                result = underscoreWholeNumberState(input, count + 1);
                break;

            case '.': case 'e': case 'E': case 'd': case 'D':
            case 'f': case 'F':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }
        
        return result;
    }

    // final state
    private static int decimalWholeNumberState(String input, int count)
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
                result = fractionWithWholeState(input, count + 1);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1);
                break;

            case '+': case '-': case '_': case 'e': case 'E':
            case '.':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }

        return result;
    }

    // non-final state
    private static int exponentState(String input, int count)
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
                result = exponentNumberState(input, count + 1);
                break;

            case '.': case 'e': case 'E': case 'f': case 'F': 
            case 'd': case 'D':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }

        return result;
    }

    // final state
    private static int suffixState(String input, int count)
    {
        if (count >= input.length()) 
        {
            return 1;
        }
        
        else
        {
            return 0;
        }
    }

    // final state
    private static int fractionState(String input, int count)
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
                result = fractionState(input, count + 1);
                break;

            case '+': case '-': case '.':
                result = invalidState(input, count + 1);
                break;

            case '_':
                result = underscoreFractionState(input, count + 1);
                break;

            case 'e': case 'E':
                result = exponentState(input, count + 1);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }

        return result;
    }

    // final state
    private static int fractionWithWholeState(String input, int count)
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
                result = fractionWithWholeState(input, count + 1);
                break;

            case '+': case '-': case '.':
                result = invalidState(input, count + 1);
                break;

            case '_':
                result = underscoreWholeFraction(input, count + 1);
                break;

            case 'e': case 'E':
                result = exponentState(input, count + 1);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }

        return result;
    }

    // final state
    private static int exponentNumberState(String input, int count)
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
                result = exponentNumberState(input, count + 1);
                break;

            case '+': case '-': case '.': case 'e': case 'E':
                result = invalidState(input, count + 1);
                break;

            case '_':
                result = underscoreExponent(input, count + 1);
                break;

            case 'f': case 'F': case 'd': case 'D':
                result = suffixState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreFractionState(String input, int count)
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
                result = fractionState(input, count + 1);
                break;

            case '_':
                result = underscoreFractionState(input, count + 1);
                break;
            
            case '+': case '-': case '.': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }

        return result;
    }

    // non-final state
    private static int underscoreWholeFraction(String input, int count)
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
                result = fractionWithWholeState(input, count + 1);
                break;

            case '_':
                result = underscoreWholeFraction(input, count + 1);
                break;

            case '+': case '-': case '.': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreExponent(String input, int count)
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
                result = exponentNumberState(input, count + 1);
                break;

            case '_':
                result = underscoreExponent(input, count + 1);
                break;

            case '+': case '-': case '.': case 'e': case 'E':
            case 'f': case 'F': case 'd': case 'D':
                result = invalidState(input, count + 1);
                break;

            default:
                return invalidState(input, count + 1);
        }

        return result;
    }
}
