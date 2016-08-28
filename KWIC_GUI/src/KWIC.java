import sharedData.SharedDataArchitecture;
import implicitInvocation.ImplicitInvocationArchitecture;

public class KWIC {
	
	public static void main( String []argv ) {
		
		try{
		
			if( argv.length == 1 ){
				
				int archType = Integer.parseInt(argv[0]);
				
				switch(archType){
				
					case 1:
						
						if(initiateSharedDataArch()){
							return;
						}
						break;
						
					case 2:
						
						if( initiateImplicitInvocationArch() ){
							return;
						}
						break;
						
					default:
						break;
				}
			}
			
		} catch(NumberFormatException e) {}
		
		displayHelp();
	}
	
	private static boolean initiateSharedDataArch() {
		
		SharedDataArchitecture control = new SharedDataArchitecture();
		return control.run();
	}
	
	private static boolean initiateImplicitInvocationArch() {
		
		ImplicitInvocationArchitecture control = new ImplicitInvocationArchitecture();
		control.setup();
		return control.run();
	}
	
	private static void displayHelp() {
		
		System.out.println( "\nUsage: java -jar \"KWIC.jar\" [Architecture Type] [Optional: < inputFile.txt]\n" );
		System.out.println( "Architecture Type (use without square brackets []):" );
		System.out.println( "1 for Main/subroutine architecture with shared data" );
		System.out.println( "2 for Object-Oriented architecture with observer pattern\n" );
		
		System.out.println( "For example:\n" );
		System.out.print( "If you want to use Main/subroutine architecture with shared data with an input file, " );
		System.out.println( "you can type: java KWIC 1 < exampleInputFileName.txt\n" );
		
		System.out.print( "If you want to use Main/subroutine architecture with shared data without an input file, " );
		System.out.println( "you can type: java KWIC 1\n" );
		
		System.out.println( "Example of user input/file input format:" );
		System.out.println( "for, a, it" );
		System.out.println( "@" );
		System.out.println( "Fast and Furious" );
		System.out.println( "Man of Steel" );
		
		System.out.println("\nSo the general user input/file input format (reference to the previous example):");
		System.out.println( "IgnoreWord, IgnoreWord, IgnoreWord, ..." );
		System.out.println( "character to indicate end of ignore words and start of lines to process" );
		System.out.println( "line to process" );
		System.out.println( "line to process" );
		System.out.println( "..." );
	}
}
