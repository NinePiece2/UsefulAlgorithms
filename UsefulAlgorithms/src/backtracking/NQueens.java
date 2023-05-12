package backtracking;

public class NQueens {
	private int matrixSize; // Instance Variables
	private int binaryMatrix [][];
	
	/*
	 * @param matrixSize positive integer that is used to specify the size of the NxN matrix used
	 */
	public NQueens(int matrixSize) {
		if (matrixSize <= 0) {
			throw new RuntimeException("Matrix Size must be a positive non-zero integer");
		}
		this.matrixSize = matrixSize;
		
		// Initializes an NxN matrix in the form of a 2D array
		this.binaryMatrix = new int [matrixSize][matrixSize];
		
		// Fills all elements of the array with zeros
		for (int i = 0; i < matrixSize; i++) { 
			for (int j = 0; j < matrixSize; j++) {
				this.binaryMatrix[i][j] = 0;
			}
		}
		
		// Solves the N-Queens problem starting at the first column
		solve(0);
		//System.out.println(toString()); // Prints the result of the algorithm
	}
	
	
	
	/*
	 * Solves the N-Queens problem
	 */
	private boolean solve(int column) {
		int j, k; // Initialize variables needed to solve the problem
		boolean horizontalCheck = true, verticalCheck = true, 
				upperLeftCheck = true, upperRightCheck = true;
		
		if (matrixSize <= column) { // Checks if the algorithm is completed
			return true;
		}
		
		for (int i = 0; i < matrixSize; i++) { // For each row in the current column
			horizontalCheck = true; 
			verticalCheck = true;
			upperLeftCheck = true;
			upperRightCheck = true;
			
			for (j = 0; j < matrixSize; j++) { // Check the row for queens
				if (binaryMatrix[i][j] == 1) {
					horizontalCheck = false;
				}
			}
			
			for (j = 0; j < matrixSize; j++) { // Check the column for queens
				if (binaryMatrix[j][column] == 1) {
					verticalCheck = false;
				}
			}
			
			for (j = i, k = column; j >= 0 && k >= 0; j--, k--) { // Upper Left Diagonal for queens
				if (binaryMatrix[j][k] == 1) {
					upperLeftCheck = false;
				}
			}
			
			for (j = i, k = column; k >= 0 && j < matrixSize; j++, k--) { // Upper Right Diagonal for queens
				if (binaryMatrix[j][k] == 1) {
					upperRightCheck = false;
				}
			} 
			
			// If there are no queens interfering
			if (horizontalCheck && upperLeftCheck && upperRightCheck && verticalCheck) {
				binaryMatrix[i][column] = 1; // Add a queen to the column and row
				if (solve(column + 1) == true) { // If the next column has a solution then
					return true; 
				} else {
					binaryMatrix[i][column] = 0; // Backtracking the Decision if the next column has no solution
				}
			}
		}
		
		return false; // No solution is possible
	}
	
	/*
	 * @Override of toString to print the result of the algorithm
	 */	
    public String toString() {
		String temp = "";
		
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				temp += binaryMatrix[i][j] + " ";
			}
			temp += "\n";
		}
		
		return temp;
	}

	public String solution(){
		return toString();
	}
	
}
