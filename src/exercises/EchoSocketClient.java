package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoSocketClient {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private BufferedReader stdIn;
	
	public void startConnection(String ip, int port)throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(),true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		String userInput;
		while((userInput = stdIn.readLine())!=null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
		
	}
	
	
	public void stop() throws IOException{
		in.close();
		out.close();
		clientSocket.close();
	}
	
	public static void main(String[] args)throws IOException {
		System.out.println("Client socket initialization...");
		EchoSocketClient client = new EchoSocketClient();
		client.startConnection("127.0.0.1",8080);
		System.out.println("Client socket stoping...");
		client.stop();
	}
}
