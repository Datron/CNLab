import java.io.DataInputStream;
import java.io.IOException;


public class RSA {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		RSALab rsa = new RSALab();
		DataInputStream in = new DataInputStream(System.in);
		String testString;
		System.out.println("Enter plain text");
		testString = in.readLine();
		BTS s1 = new BTS();
		System.out.println("Encrypted String:"+testString);
		System.out.println("String in bytes:"+s1.bytesToString(testString.getBytes()));
		BTS s2 = new BTS();
		byte[] encrypted = rsa.encrypt(testString.getBytes());
		System.out.println("Encrypted String:"+s2.bytesToString(encrypted));
		BTS s3 = new BTS();
		byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println("Encrypted String:"+s2.bytesToString(decrypted));
		System.out.println("Decrypted String:"+new String(decrypted));
	}
}
