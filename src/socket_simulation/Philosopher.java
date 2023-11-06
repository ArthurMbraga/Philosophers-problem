package socket_simulation;
import java.util.Random;

enum States {
	THINKING,
	HUNGRY,
	EATING
}

public class Philosopher extends Thread {
	private ITable tableBuffer;
	private int position;
	private States state;
	private int ateTimes;
	
	public Philosopher(int position, ITable tableBuffer) {
		this.position = position;
		this.tableBuffer = tableBuffer;
		this.state = States.THINKING;
		this.ateTimes = 0;
	}
	
	public void run() {
		while(true) {
			switch(state) {
			  case THINKING:
				waitRandomTime();
				state = States.HUNGRY;
			    break;
			    
			  case HUNGRY:
			  	tableBuffer.getForks(position);
				state = States.EATING;
			  	break;
			  	
			  case EATING:
				ateTimes++;
				waitRandomTime();
			  	tableBuffer.returnForks(position);
				state = States.THINKING;

			}			
		}
	}
	
	private void waitRandomTime() {
	    Random random = new Random();
		try {
			Thread.sleep(random.nextInt(256));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public States getPhilosopherState() {
		return state;
	}
	
	public int ateTimes() {
		return ateTimes;
	}
}
