
public class KWIC {

	private static final String INPUT_FILENAME = "TestCases/input3.in";
	
	public static void main( String []args ){
		
		Controller control = new Controller();
		
		control.setup();
		
		control.run(INPUT_FILENAME);
	}
}