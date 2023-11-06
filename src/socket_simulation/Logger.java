package socket_simulation;

public abstract class Logger extends Thread {
	protected Client[] clients;
	private String lastFirstLine = ""; 
	
	protected abstract void writeLine(String line);

	
	public void run() {		
		while(true) {
			String firstLine = "";
			String secondLine = "";
			
			for(int i = 0; i < clients.length; i++) {
				try {
					String state = clients[i].getPhilosopher().getPhilosopherState().name();
					firstLine += fixedLengthString(state, 8) + " | ";
					secondLine +=  fixedLengthString("Ate: " + clients[i].getPhilosopher().ateTimes(), 8) + " + ";
				} catch(Exception e) {
					firstLine += fixedLengthString("Starting", 8) + " | ";
					secondLine += fixedLengthString("Starting", 8) + " + ";
				}
			}
			
			if(!lastFirstLine.equals(firstLine)) {
				lastFirstLine = firstLine;
				writeLine(firstLine);
				writeLine(secondLine);				
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String fixedLengthString(String string, int length) {
	    return String.format("%1$" + length + "s", string);
	}
}
