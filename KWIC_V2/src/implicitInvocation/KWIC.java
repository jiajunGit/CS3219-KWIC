package implicitInvocation;

public class KWIC {

	private static final String INPUT_FILENAME = "TestCases/input4.in";
	
	public static void main( String []args ){
		
		ImplicitInvocationArchitecture control = new ImplicitInvocationArchitecture();
		
		control.setup();
		
		control.run(INPUT_FILENAME);
	}
}