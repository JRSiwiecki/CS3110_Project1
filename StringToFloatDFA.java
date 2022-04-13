import java.util.Scanner;

public class StringToFloatDFA 
{ 
    public static boolean validateInput(String input) 
    {
        int validity = 0;
        int count = 0;

        // if 1 is returned, input is accepted. 
        // if 0 is returned, input is rejected.
        validity = startState(String input, count);

        if (validity == 1)
        {
            return true;
        }

        else
        {
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
            boolean valid = validateInput(input);
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
                result = DecimalNoWholeNumberState(input, count + 1);
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
                result = UnderscoreWholeNumberState(input, count + 1);
            
            case '.':
                result = DecimalWholeNumberState(input, count + 1);
            
            case 'e':
            case 'E':
                result = ExponentState(input, count + 1);

            case 'f':
            case 'F':
            case 'd':
            case 'D':
                result = SuffixState(input, count + 1);
            
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
    private static int DecimalNoWholeNumberState(String input, int count)
    {
        
    }

    // non-final state
    private static int UnderscoreWholeNumberState(String input, int count)
    {

    }

    // final state
    private static int DecimalWholeNumberState(String input, int count)
    {

    }

    // non-final state
    private static int ExponentState(String input, int count)
    {

    }

    // final state
    private static int SuffixState(String input, int count)
    {

    }

    // final state
    private static int FractionState(String input, int count)
    {

    }

    // final state
    private static int FractionWithWholeState(String input, int count)
    {

    }

    // final state
    private static int ExponentNumberState(String input, int count)
    {

    }

    // non-final state
    private static int UnderscoreFractionState(String input, int count)
    {

    }

    // non-final state
    private static int UnderscoreWholeFraction(String input, int count)
    {

    }

    // non-final state
    private static int UnderscoreExponent(String input, int count)
    {

    }
}
