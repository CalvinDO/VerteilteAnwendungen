package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServices {

	public static void main(String[] args) {
		ServerSocket serverSocket;
		Socket socket;

		try {
			serverSocket = new ServerSocket(75);

			while (true) {
				try {
					boolean run = true;
					socket = serverSocket.accept();
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					//String readLine = reader.readLine();
					//System.out.println("reader.readLine() reads: " + readLine);

					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
							socket.close();
							break;
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
