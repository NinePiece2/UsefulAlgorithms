package dynamicProgramming;

import java.io.File; // Importing Libraries
import java.io.FileNotFoundException;  
import java.util.Scanner;

import java.util.NoSuchElementException;
import java.lang.RuntimeException;

public class DynamicProgDriver {
    public static void main (String[] args) {
        Knapsack knapsack;
        LIS longestIncSeq;
        RodCut rodCut;
        int nKnapsack, sentementKnapsack[], weightCandy[], weightKnapsack, lisIn[], nRodCut, price[];
        String lisLine, lisLineArray[];
        String algorithm1Name = "2.Knapsack.txt";
        String algorithm2Name = "3.LIS.txt";
        String algorithm3Name = "4.RodCut.txt";

        System.out.println("The Algorithms in the set are run sequentally");
        System.out.println("To edit the input of each problem edit the text files in the project folder.");
        System.out.println("\nAlgorithm Name                              -> File Name");
        System.out.println("Algorithm 1: Knapsack                       -> " + algorithm1Name);
        System.out.println("Algorithm 2: Longest Increasing Subsequence -> " + algorithm2Name);
        System.out.println("Algorithm 3: Rod Cutting                    -> " + algorithm3Name);
        System.out.println();

        try {
			File file1 = new File("UsefulAlgorithms/inputFiles/"+algorithm1Name); // Selects the files to read data from
            File file2 = new File("UsefulAlgorithms/inputFiles/"+algorithm2Name);
            File file3 = new File("UsefulAlgorithms/inputFiles/"+algorithm3Name);

            Scanner read1 = new Scanner(file1); // Makes a scanner from the files
            Scanner read2 = new Scanner(file2);
            Scanner read3 = new Scanner(file3);


            System.out.println("Algorithm 1 - Knapsack Algorithm:");
						
			nKnapsack = read1.nextInt(); // Read the number of values to be input
			sentementKnapsack = new int [nKnapsack]; // Create arrays of that size
			weightCandy = new int [nKnapsack];
			
			for (int i = 0; i < nKnapsack; i++) { // Populate the arrays with the data from the text file
				sentementKnapsack[i] = read1.nextInt();
			}
			
			for (int i = 0; i < nKnapsack; i++) {
				weightCandy[i] = read1.nextInt();
			}
			
			weightKnapsack = read1.nextInt(); // Get the total weight of the bag
			knapsack = new Knapsack(nKnapsack, sentementKnapsack, weightCandy, weightKnapsack); // Create a knapsack object using the given inputs

            System.out.println(knapsack.solution());


            System.out.println("\n\nAlgorithm 2 - Longest Increasing Subsequence:");
			lisLine = read2.nextLine(); // Read the entire line
			lisLineArray = lisLine.split(" ", -1); // Split the line where there is a space
			lisIn = new int[lisLineArray.length]; // Make an integer array to store the data
			
			for (int i = 0; i < lisLineArray.length; i++) { // Store the data from the String separated array into the integer one 
				lisIn[i] = Integer.parseInt(lisLineArray[i]);
			}
			
			longestIncSeq = new LIS(lisIn); // Create an LIS object and feed it the array of integers

            System.out.println(longestIncSeq.solution());


            System.out.println("\n\nAlgorithm 3 - Rod Cutting:");

            nRodCut = read3.nextInt(); // Finds the length of the rod
			
			if (nRodCut <= 0) { // Checks if the size of the rod is zero or negative
				read1.close(); // Close all of the scanners
				read2.close();
                read3.close();
				throw new RuntimeException("Rod Cut length must be greater than 0. Invalid Input: " + nRodCut);
			}
			
			price = new int[nRodCut]; // Makes a new array for the prices
			
			for (int i = 0; i < nRodCut; i++) {
				price[i] = read3.nextInt(); // Reads the price values from the text file
			}
			
			rodCut = new RodCut (nRodCut, price); // Use the input to create a RodCut object
            System.out.println(rodCut.solution());

            read1.close(); // Close all of the scanners
            read2.close();
            read3.close();


        } catch(FileNotFoundException e) { // If one or more file isn't available
            System.out.println("\u001B[31mOne or more files could not be found -> Please Create Them\n" + e);
            System.out.println("\u001B[31mProgram Terminated");
        
        } catch(NoSuchElementException ne) { // If data is entered incorrectly
            System.out.println("\u001B[31mPlease enter data into the input text files and rerun the program.");
            System.out.println("\u001B[31mFor Algorithm 1 - Knapsack Problem: The expected input is a single positive integer N followed in the next line by N "
					+ "\n\tpositive integers that represent John's sentemental value for each candy in order, on the next line N positive integers representing the"
					+ "\n\tweight of each candy followed by another line with a single positive integer representing the maximum weight that the bag can carry.");
            System.out.println("\u001B[31mFor Algorithm 2 - Longest Increasing Subsequence: The expected input is a series of integers seperated by spaces on one line");
            System.out.println("\u001B[31mFor Algorithm 3 - Rod Cutting Problem: The expected input is a single positive n integer representing the \n\t"
					+ "number of inches a rod is in length followed by n non-negitive integers representing the price of that size rod. ");
            System.out.println("\u001B[31mProgram Terminated");
        } catch(RuntimeException ex) { // If there are other errors within the program based on input
            System.out.println("\u001B[31mFor Algorithm 1 - Knapsack Problem: The expected input is a single positive integer N followed in the next line by N "
                    + "\n\tpositive integers that represent John's sentemental value for each candy in order, on the next line N positive integers representing the"
                    + "\n\tweight of each candy followed by another line with a single positive integer representing the maximum weight that the bag can carry.");
            System.out.println("\u001B[31mFor Algorithm 2 - Longest Increasing Subsequence: The expected input is a series of integers seperated by spaces on one line");
            System.out.println("\u001B[31mFor Algorithm 3 - Rod Cutting Problem: The expected input is a single positive n integer representing the \n\t"
                    + "number of inches a rod is in length followed by n non-negitive integers representing the price of that size rod. ");
            System.out.println("\u001B[31mProgram Terminated " + ex);
        }

    }
}
