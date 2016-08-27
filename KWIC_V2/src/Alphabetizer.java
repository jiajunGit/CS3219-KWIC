import java.util.Comparator;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

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
		m_SortedLines = new TreeSet<SortedEntry>( new KeywordComparator() );
		
		AbstractLine line;
		int lineOrder = 0;
		
		linesToSort.initialiseIterator();
		while( (line = linesToSort.next()) != null ) {
			m_SortedLines.add(new SortedEntry(line, linesToSort.getCurrentIteratedLineIndex(), lineOrder++));
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
			int lineOrder = m_SortedLines.size();
			
			if(newLine == null){
				return false;
			}
			
			m_SortedLines.add(new SortedEntry(newLine, newLineIdx, lineOrder));
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
			
			Iterator<SortedEntry> itr = m_SortedLines.iterator();
			SortedEntry lineEntry;
			
			while(itr.hasNext()){
				
				lineEntry = itr.next();
				if( m_OriginalLines.getLine(lineEntry.getLineIndex()) == null ){
					itr.remove();
				}
			}
			
			return m_LineStorage.setLines(m_SortedLines);
		}
		else{
			return sortAll(m_OriginalLines);
		}
	}
	
	private static class KeywordComparator implements Comparator<SortedEntry> {
		
		@Override
		public int compare(SortedEntry entry1, SortedEntry entry2) {
			
			AbstractLine line1 = entry1.getLine();
			AbstractLine line2 = entry2.getLine();
			
			String keyword1 = line1.getWord(KEYWORD_INDEX);
			String keyword2 = line2.getWord(KEYWORD_INDEX);
			
			int compareVal = keyword1.compareTo(keyword2);
			
			if(compareVal == 0){
				return compare(entry1.getLineOrder(),entry2.getLineOrder());
			}
			return compareVal;
		}
		
		private int compare( int val1, int val2 ) {
			
			if(val1 < val2){
				return 1;
			}
			else if( val2 < val1 ){
				return -1;
			}
			else{
				return 0;
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if( arg instanceof AbstractLineStorage ) {
			sortAll((AbstractLineStorage)arg);
		}
	}
	
	private AbstractLineStorage m_OriginalLines;
	private TreeSet<SortedEntry> m_SortedLines;
	private AlphaSortedLineStorage m_LineStorage;
	
	private final static int KEYWORD_INDEX = 0;
}