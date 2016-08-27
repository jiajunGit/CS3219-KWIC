import java.util.HashSet;

public class IgnoreWordStorage {

	public static IgnoreWordStorage getInstance(){
		return ignoreWordStorageInstance;
	}
	
	public void addIgnoreWord( String word ){
		if(word != null){
			ignoreWords.add(word);
		}
	}
	
	public boolean removeIgnoreWord( String word ){
		return ignoreWords.remove(word);
	}
	
	public boolean isIgnoredWord( String word ){
		return ignoreWords.contains(word);
	}
	
	private IgnoreWordStorage(){}
	
	private static final IgnoreWordStorage ignoreWordStorageInstance = new IgnoreWordStorage();
	private HashSet<String> ignoreWords = new HashSet<String>();
}
