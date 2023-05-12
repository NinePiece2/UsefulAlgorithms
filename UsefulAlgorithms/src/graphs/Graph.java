package graphs;
import java.util.ArrayList; // Importing Libraries
import java.util.LinkedList;
import java.lang.RuntimeException;

public class Graph {
																							// Instance Variables
	private ArrayList<LinkedList<Integer>> vertices = new ArrayList<LinkedList<Integer>>(); // Adjacency List	
	private int numEdges, numVertices; 
	ArrayList<Boolean> explored; // Setup for the DFS
	
	public Graph(int numEdges, int numVertices) {
		this.numEdges = numEdges;
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
	 * @return the number of vertexes that a given vertex is attached to by direct edges. 
	 */
	public int degreeVertex(int a) { 
		try {
			return vertices.get(a).size();
		} catch (RuntimeException e) {
			System.out.println("Value given is not a vertex -> Operation Canceled");
			return 0;
		}
	}
	
	/*
	 * Prints out the adjacent vertices of a given vertex.
	 */
	public void printAdjVertices(int a) {
		try {
			System.out.println("Adjacent Vertices of "+ a + " are: " + vertices.get(a));
		} catch (RuntimeException e) {
			System.out.println("Value given is not a vertex -> Operation Canceled");
		}
	}
	
	/*
	 * Completes a Breadth First Search and prints the vertices it finds in order
	 */
	public void BFS(int startingPoint) {
		ArrayList<Boolean> discovered = new ArrayList<Boolean>(); // Initializes an ArrayList for the vertices that have been discovered
		LinkedList<Integer> queue = new LinkedList<Integer>(); // Initializes a LinkedList to act as a queue when discovering the vertices
		
		for (int j = 0; j <  getNumVertices(); j++) { // Adds a false entry to the discovered array for each vertex in the graph
			discovered.add(false);
	    }
		try {
			if (startingPoint >= getNumVertices()) {
				throw new RuntimeException();
			}
			
			discovered.add(startingPoint, true); // Changes the entry of the starting point in the array to true and adds it to the queue
			queue.add(startingPoint);
			
			System.out.println("BFS starting at vertex " + startingPoint + ": "); // Used to print information about the process
			
			while (queue.size() > 0) { // While 
				int temp = queue.poll(); // Pops the head of the queue for the current vertex being checked and prints out the head
				System.out.print(temp + " ");
				
				for (int k = 0; k < degreeVertex(temp); k++) { // For each vertex that the current vertex is connected to			
					int vertexBeingDiscovered = getVertices().get(temp).get(k); // Put the value of the vertex that is connected to the current vertex 
																				//  (temp) into a variable called vertexBeingDiscovered
					if (discovered.get(vertexBeingDiscovered) == false) { // If the vertexBeingDiscovered has not yet been discovered
						queue.add(vertexBeingDiscovered);				  //  add it to the queue and mark it as discovered.
						discovered.set(vertexBeingDiscovered, true);
					}
				}
			}
		}catch (RuntimeException e) {
			System.out.println("Value given is not a vertex -> Operation Canceled");
		}
		
		System.out.println(); // Print a new line character after completion
	}
	
	/*
	 * Completes a Depth First Search and prints the vertices it finds in order
	 *  This method sets up the algorithm 
	 */
	public void DFS(int startingPoint) {
		explored = new ArrayList<Boolean>(); // Initializes an ArrayList for the vertices that have been explored
	    
	    for (int j = 0; j <  getNumVertices(); j++) { // Adds a false entry to the explored array for each vertex in the graph 
	    	explored.add(false);
	    }
	    
	    try {
	    	if (startingPoint >= getNumVertices()) {
				throw new RuntimeException();
			}
	    	    
		    System.out.println("DFS starting at vertex " + startingPoint + ": "); // Used to print information about the process
		    DFS2(startingPoint); // Calls the method that actually competes the DFS
		    
	    }catch (RuntimeException e) {
			System.out.println("Value given is not a vertex -> Operation Canceled");
		}
	    
	    System.out.println(); // Prints a new line character after completion
	
	}
	
	/*
	 *  Completes the DFS Algorithm through the use of recursion 
	 *   A private method as it should only be called by the DFS method in this class
	 */
	private void DFS2(int startingPoint) {
		System.out.print(startingPoint + " "); // Prints the value of the vertex that is currently being explored
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
	 * @return the number of edges
	 */
	public int getNumEdges() {
		return numEdges;
	}

	/*
	 * @return the number of vertices 
	 */
	public int getNumVertices() {
		return numVertices;
	}
	
	/*
	 * @return the Adjacency List for the graph
	 * Private method to only be used within this class
	 */
	private ArrayList<LinkedList<Integer>> getVertices() {
		return vertices;
	}

	/*
	 * The toString method was overridden to help with debugging by printing out a 
	 *  representation of the current data within the Adjacency List.
	 */
	@Override
    public String toString() {
		String temp = "";
		
		for (int i = 0; i < numVertices; i++) {
	    	  temp = temp + "(" + i + ")" + vertices.get(i) + "\n";
	    }
		
		return temp;
    }
	
}
