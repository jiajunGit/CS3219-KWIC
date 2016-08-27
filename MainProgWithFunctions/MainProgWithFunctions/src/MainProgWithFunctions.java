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
    
    public static void main(String[] args) {

        String[] inputLines = ProcessUserInput.processUserInput();
        ArrayList<String> unsortedList = ExecuteShift.executeShift(inputLines);
        ArrayList<String> sortedList = SortOutput.sortOutput(unsortedList);
        PrintOutput.printOutput(sortedList);
    }

    public static List<String> getIgnoreWords() {
    	return ignoreWords;
    }
    
    public static void setIgnoreWords(List<String> ignoreWords) {
    	MainProgWithFunctions.ignoreWords = ignoreWords;
    }
        
}