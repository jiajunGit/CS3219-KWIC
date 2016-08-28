package implicitInvocation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Observer;

public class Printer implements Observer {

	public Printer( IgnoreWordStorage ignoreWords ) {
		
		m_Bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		m_IgnoreWords = ignoreWords;
	}
	
	public boolean setOutputStream( OutputStream os ) {
		
		boolean isSet = false;
		
		if( os != null ){
			
			try{
				if(m_Bw != null){
					m_Bw.close();
				}
			} catch( IOException e ) {}
			
			m_Bw = new BufferedWriter( new OutputStreamWriter(os) );
			isSet = true;
		}
		return isSet;
	}
	
	public void print( AbstractLineStorage lineStorage ) {
		
		if( m_Bw != null && lineStorage != null ) {
			
			try{
				
				AbstractLine line;
				
				lineStorage.initialiseIterator();
				while( (line = lineStorage.next()) != null ) {
					printLineV2(line);
				}
				m_Bw.flush();
			}
			catch( IOException e ) {}
		}
	}
	
	private void printV2( AbstractLineStorage lineStorage ) {
		
		if( m_Bw != null && m_IgnoreWords != null && lineStorage != null ) {
			
			try{
				
				AbstractLine line;
				
				lineStorage.initialiseIterator();
				while( (line = lineStorage.next()) != null ) {
					printLineV3(line);
				}
				m_Bw.flush();
			}
			catch( IOException e ) {}
		}
	}
	
	private boolean printLineV3( AbstractLine line ) {
		
		boolean isPrinted = true;
		try {
		
			line.initialiseIterator();
			String tempWord = line.next();
			
			if( tempWord != null ) {
				
				printFirstWord(tempWord);
				
				while( (tempWord = line.next()) != null ) {
					printSubsequentWord(tempWord);
				}
				m_Bw.write("\n");
			}
		}
		catch( IOException e ) {
			isPrinted = false;
		}
		return isPrinted;
	}
	
	private void printFirstWord( String word ) throws IOException {
		
		if( word.length() > 0 ) {
			printWord(word);
		}
	}
	
	private void printSubsequentWord( String word ) throws IOException {
		
		if( word.length() > 0 ) {
			
			m_Bw.write(" ");
			printWord(word);
		}
	}
	
	private void printWord( String word ) throws IOException {
		
		char charHolder;
		if(!m_IgnoreWords.isIgnoredWord(word)){
			
			charHolder = word.charAt(0);
			if( charHolder >= 'a' && charHolder <= 'z' ){
				m_Bw.append((char)(charHolder - 0x20));
			}
			else{
				m_Bw.append(charHolder);
			}
			
			if(word.length() > 1){
				m_Bw.append(word, 1, word.length());
			}
		}
		else{
			m_Bw.append(word);
		}
	}
	
	private boolean printLineV2( AbstractLine line ) {
		
		boolean isPrinted = true;
		
		try {
			
			StringBuilder lineInStr = line.getString();
			
			if(lineInStr.length() <= 0){
				return isPrinted;
			}
			
			char firstChar = lineInStr.charAt(0);
			if( (firstChar >= 'a') && (firstChar <= 'z') ){
				lineInStr.setCharAt(0, (char)(firstChar - 0x20));
			}
			lineInStr.append("\n");
			
			m_Bw.write(lineInStr.toString());
		}
		catch( IOException e ) {
			isPrinted = false;
		}
		return isPrinted;
	}
	
	@SuppressWarnings("unused")
	private boolean printLine( AbstractLine line ) {
		
		boolean isPrinted = true;
		try {
		
			line.initialiseIterator();
			String tempWord = line.next();
			
			if( tempWord != null && printKeyword(tempWord) ) {
				while( (tempWord = line.next()) != null ) {
					m_Bw.write(" " + tempWord);
				}
				m_Bw.write("\n");
			}
		}
		catch( IOException e ) {
			isPrinted = false;
		}
		return isPrinted;
	}
	
	private boolean printKeyword( String keyword ) {
		
		boolean isPrinted = true;
		try{
			if( keyword != null && !keyword.isEmpty() ){
	
				char firstChar = keyword.charAt(0);
				if( (firstChar >= 'a') && (firstChar <= 'z') ){
					m_Bw.write((char)(firstChar - 0x20));
					if( keyword.length() > 1 ){
						m_Bw.write(keyword, 1, keyword.length() - 1);
					}
				}
				else{
					m_Bw.write(keyword);
				}
			}
		}
		catch( IOException e ) {
			isPrinted = false;
		}
		return isPrinted;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		if( arg instanceof AbstractLineStorage ) {
			printV2( (AbstractLineStorage)arg );
		}
	}
	
	private BufferedWriter m_Bw;
	private IgnoreWordStorage m_IgnoreWords;
}
