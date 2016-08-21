import java.io.*;
import java.util.Observable;

public class LineReader extends Observable {

	public LineReader( InputLineStorage lineStorage ) {
		m_LineStorage = lineStorage;
	}
	
	public boolean read( String fileName ) {
			
		if(m_LineStorage == null) {
			return false;
		}
		
		boolean isReadSuccessful = false;
		
		try{
			
			FileInputStream fs = new FileInputStream(fileName);
			
			Pair<Integer> readData = readIgnoreWords( fs );
			if(readData != null){
				isReadSuccessful = readInputLines( readData.getFirst(), readData.getSecond(), fs );
			}
			
			if( isReadSuccessful ) {
				
				setChanged();
				notifyObservers( m_LineStorage );
				clearChanged();
			}
			
			fs.close();
		} 
		catch( FileNotFoundException e ){}
		catch( IOException e ) {}
		
		return isReadSuccessful;
	}
	
	public boolean read() {
		
		if(m_LineStorage == null) {
			return false;
		}
		
		boolean isReadSuccessful = false;
		
		Pair<Integer> readData = readIgnoreWords( System.in );
		
		if(readData != null){
			isReadSuccessful = readInputLines( readData.getFirst(), readData.getSecond(), System.in );
		}
		
		if( isReadSuccessful ) {
			
			setChanged();
			notifyObservers( m_LineStorage );
			clearChanged();
		}
		
		return isReadSuccessful;
	}
	
	// '\n' will be the separator between ignore words and input lines
	// Pair.first -> readOffset
	// Pair.second -> readSize
	private Pair<Integer> readIgnoreWords( InputStream is ) {
	
		if( is == null ){
			return new Pair<Integer>( -1, -1 );
		}
		
		BufferedInputStream br = null;
		
		try {
			
			br = new BufferedInputStream( is );
			
			char charHolder;
			int numCharRead = 0;
			IgnoreWordStorage ignoreWordStore = IgnoreWordStorage.getInstance();
			StringBuilder wordHolder = new StringBuilder();
			
			while( numCharRead >= 0 ){
				
				numCharRead = br.read(m_ReadBuf);
				
				for( int i = 0; i < numCharRead; ++i ) {
					
					switch(m_ReadBuf[i]) {
							
						case ' ':
						case '\t':
						case '\n':
						case '\r':
						case ',':
							
							if( wordHolder.length() > 0 ){
								ignoreWordStore.addIgnoreWord(wordHolder.toString());
								wordHolder.setLength(0);
							}
							break;
							
						case '@':
							
							if( i >= numCharRead - 1 ) {
								return new Pair<Integer>( -1, -1 );
							}
							else {
								return new Pair<Integer>( i + 1, numCharRead );
							}
							
						default:
							
							charHolder = (char)m_ReadBuf[i];
							
							if( (charHolder >= 'A') && (charHolder <= 'Z') ) {
								charHolder += 0x20;
							}
							wordHolder.append(charHolder);
							break;
					}
				}
			}
			
			br.close();
		}
		catch( IOException e ){}
		
		return new Pair<Integer>( -1, -1 );
	}
	
	private boolean readInputLines( int bufOffset, int bufSize, InputStream is ) {
		
		if( is == null ){
			return false;
		}
		
		BufferedInputStream br = null;
		boolean isReadSuccessful = true;
		int currentLineIdx = -1;
		int numCharRead;
		int readOffset = 0;
		char charHolder;
		InputLine currentLine = null;
		StringBuilder wordHolder;
		
		try{
			
			br = new BufferedInputStream( is );
			wordHolder = new StringBuilder();
			
			if( bufSize > 0 ){
				numCharRead = bufSize;
				readOffset = bufOffset;
			}
			else{
				numCharRead = br.read(m_ReadBuf);
			}
			
			while( numCharRead >= 0 ){
				
				for( int i = readOffset; i < numCharRead; ++i ) {
					
					switch(m_ReadBuf[i]){
						
						case ' ':
						case '\t':
							
							if( wordHolder.length() > 0 ){
								currentLine.addWord(wordHolder.toString());
								wordHolder.setLength(0);
							}
							break;
							
						case '\n':
						case '\r':
						case ',':
							
							if( wordHolder.length() > 0 ){
								currentLine.addWord(wordHolder.toString());
								wordHolder.setLength(0);
							}
							currentLineIdx = -1;
							break;
							
						default:
							
							if( currentLineIdx <= -1 ) {
								currentLineIdx = m_LineStorage.addLine();
								currentLine = m_LineStorage.getLine(currentLineIdx);
							}
							
							charHolder = (char)m_ReadBuf[i];
							
							if( (charHolder >= 'A') && (charHolder <= 'Z') ) {
								charHolder += 0x20;
							}
							
							wordHolder.append(charHolder);
							break;
					}
				}
				
				numCharRead = br.read(m_ReadBuf);
				readOffset = 0;
			}
			
			if( wordHolder.length() > 0 ){
				currentLine.addWord(wordHolder.toString());
				wordHolder.setLength(0);
			}
			
			br.close();
		}
		catch( IOException e ){
			isReadSuccessful =  false;
		}
		
		return isReadSuccessful;
	}
	
	private InputLineStorage m_LineStorage;
	
	private final int READ_BUF_SIZE = 10000;
	private byte []m_ReadBuf = new byte[READ_BUF_SIZE];
}
