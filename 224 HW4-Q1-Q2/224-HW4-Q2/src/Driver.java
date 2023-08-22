//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Driver class
// Author: Begüm Þara Ünal - Giray Berk Kuþhan
// ID: 10096206194 - 10889878942
// Section: 4
// Assignment: 4
// Description: This class is driver class of question 2
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.Scanner;

public class Driver 
{    
    public static void main(String[] args) 
    {
        Scanner scInput = new Scanner(System.in); //scanner

        System.out.println("First Array:");
        String[] firstArray =stringSort.takeInput(scInput);//to take input of first array

        System.out.println("Second Array:");
        String[] secondArray = stringSort.takeInput(scInput);//to take input of second array

        stringSort arrayUtils = new stringSort();// to sort the array based on the rules
        arrayUtils.sortStringAlgorithm(firstArray, secondArray);

        System.out.println("Sorted Array:");
        arrayUtils.print(firstArray);//prints the sorted array
    }
}

/*
First Array:
dog honey apple rope
Second Array:
gdbo bonex pina elo 
Sorted Array:
gdo ehnoy ppale eorp
------------------------
First Array:
forest water nick doze  
Second Array: 
ftrki olis cim ipgk  
Sorted Array: 
eforst aertw cink doze 
------------------------
 */