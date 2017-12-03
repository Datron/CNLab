import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;


public class UDPServer {

	
	public static void main(String[] args) {
		DatagramSocket aSocket = null;
		Scanner s = new Scanner(System.in);
		int serverPort = 998;
		System.out.println("Server Ready \n waiting");
		try {
			aSocket = new DatagramSocket(serverPort);
			byte[] buffer = new byte[1000];
			byte[] buf = new byte[1000];
			DatagramPacket data1 = new DatagramPacket(buf,buf.length);
			aSocket.receive(data1);
			byte[] msg = new byte[1000];
			msg = data1.getData();
			System.out.println(new String(msg,0,data1.getLength()));
			System.out.println("\n Enter message to be sent");
			String str = s.nextLine();
			buffer = str.getBytes();
			DatagramPacket data = new DatagramPacket(buffer, buffer.length,InetAddress.getLocalHost(),998);
			aSocket.send(data);
		} catch(SocketException e){
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("Message sent \n Connection closed.");
			if(aSocket != null)
				aSocket.close();
			s.close();
		}
	}

}
