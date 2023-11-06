package socket_simulation;

public class ConsoleLogger extends Logger {
	
	public ConsoleLogger(Client[] clients) {
		this.clients = clients;
	}
	
	protected void writeLine(String text) { 
		System.out.println(text);
	}
}
