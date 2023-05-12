package substringsearch;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;


public class RabinKarpSubstringSearch {
	// Instance variables
	private long patHash; // pattern hash value
	private int M;		  // pattern length
	private long Q;		  // modulus
	private int R;		  // radix
	private long RM;	  // R^(M-1)%Q
	private ArrayList<Integer> indices;
	private String pattern, text;
	private int N;
	
	public RabinKarpSubstringSearch(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		this.M = pattern.length();
		this.N = text.length();
		this.R = 256;
		this.Q = longRandomPrime(); // A large prime that avoids overflow 
		this.indices = new ArrayList<Integer>();
		
		// Precompute (R^(M-1))(mod Q)
		this.RM = 1;
		for (int i = 1; i <= M - 1; i++) {
			RM = (R * RM) % Q;
		}
		this.patHash = hash(pattern, M);
		
		search(); // Runs the search
		
		/*
		// Prints the output depending on the number of indices where the pattern happens
		String list = "";
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
	
	// Compute hash for M-digit key
	private long hash(String key, int M) {
		long h = 0;
		for (int j = 0; j < M; j++) {
			h = (R * h + key.charAt(j)) % Q;
		}
		return h;
	}
	
	private void search() {
		long txtHash = hash(text, M);
		
		// If the hashes are initially equal the substring exists at index 0. 
		if (patHash == txtHash) {
			indices.add(0);
			
		}
		
		for (int i = M; i < N; i++) {
			txtHash = (txtHash + Q - RM * text.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + text.charAt(i)) % Q;
			if (patHash == txtHash) {
				indices.add(i - M + 1);
			}
		}
	}
	
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
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
		System.out.println("Rabin Karp Substring Search");
		System.out.print("Enter the text: ");
		text = in.nextLine();
		System.out.print("Enter the Pattern: ");
		pattern = in.nextLine();
		RabinKarpSubstringSearch search = new RabinKarpSubstringSearch(text, pattern);
		System.out.println(search.solution());
		in.close();
	}
	
}
