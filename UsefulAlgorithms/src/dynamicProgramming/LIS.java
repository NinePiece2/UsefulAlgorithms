package dynamicProgramming;

import java.util.PriorityQueue;
import static java.lang.Math.max;

public class LIS {
	
	// Instance Variables for Output
	private int solution[][], inputLength, lisValues[];

	public LIS (int input[]) {
		
		// Instance variables needed to complete the algorithm
		int inputLength = input.length;
		int solution[][] = new int [inputLength + 1][inputLength + 1];
		int[] pQueueArray = new int [inputLength];
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
		
		for (int i = 0; i < inputLength + 1; i++) { // Enters 0 into the first column of the table
			solution[i][0] = 0;
		}
		
		for (int i = 0; i < inputLength + 1; i++) { // Enters 0 into the first row of the table
			solution[0][i] = 0;
		}
		
		for (int i = 0; i < inputLength; i++) { // Adds the inputs to a priority queue which will automatically sort it from least to greatest
			pQueue.add(input[i]);
		}			
		
		
		for (int i = 0; i < inputLength; i++) { // Pops the values from the priority queue in order from least to greatest into an array
			pQueueArray[i] = pQueue.poll();
		}
		
		
		for (int i = 0; i < inputLength + 1; i++) { // For each element in the table
			for (int j = 0; j < inputLength + 1; j++) {
				if (i == 0 || j == 0) {  // If the index has a zero it is a zero
					solution[i][j] = 0;
					
				} else if (input[i - 1] == pQueueArray[j - 1]) { // If the index of the input - 1 and the index of the pQueue - 1 are equal
					solution[i][j] = solution[i - 1][j - 1] + 1; //  the solution at that location is the solution at the (input - 1 and pQueue - 1) +1
					
				} else {
					solution[i][j] = max(solution[i - 1][j], solution[i][j - 1]); // If not the other two cases the solution is the highest value of either 
																				  //  the solution at the [i - 1][j] or [i][j - 1] position
				}
			}
		}
		
		// Variables needed to print out the LIS
		int i = inputLength, j = inputLength, value = solution[inputLength][inputLength]; // value here is the Length of the LIS
		
		int lisValues[] = new int [inputLength + 1]; // Where the final values of the LIS is stored
		
		while(i > 0 && j > 0) { // While i and j are greater than 0
			if (input[i - 1] == pQueueArray[j - 1]) { // Check if the input[i-1] and pQueue[j-1] are equal as i is the index of the input and j is the index of the pQueue
				lisValues[value - 1] = input[i - 1];  //  if its true then the last value of the LIS is the input[i - 1]
				
				i--; // and decrement all of the indices and the value counter
				j--;
				value--;
			} else if(solution[i - 1][j] > solution[i][j - 1]) { // If the solution at the previous input index and current pQueueArray > solution at the current input index 
				i--;											 //  and previous pQueueArray decrement the input index by 1
			} else {                                             // Else the opposite of the last case is true where the > is a <
				j--;											 // So decrement the pQueueArray index
			}
		}
		
		// Prints out the LIS as there should be solution[inputLength][inputLength] values
		/*System.out.print("LIS is: "); 
		for (i = 0; i < solution[inputLength][inputLength]; i++) {
			System.out.print(lisValues[i] + " ");
		}
		System.out.println(""); // Prints a new line at the end
		
		System.out.println("LIS = " + solution[inputLength][inputLength]); // Prints the length of the LIS
		*/

		this.solution = solution;
		this.inputLength = inputLength;
		this.lisValues = lisValues;
	}
	
	public String solution(){
		String output = "LIS is: ";

		for (int i = 0; i < solution[inputLength][inputLength]; i++) {
			output += lisValues[i] + " ";
		}
		output += "\n"; // Prints a new line at the end
		
		output += "LIS = " + solution[inputLength][inputLength]; // Prints the length of the LIS

		return output;
	}
}
