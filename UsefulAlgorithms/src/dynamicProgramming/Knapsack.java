package dynamicProgramming;

import static java.lang.Math.max;

public class Knapsack {
	// Instance Variables for Output
	private int solution[], knapsackWeight;

	public Knapsack(int nKnapsack, int sentiment[], int weightCandy[], int knapsackWeight) {
		int solution [] = new int [knapsackWeight + 1]; // Create an integer array that has a knapsackWeight + 1 values
		
		// Only positive integers are expected as the scenario is of a person in a candy store so there should be no negative weights or negative sentiments
		if (sentiment.length != nKnapsack) { // Checks if the arrays entered have the correct size
			throw new RuntimeException("The sentement array is not the same size as the nKnapsack = " + sentiment.length);
		} else if (weightCandy.length != nKnapsack) {
			throw new RuntimeException("The weightCandy array is not the same size as the nKnapsack = " + weightCandy.length);
		} else if(nKnapsack <= 0) { // Checks if the integers entered are correct
			throw new RuntimeException("N Kapsack must be a positive integer");
		} else if (knapsackWeight <= 0) {
			throw new RuntimeException("Knapsack Weight must be a positive integer");
		}
		
		for (int i = 0; i < nKnapsack; i++) {
			if (sentiment[i] <= 0) { // Checks the values of the sentiment and weightCandy arrays to make sure they are positive integers
				throw new RuntimeException("Sentement values must be positive");
			} else if (weightCandy[i] <= 0) {
				throw new RuntimeException("Candy weight values must be positive");
			}
		}
		
		for (int i = 1; i < nKnapsack + 1; i ++) { // For each candy
			for (int j = knapsackWeight; j >= 0; j--) {// For the weight of the knapsack
				if (weightCandy[i - 1] <= j) { // If the weight of the next candy is less than the current iterative weight of the bag
					solution[j] = max(solution[j], solution[j - weightCandy[i - 1]] + sentiment[i - 1]); // Add the maximum of the current solution or 
				}																						 //  the solution at the knapsack - the weight of 
			}																							 //  the candy + the sentiment all for the next knapsack
		}

		this.solution = solution;
		this.knapsackWeight = knapsackWeight;
		//System.out.println("Highest sentimental aggregated value = " + solution[knapsackWeight]); // Prints the Highest sentimental aggregated value
	}

	public String solution(){
		return "Highest sentimental aggregated value = " + solution[knapsackWeight]; // Prints the Highest sentimental aggregated value
	}
}
