import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class KWIC {

	HashSet<String> ignoreWords = new HashSet<String>();
	ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
	LinkedList<Tuple<ArrayList<String>, Integer, Integer>> circularStorage = new LinkedList<Tuple<ArrayList<String>, Integer, Integer>>();
	TreeSet<Tuple<ArrayList<String>, Integer, Integer>> sortedStorage = new TreeSet<Tuple<ArrayList<String>, Integer, Integer>>( new KeywordComparator() );
	
	private static class KeywordComparator implements Comparator<Tuple<ArrayList<String>, Integer, Integer>> {
		
		@Override
		public int compare(Tuple<ArrayList<String>, Integer, Integer> entry1, Tuple<ArrayList<String>, Integer, Integer> entry2) {
			
			ArrayList<String> line1 = entry1.getFirst();
			ArrayList<String> line2 = entry2.getFirst();
			
			int startIndex1 = entry1.getThird();
			int startIndex2 = entry2.getThird();
			
			String keyword1 = ((startIndex1 >= 0 && startIndex1 < line1.size()) ? line1.get(startIndex1) : "");
			String keyword2 = ((startIndex2 >= 0 && startIndex2 < line2.size()) ? line2.get(startIndex2) : "");
			
			int compareVal = keyword1.compareTo(keyword2);
			
			if(compareVal == 0){
				return compare(entry1.getSecond(),entry2.getSecond());
			}
			return compareVal;
		}
		
		private int compare( int val1, int val2 ) {
			
			if(val1 < val2){
				return 1;
			}
			else if( val2 < val1 ){
				return -1;
			}
			else{
				return 0;
			}
		}
	} 
	
	private void appendWord( String word, StringBuilder line ){
		
		if(word != null && word.length() > 0) {
		
			int length = line.length();
			line.append(word);
			
			if( !ignoreWords.contains(word) ){
				
				char charHolder = line.charAt(length);
				
				if( charHolder >= 'a' && charHolder <= 'z' ){
					line.setCharAt(length, (char)(charHolder - 0x20));
				}
			}
			
			line.append(" ");
		}
	}
	
	public boolean print() {
		
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		Tuple<ArrayList<String>, Integer, Integer> entry;
		ArrayList<String> line;
		StringBuilder sb = new StringBuilder();
		boolean isPrinted = true;
		String word;
		
		Iterator<Tuple<ArrayList<String>, Integer, Integer>> itr = sortedStorage.iterator();
		
		try{
		
			while( itr.hasNext() ){
				
				entry = itr.next();
				
				if(entry != null){
					
					line = entry.getFirst();
					if( line != null ){
						
						sb.setLength(0);
						
						for( int i = entry.getThird(), end = i + line.size(); i < end; ++i ){
							
							word = line.get(i%line.size());
							appendWord(word, sb);
						}
						
						if(sb.length() > 0){
							sb.setCharAt(sb.length() - 1, '\n');
						}
						
						bw.write(sb.toString());
					}
				}
			}
			bw.flush();
		}
		catch( IOException e ) {
			isPrinted = false;
		}
		
		return isPrinted;
	}
	
	public boolean alphabetizer() {
		
		Tuple<ArrayList<String>, Integer, Integer> entry;
		boolean isSet = false;
		
		Iterator<Tuple<ArrayList<String>, Integer, Integer>> itr = circularStorage.iterator();
		while( itr.hasNext() ) {
			
			entry = itr.next();
			
			if( entry != null ){
				isSet = sortedStorage.add(entry);
			}
		}
		
		return isSet;
	}
	
	public boolean shift() {
		
		ArrayList<String> line;
		HashSet<String> duplicateWords;
		
		for( int i = 0, size = lines.size(); i < size; ++i ){
			
			line = lines.get(i);
			
			if( line != null ){
				
				duplicateWords = new HashSet<String>();
				String word;
				
				for( int j = 0, lSize = line.size(); j < lSize; ++j ) {
					
					word = line.get(j);
					
					if( !ignoreWords.contains(word) && !duplicateWords.contains(word) ){
						
						if(!circularStorage.add(new Tuple<ArrayList<String>, Integer, Integer>( line, i, j ))){
							return false;
						}
						duplicateWords.add(word);
					}
				}
			}
			else{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean read( String fileName ) {
		
		if(fileName == null) {
			return false;
		}
		
		boolean isReadSuccessful = false;
		
		try{
			
			FileInputStream fs = new FileInputStream(fileName);
			BufferedInputStream br = new BufferedInputStream( fs );
			
			ArrayList<String> currentLine = null;
			int readOffset = 0;
			byte []readBuf = new byte[10000];
			char charHolder;
			int numCharRead = 0;
			StringBuilder wordHolder = new StringBuilder();
			Boolean isExit = false;
			int bufOffset = -1;
			int bufSize = -1;
			
			while( !isExit && numCharRead >= 0 ){
				
				numCharRead = br.read(readBuf);
				
				for( int i = 0; !isExit && i < numCharRead; ++i ) {
					
					switch(readBuf[i]) {
							
						case ' ':
						case '\t':
						case '\n':
						case '\r':
						case ',':
							
							if( wordHolder.length() > 0 ){
								ignoreWords.add(wordHolder.toString());
								wordHolder.setLength(0);
							}
							break;
							
						case '@':
							
							if( i >= numCharRead - 1 ) {
								bufOffset = bufSize = -1;
							}
							else {
								bufOffset = i + 1;
								bufSize = numCharRead;
							}
							isExit = true;
							break;
							
						default:
							
							charHolder = (char)readBuf[i];
							
							if( (charHolder >= 'A') && (charHolder <= 'Z') ) {
								charHolder += 0x20;
							}
							wordHolder.append(charHolder);
							break;
					}
				}
			}
			
			isReadSuccessful = true;
			numCharRead = 0;
			charHolder = '\0';
			wordHolder = new StringBuilder();
			
			if( bufSize > 0 ){
				numCharRead = bufSize;
				readOffset = bufOffset;
			}
			else {
				numCharRead = br.read(readBuf);
			}
			
			while( numCharRead >= 0 ){
				
				for( int i = readOffset; i < numCharRead; ++i ) {
					
					switch(readBuf[i]){
						
						case ' ':
						case '\t':
							
							if( wordHolder.length() > 0 ){
								currentLine.add(wordHolder.toString());
								wordHolder.setLength(0);
							}
							break;
							
						case '\n':
						case '\r':
						case ',':
							
							if( wordHolder.length() > 0 ){
								currentLine.add(wordHolder.toString());
								wordHolder.setLength(0);
							}
							currentLine = null;
							break;
							
						default:
							
							if( currentLine == null ) {
								currentLine = new ArrayList<String>();
								lines.add(currentLine);
							}
							
							charHolder = (char)readBuf[i];
							
							if( (charHolder >= 'A') && (charHolder <= 'Z') ) {
								charHolder += 0x20;
							}
							
							wordHolder.append(charHolder);
							break;
					}
				}
				
				numCharRead = br.read(readBuf);
				readOffset = 0;
			}
			
			if( wordHolder.length() > 0 ){
				currentLine.add(wordHolder.toString());
				wordHolder.setLength(0);
			}
			
			br.close();
			fs.close();
		} 
		catch( FileNotFoundException e ){}
		catch( IOException e ) {}
		
		return isReadSuccessful;
	}
	
	public static void main( String []argv ){
		
		KWIC kwic = new KWIC();
		
		if( !kwic.read("TestCases/input3.in") ){
			System.out.println("Read error");
			return;
		}
		
		if( !kwic.shift() ){
			System.out.println("Shift error");
			return;
		}
		
		if( !kwic.alphabetizer() ){
			System.out.println("Sort error");
			return;
		}
		
		if( !kwic.print() ){
			System.out.println("Print error");
			return;
		}
	}
}
