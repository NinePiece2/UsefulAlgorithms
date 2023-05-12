package greedyAlgorithms;

import java.util.ArrayList;

public class BipartiteMatching {
	private int input [][];
	private int inputSize;
	private ArrayList<Boolean> explored; // Setup for the DFS
	private ArrayList<Integer> matched;
	
	public BipartiteMatching(int input [][]) {
		this.input = input; // Sets the instance variables
		this.inputSize = input.length;
		
		if (input.length != input[0].length) { // Checks if the array given is square
			throw new RuntimeException("Square matrix/2D Array is expected. Current size is " + inputSize + "x" + input[0].length + ".");
		}
		
		for (int i = 0; i < inputSize; i++) { // Checks if the values given in the array are valid 
			for (int j = 0; j < inputSize; j++) {
				if (input[i][j] != 1 && input[i][j] != 0) {
					throw new RuntimeException("Values in array must be 0 or 1.");
				}
			}
		}
		
	}
	
	/*
	 * Calculates the number of jobs that can be assigned with the given input array
	 * @return the number of assigned jobs
	 */
	public int maximum() {
	    int assignedjobs = 0;
		explored = new ArrayList<Boolean>(); // Initializes an ArrayList for the vertices that have been explored
		matched = new ArrayList<Integer>(); // Initializes an ArrayList for the applicants that that have been matched
		
	    for (int j = 0; j <  inputSize; j++) { // Adds a false entry to the explored array and a -1 to the matched h 
	    	explored.add(false);			   //  array for each vertex in the graph
	    	matched.add(-1);                   // All jobs are unmatched
	    }
	    
	    for (int i = 0; i < inputSize; i++) {
	    	for (int j = 0; j <  inputSize; j++) { // Sets a false entry to the explored array for each vertex in the graph 
		    	explored.set(j, false);
		    }
	    	if (dfs(i)) { // If there is an augmenting path add 1 to the number of jobs that have been assigned
	    		assignedjobs++;
	    	}
	    }
	    
		return assignedjobs; // Return the number of jobs that have been assigned
	}
	
	/*
	 * Using DFS to find the augmenting path
	 *  BFS is better than DFS
	 */
	private boolean dfs (int startingPoint) {
		for (int i = 0; i < inputSize; i++) { // For each Job posing
			if(input[startingPoint][i] == 1 & !explored.get(i)) { // If there is an unexplored job
				explored.set(i, true); // Mark the job as explored
				if (matched.get(i) == -1 || dfs(matched.get(i))) { // If the job hasn't been assigned or the result 
					matched.set(i, startingPoint);				   //  of this function with the current 
					return true; // Set the job as taken by the applicant and return true
				}
			}
		}
		return false; // Else return false
	}
	
}
