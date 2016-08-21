import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Iterator;
import java.util.Map.Entry;

public class InputLineStorage extends Observable implements AbstractLineStorage {

	public InputLineStorage(){
		
		lines = new LinkedHashMap<Integer, InputLine>();
		currentLineItr = null;
		currentItrLineIdx = -1;
	}
	
	public StorageType getType() {
		return StorageType.INPUT_LINE_STORAGE;
	}
	
	public int getLineCount() {
		return lines.size();
	}
	
	public boolean isEmpty(){
		return (lines.size() <= 0);
	}
	
	public int addLine() {
		lines.put(lines.size(), new InputLine());
		return lines.size() - 1;
	}
	
	public InputLine getLine( int lineIdx ) {
		return lines.get(lineIdx);
	}
	
	public boolean addWord( int lineIdx, String word ) {
		
		InputLine line = lines.get(lineIdx);
		
		if(line == null){
			return false;
		}
		return line.addWord(word);
	}
	
	public String getWord( int lineIdx, int wordIdx ) {
		
		InputLine line = lines.get(lineIdx);
		
		if(line == null){
			return null;
		}
		return line.getWord(wordIdx);
	}
	
	public void initialiseIterator() {
		currentLineItr = lines.entrySet().iterator();
		currentItrLineIdx = -1;
	}
	
	public int getCurrentIteratedLineIndex(){
		return currentItrLineIdx;
	}
	
	public boolean hasNext(){
		
		if( currentLineItr == null ) {
			return false;
		}
		return currentLineItr.hasNext();
	}
	
	public InputLine next() {
		
		if(!hasNext()){
			currentItrLineIdx = -1;
			return null;
		}
		Entry<Integer, InputLine> line = currentLineItr.next();
		currentItrLineIdx = line.getKey();
		return line.getValue();
	}
	
	// Only for debug
	public void printAll(){
		
		initialiseIterator();
		InputLine line;
		
		while( (line = next()) != null ) {
			System.out.println(line.toString());
		}
	}
	
	private LinkedHashMap<Integer, InputLine> lines;
	private Iterator<Entry<Integer, InputLine>> currentLineItr;
	private int currentItrLineIdx;
}
