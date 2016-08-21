
public class CircularLine implements AbstractLine {

	public CircularLine(AbstractLine inputLine, int inputLineIdx, int startOffset) {
		
		m_OriginalLine = inputLine;
		m_CurrentItrIdx = -1;
		
		if( m_OriginalLine != null ){
			
			if( startOffset < 0 || startOffset >= inputLine.getWordCount() ){
				m_StartOffset = 0;
				
			}
			else{
				m_StartOffset = startOffset;
			}
			
			m_OriginalLineIdx = inputLineIdx;
		}
	}
	
	public LineType getType() {
		return LineType.CIRCULAR_LINE;
	}
	
	@Override
	public String toString() {
		
		String line = "";
		
		if( m_OriginalLine == null ){
			return line;
		}
		
		int wordCount = m_OriginalLine.getWordCount();
		int i = m_StartOffset;
		
		for( int end = wordCount + m_StartOffset - 1; i < end; ++i ){
			line += (m_OriginalLine.getWord(i%wordCount) + " ");
		}
		
		if(wordCount > 0){
			line += m_OriginalLine.getWord(i%wordCount);
		}
		
		return line;
	}
	
	public StringBuilder getString() {
		
		StringBuilder line = new StringBuilder();
		
		if( m_OriginalLine == null ){
			return line;
		}
		
		int wordCount = m_OriginalLine.getWordCount();
		int i = m_StartOffset;
		
		for( int end = wordCount + m_StartOffset - 1; i < end; ++i ){
			line.append(m_OriginalLine.getWord(i%wordCount));
			line.append(" ");
		}
		
		if(wordCount > 0){
			line.append(m_OriginalLine.getWord(i%wordCount));
		}
		
		return line;
	}
	
	public String getWord( int wordIdx ) {
		
		if( m_OriginalLine != null && wordIdx >= 0 && wordIdx < m_OriginalLine.getWordCount() ) {
			return m_OriginalLine.getWord((wordIdx + m_StartOffset)%m_OriginalLine.getWordCount());
		}
		return null;
	}
	
	public int getWordCount() {
		return m_OriginalLine.getWordCount();
	}
	
	public int getInputLineIdx() {
		return m_OriginalLineIdx;
	}
	
	public void initialiseIterator() {
		initialiseIterator(0);
	}
	
	public void initialiseIterator( int startIdx ) {
		
		m_CurrentItrIdx = -1;
		if( m_OriginalLine != null && startIdx >= 0 && startIdx < m_OriginalLine.getWordCount() ){
			m_CurrentItrIdx = startIdx;
		}
	}
	
	public boolean hasNext() {
		return (m_OriginalLine != null && m_CurrentItrIdx >= 0 && m_CurrentItrIdx < m_OriginalLine.getWordCount());
	}
	
	public String next() {
		
		if(hasNext()){
			return m_OriginalLine.getWord((m_CurrentItrIdx + m_StartOffset)%m_OriginalLine.getWordCount());
		}
		return null;
	}
	
	private int m_StartOffset = -1;
	private int m_OriginalLineIdx = -1;
	private AbstractLine m_OriginalLine;
	
	private int m_CurrentItrIdx;
}
