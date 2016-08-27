
public class SortedEntry {

	public SortedEntry( AbstractLine line, int lineIndex, int lineOrder ) {
		
		m_Line = line;
		m_LineIndex = lineIndex;
		m_LineOrder = lineOrder;
	}
	
	public AbstractLine getLine(){
		return m_Line;
	}
	
	public int getLineIndex(){
		return m_LineIndex;
	}
	
	public int getLineOrder(){
		return m_LineOrder;
	}
	
	private AbstractLine m_Line;
	private int m_LineIndex;
	private int m_LineOrder;
}
