package dynamicProgramming;

import static java.lang.Math.max;

public class RodCut {
	
	// Instance Variables for Output
	private int nRodCut, result[];

	/*
	* Completes the Rod Cutting Problem using a bottom up method
	*/
	public RodCut (int nRodCut, int rodCutPrices[]) {
		if (nRodCut <= 0) { // Checks if the size of the rod is zero or negative
			throw new RuntimeException("Rod Cut length must be greater than 0. Invalid Input: " + nRodCut);
		}
		
		for (int i = 0; i < nRodCut; i++) { // Checks if the prices are negative
			if (rodCutPrices[i] < 0) {
				throw new RuntimeException("Rod Cut prices must be greater than 0. Invalid Input: " + rodCutPrices[i]);
			}
		}
		
		int result[] = new int [nRodCut + 1]; // Make a new int array from 0..n
		int currentPrice; // Temporary variable
		result[0] = 0; // Set the first result value to zero
		
		for (int j = 1; j <= nRodCut; j++) { // for 1 -> n
			currentPrice = Integer.MIN_VALUE; // CurrentPrice = -inf
			for (int i = 0; i < j; i++) { // for 1 -> j, needs to start at 0 because the price of 1 is stored at index 0.
				currentPrice = max(currentPrice, rodCutPrices[i] + result[j - i - 1]); // -1 Because the price of 1 is stored at index 0.
			}																		   // Store the maximum of currentPrice or the
			result[j] = currentPrice; // Store the new current price in the result for this length
		}
		
		//System.out.println("Maximum Price of a " + nRodCut + " inch rod with the given prices is " + result[nRodCut]); // Print the result of the 
		this.nRodCut = nRodCut;
		this.result = result;
	}

	public String solution(){
		return "Maximum Price of a " + nRodCut + " inch rod with the given prices is " + result[nRodCut];
	}
}
