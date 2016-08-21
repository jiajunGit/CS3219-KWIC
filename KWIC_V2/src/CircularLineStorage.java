import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Iterator;
import java.util.Map.Entry;

public class CircularLineStorage extends Observable implements AbstractLineStorage {

	public CircularLineStorage() {
		
		lines = new LinkedHashMap<Integer, CircularLine>();
		currentLineItr = null;
		currentItrLineIdx = -1;
	}
	
	public StorageType getType(){
		return StorageType.CIRCULAR_LINE_STORAGE;
	}
	
	public int getLineCount() {
		return lines.size();
	}
	
	public boolean isEmpty() {
		return lines.size() <= 0;
	}
	
	public int addLine( AbstractLine inputLine, int inputLineIdx, int startOffset ) {
		
		if( inputLine == null ){
			return -1;
		}
		
		CircularLine newLine = new CircularLine( inputLine, inputLineIdx, startOffset );
		lines.put(lines.size(), newLine);
		return lines.size() - 1;
	}
	
	public CircularLine getLine( int lineIdx ) {
		return lines.get(lineIdx);
	}
	
	public String getWord( int lineIdx, int wordIdx ) {
		
		CircularLine line = lines.get(lineIdx);
		return (line != null ? line.getWord(wordIdx) : null);
	}
	
	public void initialiseIterator() {
		currentLineItr = lines.entrySet().iterator();
		currentItrLineIdx = -1;
	}
	
	public int getCurrentIteratedLineIndex() {
		return currentItrLineIdx;
	}
	
	public boolean hasNext() {
		
		if( currentLineItr == null ){
			return false;
		}
		return currentLineItr.hasNext();
	}
	
	public CircularLine next() {
		
		if( !hasNext() ){
			currentItrLineIdx = -1;
			return null;
		}
		
		Entry<Integer, CircularLine> line = currentLineItr.next();
		currentItrLineIdx = line.getKey();
		return line.getValue();
	}
	
	private LinkedHashMap< Integer, CircularLine > lines;
	private Iterator<Entry<Integer, CircularLine>> currentLineItr;
	private int currentItrLineIdx;
}
