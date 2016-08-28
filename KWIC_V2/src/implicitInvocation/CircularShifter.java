package implicitInvocation;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class CircularShifter extends Observable implements Observer {

	public CircularShifter( CircularLineStorage lineStorage, IgnoreWordStorage ignoreWordStorage ) {
		m_LineStorage = lineStorage;
		m_IgnoreWords = ignoreWordStorage;
	}
	
	public boolean shift( AbstractLineStorage lineStorage, int changedLineIdx ) {
		
		if( lineStorage == null || m_LineStorage == null || m_IgnoreWords == null ) {
			return false;
		}
		
		boolean isShifted;
		
		if( changedLineIdx >= 0 ){
			isShifted = shift( changedLineIdx, lineStorage.getLine(changedLineIdx) );
		}
		else {
			isShifted = shift(lineStorage);
		}
		
		if( isShifted ) {
			
			setChanged();
			notifyObservers( m_LineStorage );
			clearChanged();
		}
		
		return isShifted;
	}
	
	private boolean shift( AbstractLineStorage lineStorage ){
		
		AbstractLine line;
		
		lineStorage.initialiseIterator();
		while( (line = lineStorage.next()) != null ){
			
			if( !shift(lineStorage.getCurrentIteratedLineIndex(), line) ){
				return false;
			}
		}
		return true;
	}
	
	private boolean shift( int lineIdx, AbstractLine line ) {
		
		if( line == null ){
			return false;
		}
		
		HashSet<String> duplicateWords = new HashSet<String>();
		String word;
		
		for( int i = 0, size = line.getWordCount(); i < size; ++i ) {
			
			word = line.getWord(i);
			
			if( !m_IgnoreWords.isIgnoredWord(word) && !duplicateWords.contains(word) ){
				
				if(m_LineStorage.addLine(line, lineIdx, i) < 0){
					return false;
				}
				duplicateWords.add(word);
			}
		}
		return true;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if( arg instanceof AbstractLineStorage ){
			shift((AbstractLineStorage)arg, -1);
		}
	}
	
	private CircularLineStorage m_LineStorage;
	private IgnoreWordStorage m_IgnoreWords;
}
