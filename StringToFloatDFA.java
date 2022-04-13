import java.util.Scanner;
import java.util.Stack;

public class StringToFloatDFA {
    public static void main(String[] args) 
    {
        while (true)
        {
            String input = receiveInput();
            boolean valid = validateInput(input);
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
    
    public static boolean validateInput(String input) 
    {
        boolean validity = true;

        boolean decimalReached = false;

        for (int i = 0; i < input.length(); i++) {
            char next = input.charAt(i);

            switch (next) {
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
                    break;
                
                case '.':
                    if (decimalReached)
                        return false;
                    else
                        decimalReached = true;
                    break;
                
                case '+':
                    
                    break;

                case '-':
                    
                    break;

                case '_':

                    break;

                case 'e':
                case 'E':

                    break;

                case 'f':
                case 'F':

                    break;

                case 'd':
                case 'D':

                    break;
            }
        }

        return validity;
    }
}
