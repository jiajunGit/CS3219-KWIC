import java.util.ArrayList;
import java.util.Collections;

public class SortOutput {

    /**
     * Conducts the sorting of the combinations by alphabetical order
     * @return Sorted output
     */
	public static void sortOutput() {

		ArrayList<String> listToSort = MainProgWithFunctions.getUnsortedList();
		Collections.sort(listToSort);
        MainProgWithFunctions.setSortedList(listToSort);
    }

	
}
