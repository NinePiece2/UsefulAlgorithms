package graphs;

import java.io.File; // Importing Libraries
import java.io.FileNotFoundException;  
import java.util.Scanner;

import java.util.NoSuchElementException;
import java.lang.RuntimeException;

public class GraphsDriver {
    public static void main (String[] args) {
        AcyclicGraph acyclicGraph;
        Graph graph;
        MST minSpanningTree;
        StronglyConnectedComps stronglyConnectedComps;

        int numVertices, numEdges, numAntennas, numCables;

        String algorithm1Name = "5.AcyclicGraphs.txt";
        String algorithm2Name = "6.Graphs.txt";
        String algorithm3Name = "7.MST.txt";
        String algorithm4Name = "8.StronglyConnected.txt";


        System.out.println("The Algorithms in the set are run sequentally");
        System.out.println("To edit the input of each problem edit the text files in the project folder.");
        System.out.println("\nAlgorithm Name                              -> File Name");
        System.out.println("Algorithm 1: Acyclic Graphs                 -> " + algorithm1Name);
        System.out.println("Algorithm 2: BFS and DFS                    -> " + algorithm2Name);
        System.out.println("Algorithm 3: Minimum Spanning Tree          -> " + algorithm3Name);
        System.out.println("Algorithm 4: Strongly Connected Components  -> " + algorithm4Name);
        System.out.println();

        try {

            File file1 = new File("UsefulAlgorithms/inputFiles/"+algorithm1Name); // Selects the files to read data from
            File file2 = new File("UsefulAlgorithms/inputFiles/"+algorithm2Name);
            File file3 = new File("UsefulAlgorithms/inputFiles/"+algorithm3Name);
            File file4 = new File("UsefulAlgorithms/inputFiles/"+algorithm4Name);

            Scanner read1 = new Scanner(file1); // Makes a scanner from the files
            Scanner read2 = new Scanner(file2);
            Scanner read3 = new Scanner(file3);
            Scanner read4 = new Scanner(file4);


            System.out.println("\nAlgorithm 1 - Acyclic Graph:");
			numVertices = read1.nextInt(); // Finds the number of vertices and edges
			numEdges = read1.nextInt();	//  based on the first two values given in the file
			acyclicGraph = new AcyclicGraph(numEdges, numVertices);
			
			for (int i = 0; i < numEdges; i++) { // Creates edges for each edge given in the file
				acyclicGraph.addEdge(read1.nextInt(), read1.nextInt());
			}
			
			System.out.println(acyclicGraph.cycleCheck()); // Checks the cycle of the given graph


            System.out.println("\n\nAlgorithm 2 - BFS and DFS:");

            numVertices = read2.nextInt(); // Finds the number of vertices and edges
			numEdges = read2.nextInt();	//  based on the first two values given in the file
		        
			graph = new Graph(numEdges, numVertices); // Creates a graph based on the number of vertices
		  
			for (int i = 0; i < numEdges; i++) { // Creates edges for each edge given in the file
				graph.addEdge(read2.nextInt(), read2.nextInt());
			}
			
			//System.out.println("Vert = " + numVertices + ", Edges = " + numEdges);
			//System.out.println("Num of Vert: " + graph.numOfVertices());
			System.out.println("Current Graph :\n" + graph.toString()); // Prints out the current graph from the toString method   
			System.out.println("Degree of 2: " + graph.degreeVertex(2)); // Prints the degree of vertex 0
			graph.printAdjVertices(2); // Prints the adjacent vertices of vertex 0 
			graph.BFS(0); // Does and prints the Breadth First Search and Depth First Search starting at vertex 0
			graph.DFS(0);


            System.out.println("\n\nAlgorithm 3 - Minimum Spanning Tree:");
			numVertices = read3.nextInt(); // Finds the number of vertices and edges
			numEdges = read3.nextInt();	//  based on the first two values given in the file
			minSpanningTree = new MST(numEdges, numVertices);
			
			for (int i = 0; i < numEdges; i++) { // Creates edges for each edge given in the file
				minSpanningTree.addEdge(read3.nextInt(), read3.nextInt(), read3.nextInt());
			}
			
			System.out.println(minSpanningTree.solvePrim()); // Solves the MST using prims for the given graph


            System.out.println("\n\nAlgorithm 4 - Strongly Connected Components: ");
			
			numAntennas = read4.nextInt(); // Finds the number of antennas and cables
			numCables = read4.nextInt();
			
			stronglyConnectedComps = new StronglyConnectedComps(numCables, numAntennas); // Creates a new StronglyConnectedComps object based on the input
			
			for (int i = 0; i < numCables; i++) { // Add each cable to the StronglyConnectedComps object
				stronglyConnectedComps.addEdge(read4.nextInt(), read4.nextInt());
			}
			
			System.out.println(stronglyConnectedComps.stronglyConnected()); // Check if the antennas can communicate

            read1.close(); // Close all of the scanners
            read2.close();
            read3.close();
            read4.close();

        } catch(FileNotFoundException e) { // If one or more file isn't available
        System.out.println("\u001B[31mOne or more files could not be found -> Please Create Them\n" + e);
        System.out.println("\u001B[31mProgram Terminated");
    
        } catch(NoSuchElementException ne) { // If data is entered incorrectly
            System.out.println("\u001B[31mPlease enter data into the input text files and rerun the program.");
            System.out.println("\u001B[31mFor Algorithm 1 - Acyclic Graph, Algorithm 2 - BFS and DFS and Algorithm 4 - Strongly Connected Components: The expected "
                    + "\n\tinput is two integers representing the number of verticies and edges in a graph followed by the verticies that are connected by an edge.");
            System.out.println("\u001B[31mFor Algorithm 3 - Minimum Spanning Tree: The expected input is two integers representing the number of"
                    + "\n\tverticies and edges in a graph followed by the verticies that are connected by an edge and the \n\tweight of the edge.");
            System.out.println("\u001B[31mProgram Terminated");
        } catch(RuntimeException ex) { // If there are other errors within the program based on input
            System.out.println("\u001B[31mFor Algorithm 1 - Acyclic Graph, Algorithm 2 - BFS and DFS and Algorithm 4 - Strongly Connected Components: The expected "
                    + "\n\tinput is two integers representing the number of verticies and edges in a graph followed by the verticies that are connected by an edge.");
            System.out.println("\u001B[31mFor Algorithm 3 - Minimum Spanning Tree: The expected input is two integers representing the number of"
                    + "\n\tverticies and edges in a graph followed by the verticies that are connected by an edge and the \n\tweight of the edge.");
            System.out.println("\u001B[31mProgram Terminated " + ex);
        }
    }
}
