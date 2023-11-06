package socket_simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
	private String ip;
	private int port;
	private int clientNumber;
	private Philosopher philosopher;
	
	public Client(String ip, int port, int clientNumber) {
		this.ip = ip;
		this.port = port;
		this.clientNumber = clientNumber;
	}
	
	public void run() {
		try (Socket socket = new Socket(ip, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            TableClient tableClient = new TableClient(out, in);
            philosopher = new Philosopher(clientNumber, tableClient);

            philosopher.start();
            
            try {
				philosopher.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Philosopher getPhilosopher() {
		return philosopher;
	}
}
