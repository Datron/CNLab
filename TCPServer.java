import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TCPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(998);
			System.out.println("\n server ready\n waiting..........");
			Socket s1 = s.accept();
			DataOutputStream dop = new DataOutputStream(s1.getOutputStream());
			DataInputStream di = new DataInputStream(s1.getInputStream());
			System.out.println(di.readUTF());
			String path = di.readUTF();
			System.out.println("Request received \n Processing....");
			try{
				File myFile = new File(path);
				Scanner sc = new Scanner(myFile);
				String st = sc.nextLine();
				st = "\n The contents of file are \n"+st;
				dop.writeUTF(st);
				dop.close();
				di.close();
				s.close();
				s1.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error file not found");
				dop.writeUTF("File not found");
			}
		} catch (IOException e) {
			System.out.println("error"+e.getMessage());
		}
		finally {
			System.out.println("\n Connection terminated \n");
		}

	}

}
