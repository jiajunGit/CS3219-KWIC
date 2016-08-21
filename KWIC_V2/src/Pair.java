
public class Pair<T> {

	public Pair( T first, T second ){
		
		m_First = first;
		m_Second = second;
	}
	
	public T getFirst(){
		return m_First;
	}
	
	public T getSecond(){
		return m_Second;
	}
	
	public void setFirst( T first ){
		m_First = first;
	} 
	
	public void setSecond( T second ) {
		m_Second = second;
	}
	
	private T m_First;
	private T m_Second;
}
