package graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class StronglyConnectedComps {
		// Instance Variables
		private ArrayList<LinkedList<Integer>> vertices = new ArrayList<LinkedList<Integer>>(); // Adjacency List	
		private int numVertices; 
		private ArrayList<Boolean> explored; // Setup for the DFS
		private ArrayList<Boolean> transposedExplored; // Setup for the transposed DFS
		private ArrayList<LinkedList<Integer>> transposed = new ArrayList<LinkedList<Integer>>(); // Adjacency List	

		
		public StronglyConnectedComps(int numEdges, int numVertices) {
			if (numVertices <= 0) { // Checks if the numVertices and edges given are correct values
				throw new RuntimeException("Number of Antennas must be greater than 0");
			}else if (numEdges < 0) {
				throw new RuntimeException("Number of Cables must be a positive integer");
			}
			
			this.numVertices = numVertices;
					
			for (int i = 0; i < numVertices; i++) { // Creates a linked list for each vertex
		    	  this.vertices.add(i, new LinkedList<Integer>());
		    }
		}
		
		public String stronglyConnected() {
			String output = "";
			explored = new ArrayList<Boolean>(); // Initializes an ArrayList for the vertices that have been explored
			transposedExplored = new ArrayList<Boolean>();
		    boolean result = true;
		    
			// 1. Initialize all vertices as not visited.
		    for (int j = 0; j < getNumVertices(); j++) { // Adds a false entry to the explored array for each vertex in the graph 
		    	explored.add(false);
		    }
		    
		    // 2. Do a DFS traversal of graph starting from any arbitrary vertex v. 
		    // If DFS traversal doesn’t visit all vertices, then return false
		    DFS2(0); // All graphs have a 0 vertex
		    
		    for (int i = 0; i < numVertices; i++) {
		    	if (explored.get(i) == false) {
		    		result = false;
		    	}
		    }
		    
		    // 3. Reverse all arcs (or find transpose or reverse of graph)
		    transpose();
		    
		    // 4. Mark all vertices as not-visited in reversed graph
		    for (int j = 0; j < getNumVertices(); j++) { // Adds a false entry to the explored array for each vertex in the graph 
		    	transposedExplored.add(false);
		    }
		    
		    // 5a. Do a DFS traversal of reversed graph starting from same vertex v (Same as step 2).
		    DFSTranspose(0);
		    
		    
		    // 5b. If DFS traversal doesn’t visit all vertices, then return false. Otherwise return true.
		    for (int i = 0; i < numVertices; i++) {
		    	if (transposedExplored.get(i) == false) {
		    		result = false;
		    	}
		    }
		    
		    // Output statement based on result of the graph
		    if (result) {
		    	output = "Yes all the antennas can communicate with each other";
		    } else {
		    	output = "No all the antennas cannot communicate with each other";
		    }

			return output;
		    
		}
		
		private void transpose() {
			int currentVertex; // Temporary variable
			for (int i = 0; i < numVertices; i++) { // Creates a linked list for each vertex
		    	  this.transposed.add(i, new LinkedList<Integer>());
		    }
			
			for (int i = 0; i < numVertices; i++) { // For each vertex
				for (int j = 0; j < degreeVertex(i); j++) { // For each vertex connected to the current vertex
					currentVertex = getVertices().get(i).get(j); // The current vertex is the jth vertex of the ith vertex's linked list
					transposed.get(currentVertex).add(i); // Add the opposite edge to the transposed graph
				}
			}
			
		}
		
		/*
		 *  Completes the DFS Algorithm through the use of recursion 
		 *   A private method as it should only be called by the DFS method in this class
		 */
		private void DFS2(int startingPoint) {
			explored.set(startingPoint, true); // Marks the vertex being explored as true
		    for (int k = 0; k < degreeVertex(startingPoint); k++) { // For each vertex that the current vertex is connected to	
		    	int vertexBeingDiscovered = getVertices().get(startingPoint).get(k); // Put the value of the vertex that is connected to the current vertex 
		    																		 //  (startingPoint) into a variable called vertexBeingDiscovered
		    	if (!explored.get(vertexBeingDiscovered)) { // If the vertex has not been explored
		    		DFS2(vertexBeingDiscovered);  //  run through DFS again
		    	}
				
			}
		}
		
		/*
		 *  Completes the DFS Algorithm through the use of recursion 
		 *   A private method as it should only be called by the DFS method in this class
		 */
		private void DFSTranspose(int startingPoint) {
			transposedExplored.set(startingPoint, true); // Marks the vertex being explored as true
		    for (int k = 0; k < degreeTransposedVertex(startingPoint); k++) { // For each vertex that the current vertex is connected to	
		    	int vertexBeingDiscovered = getTransposed().get(startingPoint).get(k); // Put the value of the vertex that is connected to the current vertex 
		    																		 //  (startingPoint) into a variable called vertexBeingDiscovered
		    	if (!transposedExplored.get(vertexBeingDiscovered)) { // If the vertex has not been explored
		    		DFSTranspose(vertexBeingDiscovered);  //  run through DFS again
		    	}
				
			}
		}
		
		public void addEdge(int a, int b) {
			int check = 0;
			
			if (a >= numVertices) { // Checks the whether the values given are within the graph
				throw new RuntimeException(a + " is not in the graph.");
			} else if (b >= numVertices) {
				throw new RuntimeException(b + " is not in the graph.");
			} else if (a < 0) {
				throw new RuntimeException(a + " is not in the graph.");
			} else if (b < 0) {
				throw new RuntimeException(b + " is not in the graph.");
			}
			
			for (int i = 0; i < vertices.get(a).size(); i++) { // Checks if they edge had already been created
				if (vertices.get(a).get(i) == b) {
					System.out.println("Edge Already Exists - Ignored");
					check = 1;
				}
			}
			
			if (check == 0) {
				vertices.get(a).add(b);
			}
		}
		
		/*
		 * @return the number of vertices 
		 */
		private int getNumVertices() {
			return numVertices;
		}
		
		/*
		 * @return the number of vertexes that a given vertex is attached to by direct edges. 
		 */
		private int degreeVertex(int a) { 
			try {
				return vertices.get(a).size();
			} catch (RuntimeException e) {
				System.out.println("Value given is not a vertex -> Operation Canceled");
				return 0;
			}
		}
		
		/*
		 * @return the number of vertexes that a given vertex is attached to by direct edges. 
		 */
		private int degreeTransposedVertex(int a) { 
			try {
				return transposed.get(a).size();
			} catch (RuntimeException e) {
				System.out.println("Value given is not a vertex -> Operation Canceled");
				return 0;
			}
		}
		
		/*
		 * @return the Adjacency List for the graph
		 * Private method to only be used within this class
		 */
		private ArrayList<LinkedList<Integer>> getVertices() {
			return vertices;
		}
		
		/*
		 * @return the Adjacency List for the transposed graph
		 * Private method to only be used within this class
		 */
		private ArrayList<LinkedList<Integer>> getTransposed() {
			return transposed;
		}
		
}
