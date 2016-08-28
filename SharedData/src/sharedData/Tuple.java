package sharedData;

public class Tuple <T,V,U> {

	public Tuple( T first, V second, U third ){
		
		m_First = first;
		m_Second = second;
		m_Third = third;
	}
	
	public T getFirst(){
		return m_First;
	}
	
	public V getSecond(){
		return m_Second;
	}
	
	public U getThird() {
		return m_Third;
	}
	
	private T m_First;
	private V m_Second;
	private U m_Third;
}
