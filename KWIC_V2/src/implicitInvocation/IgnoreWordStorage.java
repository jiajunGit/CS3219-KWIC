package implicitInvocation;

import java.util.HashSet;

public class IgnoreWordStorage {

	public IgnoreWordStorage(){}
	
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
	
	private HashSet<String> ignoreWords = new HashSet<String>();
}
