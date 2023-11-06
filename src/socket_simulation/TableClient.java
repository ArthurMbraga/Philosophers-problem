package socket_simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TableClient implements ITable {
	
	private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    
	public TableClient(PrintWriter printWriter, BufferedReader bufferedReader) {
		this.printWriter = printWriter;
		this.bufferedReader = bufferedReader;
	}
	
	public void getForks(int tableIndex) {
		printWriter.println("getForks " + tableIndex);
		try {
			bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void returnForks(int tableIndex) {
		printWriter.println("returnForks " + tableIndex);
		try {
			bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
