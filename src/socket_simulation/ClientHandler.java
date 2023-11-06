package socket_simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {	
	private Socket clientSocket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private Table table;
	
	public ClientHandler(Socket socket, Table table) {
		this.clientSocket = socket;
		this.table = table;
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void run() {
		while(true) {
			String message;
			try {
				message = bufferedReader.readLine();
				String response = handleCommand(message);
				printWriter.println(response);	
			} catch (IOException e) {
				System.out.println("Client handler stopped");
				break;
			}			
		}
	}
	
	private String handleCommand(String message) {
		String[] data = message.split(" ");
		String command = data[0];
		int id = Integer.parseInt(data[1]);
		
		switch(command) {
			case "getForks":
				table.getForks(id);
				return "OK";
			case "returnForks":
				table.returnForks(id);
				return "OK";
			default:
				return "command not found";
		}
	}
}
