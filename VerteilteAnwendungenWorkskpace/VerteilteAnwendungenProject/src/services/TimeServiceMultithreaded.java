package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServiceMultithreaded {

	public static void main(String[] args) {
		ServerSocket serverSocket;
		Socket socket;

		try {
			serverSocket = new ServerSocket(75);

			while (true) {
				try {
					socket = serverSocket.accept();
					
					TimeServiceThread timeThread = new TimeServiceThread(socket);
					timeThread.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
