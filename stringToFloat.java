// Name: Joseph Siwiecki
// Class: CS 3110.02
// Professor: Qichao Dong
// Project 1
// Date: 4/13/2022
// Due Date: 4/13/2022
// File Name: stringToFloat.java
// Description: Converts validated string input to a floating-point literal.
//          *** This file does not contain a main method. Run validateStringDFA.java
//              to test inputs.

import java.math.BigDecimal;

public class stringToFloat
{
    // converts a string to a float
    public static float convertStringToFloat(String input)
    {
        // big decimal provides more precision in calculations, the value is converted to a float at the end
        BigDecimal accResult = new BigDecimal("0");
        boolean isPositive = true, isFractional = true;
        int decimalPosition = input.length() - 1;
            
        // if input is in scientific notation, use function designed to calculate it
        // otherwise, continue in this function
        if (input.contains("e") || input.contains("E"))
        {
            return stringToFloatScientific(input);
        }
        
        if (input.contains("."))
            decimalPosition = input.indexOf(".");
        
        // avoids errors in calculations when there is no decimal
        else if (input.contains("f") || input.contains("F") || input.contains("d") || input.contains("D"))
        {
            isFractional = false;
            decimalPosition = input.length() - 1;
        }

        else
        {
            isFractional = false;
            decimalPosition = input.length();
        }
            

        // check if input is negative
        if (input.charAt(0) == '-')
            isPositive = false;
        
        BigDecimal bigFactor = new BigDecimal("1");
        BigDecimal tempFactor = new BigDecimal("10");
        
        // whole number part
        // look at each individual number, multiply it by its place value, and add it to itself
        for (int i = decimalPosition - 1; i >= 0; i--)
        {
            char nextChar = input.charAt(i);
            
            if (nextChar == '-')
                continue;

            if (nextChar == '_')
                continue;
            
            if (nextChar == 'f' || nextChar == 'F')
                break;
            
            if (isPositive)
            {
                BigDecimal tempResult = bigFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.add(tempResult);
            }
                
            else
            {
                BigDecimal tempResult = bigFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.subtract(tempResult);
            }
            
            // increase factor
            bigFactor = bigFactor.multiply(tempFactor);
        }

        // factors for fractional calculations
        BigDecimal smallFactor = new BigDecimal("0.1");
        tempFactor = new BigDecimal("0.1");

        smallFactor.multiply(tempFactor);
        
        // fractional part
        // same as whole number but in reverse since we are in the decimal part of the number
        for (int i = decimalPosition + 1; i < input.length() && isFractional; i++)
        {
            char nextChar = input.charAt(i);

            if (nextChar == '_')
                continue;
            
            if (nextChar == 'f' || nextChar == 'F' || nextChar == 'd' || nextChar == 'D')
                break;
            
            if (isPositive)
            {
                BigDecimal tempResult = smallFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.add(tempResult);
            }
            
            else
            {
                BigDecimal tempResult = smallFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.subtract(tempResult);
            }
                
            smallFactor = smallFactor.multiply(tempFactor);
        }
        
        // System.out.println("BigDecimal Value: " + accResult.toString()); 
        return accResult.floatValue();
    }
    
    // same as previous function but for scientific notation
    public static float stringToFloatScientific(String input) 
    {
        BigDecimal accResult = new BigDecimal("0");
        boolean isPositive = true, expPositive = true, isFractional = false;
        int decimalPosition = 0, ePosition = input.indexOf("e"); 
        
        if (input.contains("E"))
            ePosition = input.indexOf("E");
        
        if (input.charAt(0) == '-')
            isPositive = false;

        if (input.contains("."))
        {
            decimalPosition = input.indexOf(".");
            isFractional = true;
        }

        else
            decimalPosition = ePosition;
            
        // check if the exponent is negative
        if (input.charAt(ePosition + 1) == '-')
            expPositive = false;       
        
        BigDecimal bigFactor = new BigDecimal("1");
        BigDecimal tempFactor = new BigDecimal("10");
        
        // whole number calculation
        for (int i = decimalPosition - 1; i >= 0; i--)
        {
            char nextChar = input.charAt(i);
            
            if (nextChar == '-')
                continue;

            if (nextChar == '_')
                continue;
            
            if (nextChar == 'e' || nextChar == 'E')
                break;
            
            if (isPositive)
            {
                BigDecimal tempResult = bigFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.add(tempResult);
            }
                
            else
            {
                BigDecimal tempResult = bigFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.subtract(tempResult);
            }
            
            bigFactor = bigFactor.multiply(tempFactor);
        }
        
        BigDecimal smallFactor = new BigDecimal("0.1");
        tempFactor = new BigDecimal("0.1");

        smallFactor.multiply(tempFactor);

        // fractional part
        for (int i = decimalPosition + 1; i < ePosition && isFractional; i++)
        {
            char nextChar = input.charAt(i);
            
            if (nextChar == '_')
                continue;
            
            if (isPositive)
            {
                BigDecimal tempResult = smallFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.add(tempResult);
            }
            
            else
            {
                BigDecimal tempResult = smallFactor.multiply(BigDecimal.valueOf(nextChar - '0'));
                accResult = accResult.subtract(tempResult);
            }
                
            smallFactor = smallFactor.multiply(tempFactor);
        }

        int exponent = 0;
        int factor = 1;
        
        // exponent part
        for (int i = input.length() - 1; i >= ePosition + 1; i--)
        {
            char nextChar = input.charAt(i);
            
            if (nextChar == '-' || nextChar == 'e' || nextChar == 'E')
                break;

            if (nextChar == 'f' || nextChar == 'F' || nextChar == 'd' || nextChar == 'D' || nextChar == '+' || nextChar == '_')
                continue;
            
            if (expPositive)
            {
                int increment = (nextChar - '0') * factor;
                exponent += increment;
            }
            
            else
            {
                int increment = (nextChar - '0') * factor;
                exponent -= increment;
            }
        
            factor *= 10;
        }

        BigDecimal tenFactor = new BigDecimal("10");
        
        // if the exponent is positive, just do result * (10 ^ exponent)
        if (expPositive)
            accResult = accResult.multiply(tenFactor.pow(exponent));
        
        // if the exponent is not positive, we can't use negative values for exponents with big decimal, so 
        // shift the decimal point by the amount of our exponent
        else 
            accResult = accResult.movePointRight(exponent);
         
        // System.out.println("BigDecimal Value: " + accResult.toString()); 
        return accResult.floatValue();
    }

    // helper function that removes uneccesary characters
    public static String removeSpaces(String expression)
    {
        StringBuilder sb = new StringBuilder(expression);

        for (int i = 0; i < sb.length(); i++)
        {
            if (sb.charAt(i) == ' ')
            {
                sb.deleteCharAt(i);
                i--;
            }   
        }
        
        String newExpression = sb.toString();
        return newExpression;
    }
}