package implicitInvocation;

public class ImplicitInvocationArchitecture {

	public ImplicitInvocationArchitecture() {
		
		// Initialize data modules
		ignoreWords = new IgnoreWordStorage();
		inputLineStorage = new InputLineStorage();
		circularLineStorage = new CircularLineStorage();
		alphaSortedLineStorage = new SortedLineStorage();
		
		// Initialize function modules
		lineReader = new LineReader(inputLineStorage, ignoreWords);
		circularShifter = new CircularShifter(circularLineStorage, ignoreWords);
		alphabetizer = new Alphabetizer(alphaSortedLineStorage);
		printer = new Printer(ignoreWords);
		
		hasSetup = false;
	}
	
	public void setup() {
		
		lineReader.addObserver(circularShifter);
		circularShifter.addObserver(alphabetizer);
		alphabetizer.addObserver(printer);
		
		hasSetup = true;
	}
	
	public boolean run() {
		
		if( !hasSetup ) {
			return false;
		}
		
		lineReader.read();
		
		return true;
	}
	
	public boolean run( String fileName ) {
		
		if( !hasSetup ) {
			return false;
		}
		
		lineReader.read(fileName);
		
		return true;
	}
	
	// Data Modules
	private IgnoreWordStorage ignoreWords;
	private InputLineStorage inputLineStorage;
	private CircularLineStorage circularLineStorage;
	private SortedLineStorage alphaSortedLineStorage;
	
	// Function Modules
	private LineReader lineReader;
	private CircularShifter circularShifter;
	private Alphabetizer alphabetizer;
	private Printer printer;
	
	private boolean hasSetup;
}
