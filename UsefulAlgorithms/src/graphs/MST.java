package graphs;

import java.util.ArrayList;

public class MST {
	// Instance Variables
	private int vertices[][];
	private int numVertices; 
	
	public MST(int numEdges, int numVertices) {
		
		if (numVertices <= 0) { // Checks the inputs given by the user and produces errors for incorrect values
			throw new RuntimeException("Number of Vercites must be greater than 0");
		} else if (numEdges < 0) {
			throw new RuntimeException("Number of Edges must be a positive integer");
		}
		
		// Instance variables are set
		this.numVertices = numVertices;
		this.vertices = new int[numVertices][numVertices]; // Size of Adjacency Matrix is numVertices x numVertices
		
		for (int i = 0; i < this.numVertices; i++) { // Creates a linked list for each vertex
	    	for (int j = 0; j < this.numVertices; j++) {
	    		this.vertices[i][j] = 0;
	    	}
	    }
	}
	
	/*
	 *  Adds the value of the weight to the Adjacency Matrix location of where the two vertices intersect in the matrix
	 *  Undirected graph is expected so it is done for both vertices.
	 */
	
	public void addEdge(int a, int b, int weight) {
		int check = 0;
		
		if (a >= getNumVertices()) { // Checks the whether the values given are within the graph
			throw new RuntimeException(a + " is not in the graph.");
		} else if (b >= getNumVertices()) {
			throw new RuntimeException(b + " is not in the graph.");
		} else if (a < 0) {
			throw new RuntimeException(a + " is not in the graph.");
		} else if (b < 0) {
			throw new RuntimeException(b + " is not in the graph.");
		} else if (weight == 0) {
			throw new RuntimeException("Weight of zero is not allowed");
		}
		
		
		if (getVertices()[a][b] != 0) { // Checks for duplicate edges
			System.out.println("Edge Already Exists - Ignored");
			check = 1;
		}
		
		if (check == 0) {
			getVertices()[a][b] = weight;
			getVertices()[b][a] = weight;
		}
	}
	
	public String solvePrim() {
		String output = "";
		int location, min, totalWeight = 0; // Temporary variables needed in the algorithm
		
		ArrayList<Integer> mstSolution = new ArrayList<Integer>(); // Storage location for the solved tree
		ArrayList<Integer> key = new ArrayList<Integer>(); // Used to keep minimum values during the algorithm 
		ArrayList<Boolean> minimized = new ArrayList<Boolean>(); // Used to keep track of vertices that are already in the solution/MST
		
		
		for (int i = 0; i < getNumVertices(); i++) { // Sets all key values to be the highest integer value possible
			key.add(Integer.MAX_VALUE);				 // Similar to Double.POSITIVE_INFINITY
			minimized.add(false);					 // Also sets all entries in the minimized arraylist to false as none have been discovered yet
			mstSolution.add(0);						 // And finally adds a zero entry to the solution arraylist before modifying them
		}
		
		key.set(0, 0);			// Sets the first vertex's key to 0
		
		for (int i = 0; i < getNumVertices() - 1; i++) { // There are NumVertices - 1 edges in the MST
			min = Integer.MAX_VALUE; // The minimum is set to the highest value possible for an integer so the first value will be set as the minimum
			location = 0; // Sets the location to 0 by default
			for (int j = 0; j < getNumVertices(); j++) { // For every vertex
				if(key.get(j) < min && minimized.get(j) == false) { // If the key of the vertex is less than the min
					min = key.get(j); // Set min equal to the key
					location = j;     //  and the location is set to the vertex that the minimum occurs at
				}
			}
			
			minimized.set(location, true); // Sets the minimum vertex to be set as discovered
		
			for (int j = 0; j < getNumVertices(); j++) { // For every vertex 
				if(getVertices()[location][j] != 0 && minimized.get(j) == false && getVertices()[location][j] < key.get(j)) { // If the vertex is connected to the current location vertex and has a value
					mstSolution.set(j, location); // set the value of the new vertex to the location of the old vertex      	  and the value is smaller then the current lowest value in the key of the vertex
					key.set(j, getVertices()[location][j]); // set the key of the new vertex to the weight of the of the old vertex that it is attached to
				}
			}
			
		}
		for (int i = 0; i < getNumVertices(); i++) { // For the key of each vertex add the values to the total weight of the MST
			if (key.get(i) < Integer.MAX_VALUE) {
				totalWeight += key.get(i);
			}
		}
        
		for (int i = getNumVertices() - 1; i > 0 ; i--) { // Print the edges of the MST starting with the last one in the arraylist
        	output += "Edge " + mstSolution.get(i) + "-" + i + " has a weight of " +  key.get(i) + "\n";
        }
        
        output += "MST = " + totalWeight; // Print out the total weight

		return output;
	}
	
	/*
	 * @return the array representation for the graph
	 * Private method to only be used within this class
	 */
	private int [][] getVertices(){
		return this.vertices;
	}
	
	/*
	 * @return the number of vertices 
	 * Private method to only be used within this class
	 */
	private int getNumVertices(){
		return this.numVertices;
	}
	
	/*
	 * The toString method was overridden to help with debugging by printing out a 
	 *  representation of the current graph within the Adjacency Matrix.
	 * Used for troubleshooting
	 */
	@Override
    public String toString() {
		String temp = "";
		
		for (int i = 0; i < getNumVertices(); i++) {
			temp += "(" + i + ") ";
			for (int j = 0; j < getNumVertices(); j++) {
	    	  temp += Integer.toString(getVertices()[i][j]) + " ";
			}
			temp += "\n";
	    }

		return temp;
    }
	
}
