package socket_simulation;

public class Table implements ITable {
	private int size;
	private Boolean[] forks;
	
	
	public Table(int size) {
		this.size = size;
		this.forks = new Boolean[size];
		
		for (int i = 0; i < size; i++) 
			forks[i] = false;
	}
	
	public synchronized void getForks(int tableIndex) {
		try {
			int index1 = tableIndex;
			int index2 = (tableIndex + 1) % size;
			
			while (true) {
				if (forks[index1] || forks[index2]) {
					wait();
				} else {
					forks[index1] = true;
					forks[index2] = true;
					
					notifyAll();
					return; 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void returnForks(int tableIndex) {
		try {
			int index1 = tableIndex;
			int index2 = (tableIndex + 1) % size;
			
			forks[index1] = false;
			forks[index2] = false;
			
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getSize() {
		return size;
	}
}