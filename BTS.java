
public class BTS {
	public String bytesToString(byte[] encrypted){
		String test = " ";
		for (byte b:encrypted)
			test += Byte.toString(b);
		return test;
	}
}
