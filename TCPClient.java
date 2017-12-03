import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class TCPClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Socket s = new Socket("localhost",998);
			DataInputStream di = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("connected to 127.0.0.1 \n");
			System.out.println("\nConnection established to server");
			System.out.println("\nEnter the full path of the file to be displayed");
			String path = sc.nextLine();
			dos.writeUTF(path);
			System.out.println(new String(di.readUTF()));
			di.close();
			dos.close();
			s.close();
			sc.close();
		}
		catch(IOException e){
			System.out.println("\n error:"+e.getMessage());
		}
	}
}
