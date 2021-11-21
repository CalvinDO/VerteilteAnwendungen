package services2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TimeServiceClient {

	public static void main(String[] args) {
		try (Socket clientSocket = new Socket("localhost", 75)) {
			System.out.println("dateFromServer returns: " + dateFromServer(clientSocket));
			//System.out.println("timeFromServer returns: " + timeFromServer(clientSocket));
		} catch (Exception e) {
		}

	}

	public static String dateFromServer(Socket clientSocket) throws Exception {
		return fromServer("date", clientSocket);
	}

	public static String timeFromServer(Socket clientSocket) throws Exception {
		return fromServer("time", clientSocket);
	}

	public static String fromServer(String specification, Socket clientSocket) throws Exception {
		String output = "something went wrong";

		System.out.println(clientSocket.toString());
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		writer.write(specification);
		writer.newLine();
		writer.flush();

		reader.readLine();
		output = reader.readLine();

		return output;
	}
}
