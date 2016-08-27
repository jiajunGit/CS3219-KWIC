import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessUserInput {

	/**
     * Processes the ignore words and input lines
     * @return Processed input lines
     */
    public static String[] processUserInput() {

        Scanner sc = new Scanner(System.in);
        List<String> ignoreWords = new ArrayList<String>();
                
        while (true) {
            String rawIgnoreInput = sc.nextLine();
            if (rawIgnoreInput.charAt(0) == (64)) {
                break;
            }
            
            rawIgnoreInput = rawIgnoreInput.toLowerCase();
            String[] ignoreInput = rawIgnoreInput.split(", ");
            for (String word : ignoreInput) {
                ignoreWords.add(word);
            }
            
        }
        
        MainProgWithFunctions.setIgnoreWords(ignoreWords);
        
        String rawInput = sc.nextLine();
        rawInput = rawInput.toLowerCase();
        String[] inputLines = rawInput.split(", ");

        if (sc != null) {
            sc.close();
        }
        
        return inputLines;
    }

	
}
