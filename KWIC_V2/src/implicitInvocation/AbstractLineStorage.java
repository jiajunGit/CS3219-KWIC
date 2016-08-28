package implicitInvocation;


public interface AbstractLineStorage {

	public StorageType getType();
	
	public int getLineCount();
	
	public boolean isEmpty();
	
	public AbstractLine getLine( int lineIdx );
	
	public String getWord( int lineIdx, int wordIdx );
	
	public void initialiseIterator();
	
	public int getCurrentIteratedLineIndex();
	
	public boolean hasNext();
	
	public AbstractLine next();
}
