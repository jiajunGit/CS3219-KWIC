/*
 * This Java file contains the solution which corresponds to the first solution on main program/subroutine
 * with shared data in the paper by Garlan & Shaw.
 * 
 * It takes in a set of ignore words and text to conduct Key Word In Context.
 */

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainProgWithFunctions {
			
	private static List<String> ignoreWords = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		String[] inputLines = processUserInput();
		ArrayList<String> completeSetOfCombinations = executeShift(inputLines);
		
		assertTrue("Output was not sorted successfully!", sortOutput());
		assertTrue("Printing the output to screen failed!", printOutput());
	}
	
	/**
	 * Processes the ignore words and input lines
	 * @return Processed input lines
	 */
	private static String[] processUserInput() {
		
		Scanner sc = new Scanner(System.in);
		
		String rawIgnoreWords = sc.nextLine();
		ignoreWords = Arrays.asList(rawIgnoreWords.split("\\s*,\\s*"));
		
		String rawInput = sc.nextLine();
		String[] inputLines = rawInput.split(", ");
				
		sc.close();
		return inputLines;
	}
	
	/**
	 * Executes the circular shift for the input lines
	 * @return All of the possible combinations
	 */
	private static ArrayList<String> executeShift(String[] originalInput) {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		return lines;
	}
	
	private static boolean sortOutput() {
		return true;
	}
	
	private static boolean printOutput() {
		return true;
	}
}
