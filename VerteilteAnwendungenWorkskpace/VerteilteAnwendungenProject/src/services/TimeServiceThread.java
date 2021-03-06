package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TimeServiceThread extends Thread {
	private Socket socket;
	
	public TimeServiceThread(Socket _socket){
		this.socket = _socket;
	}
	
	@Override
	public void run() {
		
		try {
			this.communicate();
		} catch(Exception e) {
			System.out.println("connection closed, Exception thrown in communicate. Below is the StackTrace");
			e.printStackTrace();
		}
		
	}
	
	public void communicate() throws Exception {
		boolean run = true;
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));


		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		writer.write("time service");
		writer.newLine();
		writer.flush();

		while (run) {
			switch (reader.readLine()) {
			case "date":
				writer.write(Clock.date());
				writer.newLine();
				writer.flush();
				break;
			case "time":
				writer.write(Clock.time());
				writer.newLine();
				writer.flush();
				break;
			default:
				run = false;
				this.socket.close();
				break;
			}
		}
	}
}
