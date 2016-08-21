import java.util.Comparator;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Alphabetizer extends Observable implements Observer {

	public Alphabetizer( AlphaSortedLineStorage lineStorage ) {
		
		m_LineStorage = lineStorage;
		m_SortedLines = null;
		m_OriginalLines = null;
	}
	
	public boolean sortAll( AbstractLineStorage linesToSort ) {
		
		if( m_LineStorage == null || linesToSort == null ) {
			return false;
		}
		
		m_OriginalLines = linesToSort;
		m_SortedLines = new TreeMap<AbstractLine, Integer>( new KeywordComparator() );
		
		AbstractLine line;
		
		linesToSort.initialiseIterator();
		while( (line = linesToSort.next()) != null ) {
			m_SortedLines.put(line, linesToSort.getCurrentIteratedLineIndex());
		}
		
		boolean isSet = m_LineStorage.setLines(m_SortedLines);
		
		if(isSet) {
			
			setChanged();
			notifyObservers( m_LineStorage );
			clearChanged();
		}
		
		return isSet;
	}
	
	public boolean sort( int newLineIdx ) {
		
		if( m_LineStorage == null || m_OriginalLines == null ) {
			return false;
		}
		
		if( m_SortedLines != null ) {
			
			AbstractLine newLine = m_OriginalLines.getLine(newLineIdx);
			
			if(newLine == null){
				return false;
			}
			
			m_SortedLines.put( newLine, newLineIdx );
			
			return m_LineStorage.setLines(m_SortedLines);
		}
		else {
			return sortAll(m_OriginalLines);
		}
	}
	
	public boolean sortAfterDelete() {
		
		if( m_LineStorage == null || m_OriginalLines == null ) {
			return false;
		}
		
		if( m_SortedLines != null ) {
			
			Iterator<Entry<AbstractLine, Integer>> itr = m_SortedLines.entrySet().iterator();
			Entry<AbstractLine, Integer> lineEntry;
			
			while(itr.hasNext()){
				
				lineEntry = itr.next();
				if( m_OriginalLines.getLine(lineEntry.getValue()) == null ){
					itr.remove();
				}
			}
			
			return m_LineStorage.setLines(m_SortedLines);
		}
		else{
			return sortAll(m_OriginalLines);
		}
	}
	
	static class KeywordComparator implements Comparator<AbstractLine> {
		
		@Override
		public int compare(AbstractLine lineIdx1, AbstractLine lineIdx2) {
			
			String keyword1 = lineIdx1.getWord(KEYWORD_INDEX);
			if( keyword1 == null ){
				return 0;
			}
			
			String keyword2 = lineIdx2.getWord(KEYWORD_INDEX);
			if( keyword2 == null ){
				return 0;
			}
			
			return keyword1.compareTo(keyword2);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if( arg instanceof AbstractLineStorage ) {
			sortAll((AbstractLineStorage)arg);
		}
	}
	
	private AbstractLineStorage m_OriginalLines;
	private TreeMap<AbstractLine, Integer> m_SortedLines;
	private AlphaSortedLineStorage m_LineStorage;
	
	private final static int KEYWORD_INDEX = 0;
}