/*
 * This Java file contains the solution which corresponds to the first solution on main program/subroutine
 * with shared data in the paper by Garlan & Shaw.
 * 
 * It takes in a set of ignore words and text to conduct Key Word In Context.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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

        String rawIgnoreWords = sc.nextLine();
        ignoreWords = Arrays.asList(rawIgnoreWords.split(", "));

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

        for (int i = 0; i < originalInput.length; i++) {
            String[] lineToShift = originalInput[i].split(" "); 
            int lengthOfLine = lineToShift.length;
            int notIgnoreWordCount = 0;
            Boolean isNotDuplicate = true;

            while (isNotDuplicate) {

                for (int j = 0; j < ignoreWords.size(); j++) {
                    if (!(lineToShift[0].equals(ignoreWords.get(j)))) {
                        notIgnoreWordCount += 1;
                        break;
                    }
                }

                if (notIgnoreWordCount == 2) {
                    notIgnoreWordCount = 0;
                    
                    StringBuffer strBuf = new StringBuffer();
                    for (int k = 0; k < lineToShift.length; k++) {
                        strBuf.append(" ").append(lineToShift[k]);
                    }

                    String shiftedLine = strBuf.toString();
                    lines.add(shiftedLine);
                    
                    // TODO: Add duplicate check (to determine when end of loop)
                    
                    break;
                    
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
