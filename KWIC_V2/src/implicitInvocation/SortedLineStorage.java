package implicitInvocation;

import java.util.Iterator;
import java.util.Observable;
import java.util.SortedSet;

public class SortedLineStorage extends Observable implements AbstractLineStorage {

	public SortedLineStorage() {
		
		m_SortedLines = null;
		m_CurrentItr = null;
		m_CurrentItrIdx = -1;
	}
	
	public boolean setLines( SortedSet<SortedEntry> sortedLineIdx ) {
		
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
		SortedEntry entry;
		Iterator<SortedEntry> itr = m_SortedLines.iterator();
		
		while( itr.hasNext() ){
			
			entry = itr.next();
			if( entry != null && i == lineIdx ){
				return entry.getLine();
			}
			++i;
		}
		return null;
	}
	
	// Try not to use this function in this class because it might result in worst case 0(n) runtime
	public String getWord( int lineIdx, int wordIdx ) {
		
		AbstractLine line = getLine(lineIdx);
		return (line != null ? line.getWord(wordIdx) : null);
	}
	
	public void initialiseIterator() {
		
		if( m_SortedLines == null ){
			m_CurrentItr = null;
		}
		else{
			m_CurrentItr = m_SortedLines.iterator();
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

		SortedEntry entry = m_CurrentItr.next();
		if( entry == null || entry.getLine() == null ) {
			
			m_CurrentItr = null;
			m_CurrentItrIdx = -1;
			return null;
		}
		++m_CurrentItrIdx;
		
		return entry.getLine();
	}
	
	private SortedSet<SortedEntry> m_SortedLines;
	private Iterator<SortedEntry> m_CurrentItr;
	private int m_CurrentItrIdx;
}
