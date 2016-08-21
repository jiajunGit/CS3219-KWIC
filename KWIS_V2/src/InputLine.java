import java.util.ArrayList;

public class InputLine implements AbstractLine {

	public InputLine() {
		words = new ArrayList<String>();
		currentItrIdx = -1;
	}
	
	public LineType getType() {
		return LineType.INPUT_LINE;
	}
	
	@Override
	public String toString() {
		
		String line = "";
		
		int i = 0;
		for( int size = words.size() - 1; i < size; ++i ){
			line += (words.get(i) + " ");
		}
		
		if( !words.isEmpty() ){
			line += words.get(i);
		}
		
		return line;
	}
	
	public StringBuilder getString() {
		
		StringBuilder line = new StringBuilder();
		
		int i = 0;
		for( int size = words.size() - 1; i < size; ++i ){
			line.append(words.get(i));
			line.append(" ");
		}
		
		if( !words.isEmpty() ){
			line.append(words.get(i));
		}
		
		return line;
	}
	
	public boolean addWord( String word ){
		
		if(word != null && !word.isEmpty()){
			return words.add(word);
		}
		return false;
	}
	
	public String getWord( int wordIdx ) {
		
		if( wordIdx >= 0 && wordIdx < words.size() ){
			return words.get(wordIdx);
		}
		return null;
	}
	
	public int getWordCount() {
		return words.size();
	}
	
	public void initialiseIterator() {
		initialiseIterator(0);
	}
	
	public void initialiseIterator( int startIdx ) {

		currentItrIdx = -1;
		if( startIdx >= 0 && startIdx < words.size() ){
			currentItrIdx = startIdx;
		}
	}
	
	public boolean hasNext() {
		return (currentItrIdx >= 0 && currentItrIdx < words.size());
	}
	
	public String next() {
		return (hasNext() ? words.get(currentItrIdx++) : null);
	}
	
	private ArrayList<String> words;
	private int currentItrIdx;
}
