public class PrintOutput {

	/**
	 * Prints the alphabetically sorted list of combinations
	 */
	public static void printOutput() {

		for (String outputLine : MainProgWithFunctions.getSortedList()) {
			System.out.println(outputLine);
		}
	}

}
