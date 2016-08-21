
public class KWIC {

	private static final String INPUT_FILENAME = "C:\\Users\\WorkBench\\Desktop\\test.txt"; // Change to a valid file name to run
	
	public static void main( String []args ){
		
		Controller control = new Controller();
		
		control.setup();
		
		control.run(INPUT_FILENAME);
	}
}