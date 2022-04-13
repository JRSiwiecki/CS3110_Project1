import java.util.Scanner;

public class StringToFloatDFA 
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

    public static String receiveInput() 
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter a float (Enter 'q' to quit): ");

        String userInput = in.nextLine();

        if (userInput == "q" || userInput == "Q") 
        {
            System.exit(0);
        }

        in.close();

        return userInput;
    }
    
    public static void main(String[] args) 
    {
        while (true) 
        {
            String input = receiveInput();
            validateInput(input);
        }
    }

    // non-final state
    private static int startState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;
        
        switch (c)
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result = wholeNumberState(input, count + 1);
                
            case '+':
            case '-':
            case '_':
            case 'e':
            case 'E':
            case 'f':
            case 'F':
            case 'd':
            case 'D':
                result = invalidState(input, count + 1);
                
            case '.':
                result = decimalNoWholeNumberState(input, count + 1);
        }

        return result;
    }

    // non final-state
    private static int wholeNumberState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;    
    
        switch (c)
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result = wholeNumberState(input, count + 1);
                
            case '_':
                result = underscoreWholeNumberState(input, count + 1);
            
            case '.':
                result = decimalWholeNumberState(input, count + 1);
            
            case 'e':
            case 'E':
                result = exponentState(input, count + 1);

            case 'f':
            case 'F':
            case 'd':
            case 'D':
                result = suffixState(input, count + 1);
            
            case '+':
            case '-':
                result = invalidState(input, count + 1);
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
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result = fractionState(input, count + 1);
            
            case '+':
            case '-':
            case '_':
            case 'e':
            case 'E':
            case 'f':
            case 'F':
            case 'd':
            case 'D':
            case '.':
                result = invalidState(input, count + 1);
        }
        
        return result;
    }

    // non-final state
    private static int underscoreWholeNumberState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        switch (c)
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result = wholeNumberState(input, count + 1);

            case '+':
            case '-':
                result = invalidState(input, count + 1);

            case '_':
                result = underscoreWholeNumberState(input, count + 1);

            case '.':
            case 'e':
            case 'E':
            case 'd':
            case 'D':
            case 'f':
            case 'F':
                result = invalidState(input, count + 1);
        }
        
        return result;
    }

    // final state
    private static int decimalWholeNumberState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // non-final state
    private static int exponentState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // final state
    private static int suffixState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // final state
    private static int fractionState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // final state
    private static int fractionWithWholeState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // final state
    private static int exponentNumberState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // non-final state
    private static int underscoreFractionState(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // non-final state
    private static int underscoreWholeFraction(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }

    // non-final state
    private static int underscoreExponent(String input, int count)
    {
        char c = input.charAt(count);
        int result = -1;

        return result;
    }
}
