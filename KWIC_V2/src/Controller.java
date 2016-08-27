
public class Controller {

	public Controller() {
		
		// Initialize data modules
		inputLineStorage = new InputLineStorage();
		circularLineStorage = new CircularLineStorage();
		alphaSortedLineStorage = new AlphaSortedLineStorage();
		
		// Initialize function modules
		lineReader = new LineReader(inputLineStorage);
		circularShifter = new CircularShifter(circularLineStorage);
		alphabetizer = new Alphabetizer(alphaSortedLineStorage);
		printer = new Printer();
		
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
	private InputLineStorage inputLineStorage;
	private CircularLineStorage circularLineStorage;
	private AlphaSortedLineStorage alphaSortedLineStorage;
	
	// Function Modules
	private LineReader lineReader;
	private CircularShifter circularShifter;
	private Alphabetizer alphabetizer;
	private Printer printer;
	
	private boolean hasSetup;
}
