import java.util.Scanner;

public class StringToFloatDFA 
{ 
    public static boolean validateInput(String input) 
    {
        boolean validity = true;
            


        return validity;
    }

    public static String receiveInput() 
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter a float (Enter 'q' to quit): ");

        String userInput = in.nextLine();

        if (userInput == "q" || userInput == "Q") {
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

    private static void startState(char c)
    {

    }

    private static void wholeNumberState(char c)
    {

    }

    private static void invalidState(char c)
    {

    }

    private static void DecimalNoWholeNumberState(char c)
    {

    }

    private static void UnderscoreWholeNumberState(char c)
    {

    }

    private static void DecimalWholeNumberState(char c)
    {

    }

    private static void ExponentState(char c)
    {

    }

    private static void SuffixState(char c)
    {

    }

    private static void FractionState(char c)
    {

    }

    private static void FractionWithWholeState(char c)
    {

    }

    private static void ExponentNumberState(char c)
    {

    }

    private static void UnderscoreFractionState(char c)
    {

    }

    private static void UnderscoreWholeFraction(char c)
    {

    }

    private static void UnderscoreExponent(char c)
    {

    }
}
