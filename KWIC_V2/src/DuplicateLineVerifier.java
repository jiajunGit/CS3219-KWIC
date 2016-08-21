import java.util.HashMap;
import java.util.LinkedList;

public class DuplicateLineVerifier {

	public DuplicateLineVerifier( AbstractLineStorage lineStorage ) {
		m_DuplicateTable = new HashMap<Integer, LinkedList<Integer>>();
		m_LineStorage = lineStorage;
	}
	
	public boolean addLine( int lineIdx, String line ) {
		
		if( m_LineStorage == null || line == null ){
			return false;
		}
		
		int hashCode = line.hashCode();
		LinkedList<Integer> lineIdxList = m_DuplicateTable.get(hashCode);
		boolean isAdded;
		
		if(lineIdxList == null) {
			
			lineIdxList = new LinkedList<Integer>();
			isAdded = lineIdxList.add(lineIdx);
			m_DuplicateTable.put(hashCode, lineIdxList);
		}
		else {
			isAdded = lineIdxList.add(lineIdx);
		}
		
		return isAdded;
	}
	
	public boolean isDuplicate( String line ) {
		
		if( m_LineStorage == null || line == null ){
			return false;
		}
		
		int hashCode = line.hashCode();
		
		LinkedList<Integer> lineIdxList = m_DuplicateTable.get(hashCode);
		
		if( lineIdxList == null ){
			return false;
		}
		
		AbstractLine tempLine;
		for( int lineIdx : lineIdxList ){
			tempLine = m_LineStorage.getLine(lineIdx);
			if( tempLine != null && line.compareToIgnoreCase(tempLine.toString()) == 0 ){
				return true;
			}
		}
		
		return false;
	}
	
	private HashMap<Integer, LinkedList<Integer>> m_DuplicateTable;
	private AbstractLineStorage m_LineStorage;
}
