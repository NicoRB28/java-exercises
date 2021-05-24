package udpSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public class QuoteServerThread extends Thread {
	
	private DatagramSocket socket = null;
	private BufferedReader in = null;
	private boolean moreQuotes = true;
	
	
	public QuoteServerThread()throws IOException {
		this("QuoteServerThread");
	}
	
	public QuoteServerThread(String name)throws SocketException,IOException{
		super(name);
		socket = new DatagramSocket(4445);		
		in = Files.newBufferedReader(Paths.get("files/one-liners.txt"), StandardCharsets.UTF_8);
		
	}
	

	public void run(){
	
		while(moreQuotes) {
			byte[] buf = new byte[256];
			
			//receive request
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);				
			} catch (IOException ex) {
				throw new RuntimeException(ex.getMessage()+ ex.getCause());
			}
			
			String dString = null;
			if(in == null)
				dString = new Date().toString();
			else
				dString = getNextQuote();
			
			buf = dString.getBytes();
			
			//send response to the client at address and port
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			packet = new DatagramPacket(buf, buf.length,address,port);
			try {
				socket.send(packet);				
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage() + e.getCause());
			}
			socket.close();
		}
	}
	
	private String getNextQuote() {
		String returnValue;
		try  {
			if((returnValue = in.readLine()) == null) {
				in.close();
				moreQuotes = false;
				return "No more quotes, Goodbye";
			}
			return returnValue;
		} catch (IOException e) {
			return "IOE exception";
		}
	}
}
