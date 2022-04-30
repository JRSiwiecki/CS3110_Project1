import java.util.Stack;

public class EvaluateExpression 
{
    public static float evaluateExpression(String input)
    {
        float result = 0f;

        String postfix = convertToPostfix(input);

        result = evaluatePostfix(postfix);

        return result;
    }

    private static String convertToPostfix(String infix)
    {
        String result = "";

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < infix.length(); i++)
        {
            char nextChar = infix.charAt(i);

            if (Character.isLetterOrDigit(nextChar) || nextChar == '.')
            {
                result += nextChar;
            }

            else if (nextChar == '(')
            {
                stack.push(nextChar);
            }

            else if (nextChar == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                {
                    result += stack.pop();
                }
            }   

            else if (nextChar == ' ')
            {
                continue;
            }

            else
            {
                while (!stack.isEmpty() && precendence(nextChar) <= precendence(stack.peek()))
                {
                    result += stack.pop();
                }

                stack.push(nextChar);
            }
        }

        while (!stack.isEmpty())
        {
            if (stack.peek() == '(')
            {
                return "Invalid.";
            }

            result += stack.pop();
        }
        
        return result;
    }

    private static float evaluatePostfix(String expression) 
    {
        // Create a ResizeableArrayStack
        Stack<Float> stack = new Stack<Float>();

        // Scan all characters of postfix individually
        for (int i = 0; i < expression.length(); i++) {
            char nextCharacter = expression.charAt(i);

            // If nextCharacter is an integer,
            // push it to the stack.
            if (Character.isDigit(nextCharacter)) 
            {
                float temp = findFloat(expression);
                stack.push(findFloat(expression));
                String tempString = String.valueOf(temp);
                i += tempString.length();
            }

            // If the character is an operator, pop the last two
            // elements from stack and apply the operator
            else {
                // declare them but don't initialize,
                // because if the nextCharacter is a space after a number
                // there is a chance that there will not be
                // two operands yet, and so we will get an
                // EmptyStackException, which is bad!
                float operandOne;
                float operandTwo;

                switch (nextCharacter) {
                    // addition
                    case '+':
                        operandOne = stack.pop();
                        operandTwo = stack.pop();
                        stack.push(operandTwo + operandOne);
                        break;

                    // subtraction
                    case '-':
                        operandOne = stack.pop();
                        operandTwo = stack.pop();
                        stack.push(operandTwo - operandOne);
                        break;

                    // division
                    case '/':
                        operandOne = stack.pop();
                        operandTwo = stack.pop();
                        stack.push(operandTwo / operandOne);
                        break;

                    // multiplication
                    case '*':
                        operandOne = stack.pop();
                        operandTwo = stack.pop();
                        stack.push(operandTwo * operandOne);
                        break;
                    
                    default:
                        break;
                }
            }
        }
        
        return stack.pop();
    }
    
    private static float findFloat(String input)
    {
        float result = 0f;

        for (int i = 0; i < input.length(); i++)
        {
            char nextChar = input.charAt(i);

            switch (nextChar)
            {
                case ' ': case 'f': case 'F':
                    return StringToFloat.convertStringToFloat(input.substring(0, i));

                default:
                    break;
            }
        }
        
        return result;
    }
    
    private static int precendence(char c)
    {
        switch (c)
        {
            case '+': case '-':
                return 1;

            case '*': case '/':
                return 2;
        }

        return -1;
    }
}
