import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AlphaSortedLineStorage extends Observable implements AbstractLineStorage {

	public AlphaSortedLineStorage() {
		
		m_SortedLines = null;
		m_CurrentItr = null;
		m_CurrentItrIdx = -1;
	}
	
	public boolean setLines( Map<AbstractLine, Integer> sortedLineIdx ) {
		
		m_CurrentItr = null;
		m_CurrentItrIdx = -1;
		
		if( sortedLineIdx != null ){
			
			m_SortedLines = sortedLineIdx;
			return true;
		}
		else {
			
			m_SortedLines = null;
			return false;
		}
	}
	
	public StorageType getType() {
		return StorageType.ALPHA_SORTED_STORAGE;
	}
	
	public int getLineCount() {
		
		if( m_SortedLines == null ){
			return 0;
		}
		return m_SortedLines.size();
	}
	
	public boolean isEmpty() {
		return getLineCount() <= 0;
	}
	
	// Try not to use this function in this class because it might result in worst case 0(n) runtime
	public AbstractLine getLine( int lineIdx ) {
		
		if( m_SortedLines == null || lineIdx < 0 || lineIdx >= m_SortedLines.size() ){
			return null;
		}
		
		int i = 0;
		AbstractLine originalLine;
		Iterator<AbstractLine> itr = m_SortedLines.keySet().iterator();
		
		while( itr.hasNext() ){
			
			originalLine = itr.next();
			if( i == lineIdx ){
				return originalLine;
			}
			++i;
		}
		return null;
	}
	
	// Try not to use this function in this class because it might result in worst case 0(n) runtime
	public String getWord( int lineIdx, int wordIdx ) {
		
		AbstractLine line = getLine(lineIdx);
		return line.getWord(wordIdx);
	}
	
	public void initialiseIterator() {
		
		if( m_SortedLines == null ){
			m_CurrentItr = null;
		}
		else{
			m_CurrentItr = m_SortedLines.keySet().iterator();
		}
		m_CurrentItrIdx = -1;
	}
	
	public int getCurrentIteratedLineIndex() {
		return m_CurrentItrIdx;
	}
	
	public boolean hasNext() {
		
		if( m_CurrentItr == null || m_SortedLines == null ){
			return false;
		}
		return m_CurrentItr.hasNext();
	}
	
	public AbstractLine next() {
		
		if(!hasNext()){
			return null;
		}

		AbstractLine line = m_CurrentItr.next();
		if( line == null ) {
			
			m_CurrentItr = null;
			m_CurrentItrIdx = -1;
			return null;
		}
		++m_CurrentItrIdx;
		
		return line;
	}
	
	private Map<AbstractLine, Integer> m_SortedLines;
	private Iterator<AbstractLine> m_CurrentItr;
	private int m_CurrentItrIdx;
}
