import java.util.ArrayList;

public class ExecuteShift {

	/**
     * Executes the circular shift for the input lines
     * @return All of the possible combinations
     */
	public static void executeShift(String[] originalInput) {

        ArrayList<String> lines = new ArrayList<String>();

        for (int i = 0; i < originalInput.length; i++) {
        	
            String[] lineToShift = originalInput[i].split(" "); 
         
            int lengthOfLine = lineToShift.length;
            for (int x = 0; x < lengthOfLine; x++) {
            	Boolean isNotIgnoreWord = true;
            	for (int y = 0; y < MainProgWithFunctions.getIgnoreWords().size(); y++) {
            		if (lineToShift[x].equals(MainProgWithFunctions.getIgnoreWords().get(y))) {
            			isNotIgnoreWord = false;
            		}
            	}
            	if (isNotIgnoreWord) {
            		if (lineToShift[x].length() > 1) {
            			lineToShift[x] = lineToShift[x].substring(0, 1).toUpperCase() + lineToShift[x].substring(1);
            		} else {
            			lineToShift[x] = lineToShift[x].toUpperCase();
            		}
            	}
            }
            
            int notIgnoreWordCount = 0;
            Boolean isNotDuplicate = true;
            ArrayList<String> allCurrentLineShifts = new ArrayList<String>();

            while (isNotDuplicate) {

                Boolean isIgnoreWord = false;                
                
                for (int j = 0; j < MainProgWithFunctions.getIgnoreWords().size(); j++) {
                    if (lineToShift[0].equals(MainProgWithFunctions.getIgnoreWords().get(j))) {
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

        MainProgWithFunctions.setUnsortedList(lines);
    }


	
}
