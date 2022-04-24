import java.util.Scanner;

// RUN THIS FILE TO TEST INPUT FOR PROJECT 2

public class Driver 
{
    public static void main(String[] args) 
    {
        while (true) 
        {
            String input = receiveInput();
            ValidateExpressionPDA.validateExpression(input);
        }
    }
    
    public static String receiveInput() 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an expression (Enter 'q' to quit): ");

        String userInput = in.nextLine();

        if (userInput.charAt(0) == ('q') || userInput.charAt(0) == ('Q')) {
            System.exit(0);
        }

        return userInput;
    }
}
