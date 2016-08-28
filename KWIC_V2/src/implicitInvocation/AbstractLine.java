package implicitInvocation;

public interface AbstractLine {

	public LineType getType();
	
	public String toString();
	
	public StringBuilder getString();
	
	public String getWord( int wordIdx );
	
	public int getWordCount();
	
	public void initialiseIterator();
	
	public void initialiseIterator( int startIdx );
	
	public boolean hasNext();
	
	public String next();
}
