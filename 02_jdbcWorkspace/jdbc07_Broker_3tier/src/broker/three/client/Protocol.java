package broker.three.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * Database와 거의 동일한 모양으로 작성한 모듈.. 즉 기능의 선언부가 동일하다.
 * 하지만 구현부는 전혀 달라진다. Client 사이드의 통신 알고리즘을 가지고 있는 클래스
 */
public class Protocol {
	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public static final int MIDDLE_PORT = 60000;
	
	public Protocol(String serveIP) throws Exception{
		
	}

}
