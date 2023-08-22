//-----------------------------------------------------------------------------------------------------------------------------------
// Title: stringSort class
// Author: Begüm Þara Ünal - Giray Berk Kuþhan
// ID: 10096206194 - 10889878942
// Section: 4
// Assignment: 4
// Description: This class is to implement a String sorting algorithm.
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.Arrays;
import java.util.Scanner;

public class stringSort 
{
        //-----------------------------------------------------------------------------------------------------------------
	   // Summary: This is method for reads a line of input with nextLine from the Scanner
	  // Precondition: scInput is Scanner, w is integer, w is integer.
	 // Postcondition: Splits inputs into an array of strings.
    //-------------------------------------------------------------------------------------------------------------------
    public static String[] takeInput(Scanner scInput) 
    {
        String line = scInput.nextLine();

        return line.split("\\s");
    }

 
        //-----------------------------------------------------------------------------------------------------------------
	   // Summary: This is method for prints the array
	  // Precondition: str is String Array
	 // Postcondition: Prints the result
    //-------------------------------------------------------------------------------------------------------------------
    public static void print(String[] str) 
    {
    String joined = String.join(" ", str);

    System.out.println(joined);
    }


        //-----------------------------------------------------------------------------------------------------------------
	   // Summary: This is method for calculate the value of string based on the character values using the formula.
	  // Precondition: calculate is String
	 // Postcondition: Return the calculation result.
    //-------------------------------------------------------------------------------------------------------------------
    public static int calculateString(String calculate) 
    {
    int sum = 0;
    int length = calculate.length();
    
    for (char c : calculate.toCharArray()) 
    {
        sum += (c - 'a' + 1) * Math.pow(26, --length);
    }
    
    return sum;
    }


        //-----------------------------------------------------------------------------------------------------------------
	   // Summary: This is method for to find the distance between string values 
	  // Precondition: firstArray is String Array,secondArray is String Array
	 // Postcondition: Redirects to the relevant method according to the distance value
    //-------------------------------------------------------------------------------------------------------------------
    public static void sortStringAlgorithm(String[] firstArray, String[] secondArray) 
    {
        for (int i = 0; i < firstArray.length; i++) 
        {
            int distance = Math.abs(calculateString(firstArray[i]) - calculateString(secondArray[i]));//calculate the distance

            if (distance % 2 == 0) //if distance is even 
            {
                firstArray[i] = sorting(firstArray[i], secondArray[i]);//sort the string in the order defined by the corresponding element in the second array.
            } 
            else //if the distance is odd 
            {
                firstArray[i] = alfabeticOrder(firstArray[i]); //sort the array alphabetically
            }
        }
    }
         //-----------------------------------------------------------------------------------------------------------------
	    // Summary: This is method Sorts a string by adding the characters in the string "sequence" in the specified order, 
       //followed by any remaining characters not found in the string "sequence". 
	  // Precondition: str is String,sequence is String 
	 // Postcondition: Return to order 
    //-------------------------------------------------------------------------------------------------------------------
   public static String sorting(String str, String sequence) 
   {
    StringBuilder order = new StringBuilder();
    StringBuilder left = new StringBuilder(str);

    // Inserts characters from string 'sequence' in order
    for (char ch : sequence.toCharArray()) 
    {
        int value = left.indexOf(String.valueOf(ch));

        while (value != -1) //until no more character are found
        {
            order.append(ch);
            left.deleteCharAt(value);// character is removed
            value = left.indexOf(String.valueOf(ch));
        }
    }

     order.append(left); // adds remaining characters not in 'sequence'

        return order.toString();//return the result
}


        //-----------------------------------------------------------------------------------------------------------------
	   // Summary: This is method sorts a string by converting it to a character array,
       // sorting the array, and then converting it back to a string.
	  // Precondition: str is String
	 // Postcondition: Return new sorting string
    //-------------------------------------------------------------------------------------------------------------------
    public static String alfabeticOrder(String str) 
    {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
