package substringsearch;

import java.util.ArrayList;
import java.util.Scanner;

public class BoyerMooreSubstringSearch {
	private String pattern, text; // Instance Variables
	private int N, M;
	private int right [];
	private ArrayList<Integer> indices;
	public BoyerMooreSubstringSearch(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		this.N = text.length(); // length of the text and pattern
		this.M = pattern.length();
		this.indices = new ArrayList<Integer>(); // Creates an array list to hold the locations where the pattern is found
		this.right = new int [256];
		//String list = "";
		
		// Precompute the index of the rightmost occurrence of character i in pattern
		//  (-1 if the character is not in the pattern)
		for (int i = 0; i < 256; i++) {
			this.right[i] = -1;
		}
		
		for (int i = 0; i < this.M; i++) {
			this.right[this.pattern.charAt(i)] = i;
		}
		
		search(); // Runs the search
		
		/*
		// Prints the output depending on the number of indices where the pattern happens
		if(indices.size() == 1) {
			System.out.println("Pattern " + pattern + " found at index " + indices.get(0));
		} else if(indices.size() == 0) {
			System.out.println("The pattern " + pattern + " could not be found in the text.");
		} else {
			list += indices.get(0);
			for (int i = 1; i < indices.size(); i++) {
				list += ", " + indices.get(i);
			}
			list += ".";
			System.out.println("Pattern " + pattern + " found at indices " + list);
		}*/
				
	}
	
	private void search() {
		int skip;
		for (int i = 0; i <= this.N - this.M; i += skip) {
			skip = 0;
			for (int j = this.M - 1; j >= 0; j--) {
				if (this.pattern.charAt(j) != this.text.charAt(i + j)) {
					// Compute the skip value
					skip = Math.max(1, j - this.right[this.text.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0) {
				// Add the index to the list of indices
				indices.add(i);
				skip++;
			}
		}
	}

	public String solution(){
		// Prints the output depending on the number of indices where the pattern happens
		String list = "", output = "";
		if(indices.size() == 1) {
			output = "Pattern " + pattern + " found at index " + indices.get(0);
		} else if(indices.size() == 0) {
			output = "The pattern " + pattern + " could not be found in the text.";
		} else {
			list += indices.get(0);
			for (int i = 1; i < indices.size(); i++) {
				list += ", " + indices.get(i);
			}
			list += ".";
			output = "Pattern " + pattern + " found at indices " + list;
		}

		return output;
	}
	
	
	public static void main(String args[]) {
		String pattern, text;
		Scanner in = new Scanner(System.in);
		System.out.println("Boyer Moore Substring Search");
		// Gets input from the user
		System.out.print("Enter the text: ");
		text = in.nextLine();
		System.out.print("Enter the Pattern: ");
		pattern = in.nextLine();
		// Crates an object for the Substring Search
		BoyerMooreSubstringSearch search = new BoyerMooreSubstringSearch(text, pattern);
		System.out.println(search.solution());
		in.close();
	}
}
