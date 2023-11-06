package socket_simulation;

public class ClientsMain {
	public static void main(String[] args) {
		String IP = "localhost";
		int SIZE = 5;
		int PORT = 1237;
			
		
		Client[] clients = new Client[SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			clients[i] = new Client(IP, PORT, i);
			clients[i].start();
		}
			
		Logger logger = new ConsoleLogger(clients);
		logger.start();
		
		try {
			logger.join();

			for(int i = 0; i < SIZE; i++)
				clients[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
