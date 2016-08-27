/*
 * This Java file contains the solution which corresponds to the first solution on main program/subroutine
 * with shared data in the paper by Garlan & Shaw.
 * 
 * It takes in a set of ignore words and text to conduct Key Word In Context.
 */

import java.util.List;
import java.util.ArrayList;

public class MainProgWithFunctions {

    private static List<String> ignoreWords = new ArrayList<String>();
    private static ArrayList<String> unsortedList = new ArrayList<String>();
    private static ArrayList<String> sortedList = new ArrayList<String>();
    
    public static void main(String[] args) {

        String[] inputLines = ProcessUserInput.processUserInput();
        ExecuteShift.executeShift(inputLines);
        SortOutput.sortOutput();
        PrintOutput.printOutput();
    }

    public static List<String> getIgnoreWords() {
    	return ignoreWords;
    }
    
    public static void setIgnoreWords(List<String> ignoreWords) {
    	MainProgWithFunctions.ignoreWords = ignoreWords;
    }
    
    public static ArrayList<String> getSortedList() {
    	return sortedList;
    }
    
    public static void setSortedList(ArrayList<String> sortedList) {
    	MainProgWithFunctions.sortedList = sortedList;
    }
    
    public static ArrayList<String> getUnsortedList() {
    	return unsortedList;
    }
    
    public static void setUnsortedList(ArrayList<String> unsortedList) {
    	MainProgWithFunctions.unsortedList = unsortedList;
    }
        
}