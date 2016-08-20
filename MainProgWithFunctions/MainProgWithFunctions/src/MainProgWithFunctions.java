/*
 * This Java file contains the solution which corresponds to the first solution on main program/subroutine
 * with shared data in the paper by Garlan & Shaw.
 * 
 * It takes in a set of ignore words and text to conduct Key Word In Context.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainProgWithFunctions {

    private static List<String> ignoreWords = new ArrayList<String>();

    public static void main(String[] args) {

        String[] inputLines = processUserInput();
        ArrayList<String> allUnsortedCombinations = executeShift(inputLines);
        ArrayList<String> allSortedCombinations = sortOutput(allUnsortedCombinations);
        printOutput(allSortedCombinations);
    }

    /**
     * Processes the ignore words and input lines
     * @return Processed input lines
     */
    private static String[] processUserInput() {

        Scanner sc = new Scanner(System.in);
                
        while (true) {
            String rawIgnoreInput = sc.nextLine();
            if (rawIgnoreInput.charAt(0) == (64)) {
                break;
            }
            
            String[] ignoreInput = rawIgnoreInput.split(", ");
            for (String word : ignoreInput) {
                ignoreWords.add(word);
            }
            
        }
        
        String rawInput = sc.nextLine();
        String[] inputLines = rawInput.split(", ");

        if (sc != null) {
            sc.close();
        }
        
        return inputLines;
    }

    /**
     * Executes the circular shift for the input lines
     * @return All of the possible combinations
     */
    private static ArrayList<String> executeShift(String[] originalInput) {

        ArrayList<String> lines = new ArrayList<String>();

        for (int i = 0; i < originalInput.length; i++) {
            String[] lineToShift = originalInput[i].split(" "); 
            int lengthOfLine = lineToShift.length;
            int notIgnoreWordCount = 0;
            Boolean isNotDuplicate = true;
            ArrayList<String> allCurrentLineShifts = new ArrayList<String>();

            while (isNotDuplicate) {

                Boolean isIgnoreWord = false;                
                
                for (int j = 0; j < ignoreWords.size(); j++) {
                    if (lineToShift[0].equals(ignoreWords.get(j))) {
                        isIgnoreWord = true;
                    }
                }
                
                if (!(isIgnoreWord)) {
                    notIgnoreWordCount += 1;
                }

                if (notIgnoreWordCount == 2) {
                    notIgnoreWordCount = 0;
                    
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append(lineToShift[0]);
                    for (int k = 1; k < lineToShift.length; k++) {
                        strBuf.append(" ").append(lineToShift[k]);
                    }

                    String shiftedLine = strBuf.toString();
                    
                    for (int l = 0; l < allCurrentLineShifts.size(); l++) {
                        if (shiftedLine.equals(allCurrentLineShifts.get(l))) {
                            isNotDuplicate = false;
                            break;
                        }
                    }
                    
                    if (isNotDuplicate) {
                        lines.add(shiftedLine);
                        allCurrentLineShifts.add(shiftedLine);
                    } else {
                        break;
                    }
                    
                } else {
                    String temp = lineToShift[0];
                    for (int m = 0; m < lengthOfLine - 1; m++) {
                        lineToShift[m] = lineToShift[m+1];
                    }
                    lineToShift[lengthOfLine - 1] = temp;
                }
                
            }
        }

        return lines;
    }

    /**
     * Conducts the sorting of the combinations by alphabetical order
     * @return Sorted output
     */
    private static ArrayList<String> sortOutput(ArrayList<String> combinationList) {

        Collections.sort(combinationList);
        return combinationList;
    }

    /**
     * Prints the alphabetically sorted list of combinations
     */
    private static void printOutput(ArrayList<String> sortedList) {

        for (String outputLine : sortedList) {
            System.out.println(outputLine);
        }
    }
}

