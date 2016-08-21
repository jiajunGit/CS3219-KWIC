
public class KWIS {

	private static final String INPUT_FILENAME = "TestCases/input1.in";
	
	public static void main( String []args ){
		
		Controller control = new Controller();
		
		control.setup();
		
		control.run(INPUT_FILENAME);
	}
}