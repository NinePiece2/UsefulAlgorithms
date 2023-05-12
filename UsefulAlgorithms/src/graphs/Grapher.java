package graphs;
import java.io.File; // Importing Libraries
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class Grapher {

	private static Graph graph; // Instance Variables
	private static int numEdges, numVertices;
	private static String filename;
	
	public static void main (String[] args) {	 
		try {
			filename = "test.txt";
			File file = new File(filename); // Selects the file to read data from
			Scanner read = new Scanner(file); // Starts a scanner on the file
			
			numVertices = read.nextInt(); // Finds the number of vertices and edges
			numEdges = read.nextInt();	//  based on the first two values given in the file
		        
			graph = new Graph(numEdges, numVertices); // Creates a graph based on the number of vertices
		  
			for (int i = 0; i < numEdges; i++) { // Creates edges for each edge given in the file
				graph.addEdge(read.nextInt(), read.nextInt());
			}
			
			//System.out.println("Vert = " + numVertices + ", Edges = " + numEdges);
			//System.out.println("Num of Vert: " + graph.numOfVertices());
			System.out.println("Current Graph :\n" + graph.toString()); // Prints out the current graph from the toString method   
			System.out.println("Degree of 2: " + graph.degreeVertex(2)); // Prints the degree of vertex 0
			graph.printAdjVertices(2); // Prints the adjacent vertices of vertex 0 
			graph.BFS(0); // Does and prints the Breadth First Search and Depth First Search starting at vertex 0
			graph.DFS(0);
			
			
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("A File could not be found -> " + filename); // If the file name was entered incorrectly 
		}
	}
}
