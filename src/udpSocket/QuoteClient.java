package udpSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



public class QuoteClient {
	
	private static DatagramSocket socket;
	private static byte[] buf;
	private static InetAddress address;
	private static boolean getAnotherQuote = true;
	
	public static void main(String[] args) throws SocketException,UnknownHostException,IOException{
		
		// get a datagram socket
		socket = new DatagramSocket();
		
		//send request
		buf = new byte[256];
		address = InetAddress.getByName("localhost");
		DatagramPacket packet = new DatagramPacket(buf,buf.length, address,4445);
		while(getAnotherQuote) {
			socket.send(packet);
			
			
			//get Response
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			//display response
			String received = new String(packet.getData(),0,packet.getLength());
			System.out.println("The quote of the moment is: " + received);
		}
		socket.close();
	}
	
}
