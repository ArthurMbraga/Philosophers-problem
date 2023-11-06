package socket_simulation;

import java.io.IOException;

public class ServerMain {
	public static void main(String[] args) {
		int SIZE = 5;
		int PORT = 1234;
		
		Table table = new Table(SIZE);
		
		Server server = new Server(PORT, table);
		server.start();	
		
		try {
			System.in.read();
			server.stopServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
