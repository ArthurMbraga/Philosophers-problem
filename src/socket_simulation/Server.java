package socket_simulation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	
	private int maxConnections;
	private int numberOfConnections;
	private Table table;
	private int port;
	private ServerSocket serverSocket;
	
	public Server(int port, Table table) {
		this.port = port;
		this.table = table;
		maxConnections = table.getSize();
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			
			System.out.println("Server listening on port " + port + "...");
			
			while(numberOfConnections < maxConnections) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected from " + clientSocket.getInetAddress());
				numberOfConnections++;
				
				ClientHandler clientHandler = new ClientHandler(clientSocket, table);
				clientHandler.start();
			}
		} catch (IOException e) {
			System.out.println("Server stopped");
		}
		
	}
	
	public void stopServer() {
		try {
			maxConnections = 0;
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
