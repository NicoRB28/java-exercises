package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSocketServer {
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void start(int port)throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String inputLine;
		while((inputLine = in.readLine())!= null) {
			out.println(inputLine);
		}
	}
	
	public void stop()throws IOException {
		in.close();
		out.close();	
		clientSocket.close();
		serverSocket.close();
	}
	
	public static void main(String[] args)throws IOException {
		System.out.println("server socket initialization...");
		EchoSocketServer server = new EchoSocketServer();
		server.start(8080);
		System.out.println("server socket stoping...");
	}
}
