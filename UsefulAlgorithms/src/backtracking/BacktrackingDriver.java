package backtracking;

import java.io.File; // Importing Libraries
import java.io.FileNotFoundException;  
import java.util.Scanner;

import java.util.NoSuchElementException;
import java.lang.RuntimeException;

public class BacktrackingDriver {
    public static void main (String[] args) {
        NQueens nQueens;
        int nQueenNum;
        String algorithm1Name = "1.NQueens.txt";

        System.out.println("The Algorithms in the set are run sequentally");
        System.out.println("To edit the input of each problem edit the text files in the project folder.");
        System.out.println("\nAlgorithm Name                              -> File Name");
        System.out.println("Algorithm 1: N Queens                       -> " + algorithm1Name);
        System.out.println();


        try {
			File file1 = new File("UsefulAlgorithms/inputFiles/"+algorithm1Name); // Selects the files to read data from

            Scanner read1 = new Scanner(file1); // Makes a scanner from the files


            nQueenNum = read1.nextInt(); // Gets the number from the text file
			System.out.println("Algorithm 1 - N-Queens(N = " + nQueenNum +"):");
			System.out.println("1s are where queens should be:");

			nQueens = new NQueens(nQueenNum); // Creates an NQueens object using the given number and solves the algorithm

            System.out.println(nQueens.solution()); // Prints the solution of the NQueens problem

            read1.close(); // Close all of the scanners


        } catch(FileNotFoundException e) { // If one or more file isn't available
            System.out.println("\u001B[31mOne or more files could not be found -> Please Create Them\n" + e);
            System.out.println("\u001B[31mProgram Terminated");
        
        } catch(NoSuchElementException ne) { // If data is entered incorrectly
            System.out.println("\u001B[31mPlease enter data into the input text files and rerun the program.");
            System.out.println("\u001B[31mFor Algorithm 1 - N Queens: The expected input is a single positive integer.");
            System.out.println("\u001B[31mProgram Terminated");
        } catch(RuntimeException ex) { // If there are other errors within the program based on input
            System.out.println("\u001B[31mFor Algorithm 1 - N Queens: The expected input is a single positive integer.");
            System.out.println("\u001B[31mProgram Terminated " + ex);
        }
    }
}
