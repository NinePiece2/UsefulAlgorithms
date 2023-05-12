package greedyAlgorithms;

import java.io.File; // Importing Libraries
import java.io.FileNotFoundException;  
import java.util.Scanner;

import java.util.NoSuchElementException;
import java.lang.RuntimeException;

public class GreedyDriver {
	public static void main (String[] args) {
		String algorithm1Name = "9.MaxBipartMatch.txt";
		String firstLine, firstLineArr [];
		int inputAdjMatrix[][];
		BipartiteMatching maxBipartMatch;
		
		System.out.println("The Algorithms in the set are run sequentally");
        System.out.println("To edit the input of each problem edit the text files in the project folder.");
        System.out.println("\nAlgorithm Name                              -> File Name");
		System.out.println("Algorithm 1: Maximum Bipartite Matching     -> " + algorithm1Name);
		System.out.println();
		
		try {
			File file1 = new File("UsefulAlgorithms/inputFiles/"+algorithm1Name); // Selects the files to read data from

			Scanner read1 = new Scanner(file1); // Makes a scanner from the files
			
			System.out.println("Algorithm 1 - Maximum Bipartite Matching (Ford-Fulkerson): ");
			
			firstLine = read1.nextLine(); // Read the entire first line
			firstLineArr = firstLine.split(" ", -1); // Separate it by spaces into an array
			
			inputAdjMatrix = new int[firstLineArr.length][firstLineArr.length]; // Create an NxN array based on the size of the first row
			
			for (int i = 0; i < firstLineArr.length; i++) { // Move the first row into the NxN array
				inputAdjMatrix[0][i] = Integer.parseInt(firstLineArr[i]);
			}
			
			for (int i = 1; i < firstLineArr.length; i++) { // Add the rest of the values into the NxN array
				for (int j = 0; j < firstLineArr.length; j++) {
					inputAdjMatrix[i][j] = read1.nextInt();
				}
			}

			maxBipartMatch = new BipartiteMatching(inputAdjMatrix); // Create a BipartiteMatching object using the NxN array
			
			System.out.println("The maximum number of applicants matching for the jobs is: " + maxBipartMatch.maximum()); // Run the maximum and print the solution
					
			read1.close(); // Close the scanner
			
		} catch(FileNotFoundException e) { // If the file isn't available
			System.out.println("\u001B[31mFile could not be found -> Please Create It\n" + e);
			System.out.println("\u001B[31mProgram Terminated");
		} catch(NoSuchElementException ne) { // If data is entered incorrectly
			System.out.println("\u001B[31mPlease enter data into the input text files and rerun the program.");
			System.out.println("\u001B[31mFor Problem 1 - Maximum Bipartite Matching the expected input is an NxN adjacency matrix representation of M job applicants \n\t"
					+ "and N jobs where 1 is an application and 0 is no application."); 
			System.out.println("\u001B[31mProgram Terminated");
		} catch(RuntimeException ex) { // If there are other errors within the program based on input
			System.out.println("\u001B[31mPlease enter data into the input text files and rerun the program.");
			System.out.println("\u001B[31mFor Problem 1 - Maximum Bipartite Matching the expected input is an NxN adjacency matrix representation of M job applicants \n\t"
					+ "and N jobs where 1 is an application and 0 is no application."); 
			System.out.println("\u001B[31mProgram Terminated " + ex);
		}
	}	
}
