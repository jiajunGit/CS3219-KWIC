import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Observer;

public class Printer implements Observer {

	public Printer() {
		m_Bw = new BufferedWriter( new OutputStreamWriter(System.out) );
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
			print( (AbstractLineStorage)arg );
		}
	}
	
	private BufferedWriter m_Bw;
}
