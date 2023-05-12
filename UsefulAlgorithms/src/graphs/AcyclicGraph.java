package graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class AcyclicGraph {
	// Instance Variables
	private ArrayList<LinkedList<Integer>> vertices = new ArrayList<LinkedList<Integer>>(); // Adjacency List	
	private int numVertices; 
	ArrayList<Boolean> explored; // Setup for the DFS
	
	public AcyclicGraph(int numEdges, int numVertices) {
		if (numVertices <= 0) {
			throw new RuntimeException("Number of Vercites must be greater than 0");
		}else if (numEdges < 0) {
			throw new RuntimeException("Number of Edges must be a positive integer");
		}
		
		this.numVertices = numVertices;
				
		for (int i = 0; i < numVertices; i++) { // Creates a linked list for each vertex
	    	  this.vertices.add(i, new LinkedList<Integer>());
	    }
	}
	
	/*
	 *  Adds the value of the second vertex to the linked list of the first
	 *  Undirected graph is expected so it is done for both vertices.
	 */
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
			vertices.get(b).add(a);
		}
	}
	
	/*
	 * Checks if there are cycles in the graph
	 * DFS base is used
	 * Sets everything up to be used in the cycleCheckMain() function
	 */
	public String cycleCheck() {
		boolean acyclic = true;
		explored = new ArrayList<Boolean>(); // Initializes an ArrayList for the vertices that have been explored
	    
	    for (int j = 0; j <  getNumVertices(); j++) { // Adds a false entry to the explored array for each vertex in the graph 
	    	explored.add(false);
	    }
	    
	    for (int i = 0; i < getNumVertices(); i++) { // For every vertex in the graph
	    	if (!explored.get(i)) { // If it has not been explored
	    		if (cycleCheckMain(i, -1)) { // Check if they're is a cycle (-1 is used as it is not a vertex that is possible for the first run,
	    			acyclic = false;		 //  any negative number would work)
	    		}							 // If there is a cycle change the acyclic to false
	    	}
	    }
	    
	    if (acyclic) { // Outputs the result of whether the graph has a cycle or not
	    	return "Acyclic Graph: YES";
	    }else {
	    	return "Acyclic Graph: NO";
	    }
		    	
	}
	
	/*
	 * Used in the cycleCheck() function to check for cycles recursively in the graph 
	 */
	private boolean cycleCheckMain(int startingPoint, int start) {
		explored.set(startingPoint, true); // Marks the vertex being explored as true
		
		for (int k = 0; k < degreeVertex(startingPoint); k++) { // For each vertex that the current vertex is connected to	
	    	int vertexBeingDiscovered = getVertices().get(startingPoint).get(k); // Put the value of the vertex that is connected to the current vertex 
	    																		 //  (startingPoint) into a variable called vertexBeingDiscovered
	    	if (!explored.get(vertexBeingDiscovered)) { // If the vertex has not been explored
	    		if(cycleCheckMain(vertexBeingDiscovered, startingPoint)) {
	    			return true;
	    		}
	    	} else if(vertexBeingDiscovered != start) { // If the starting point is the same as the element being explored
	    		return true;
	    	}
		}
		return false;
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
	 * @return the Adjacency List for the graph
	 * Private method to only be used within this class
	 */
	private ArrayList<LinkedList<Integer>> getVertices() {
		return vertices;
	}
	
	
}
