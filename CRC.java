import java.util.Scanner;


public class CRC {
	public static int n;
	static String divide(String s){
		String div = "10001000000100001";
		char x;
		int i,j;
		for (i=0;i<n;i++)
		{
			x = s.charAt(i);
			for (j=0;j<17;j++)
			{
				if(x=='1')
					if (s.charAt(i+j) != div.charAt(j))
						s = s.substring(0,i+j)+"1"+s.substring(i+j+1);
				else
						s = s.substring(0,i+j)+"0"+s.substring(i+j+1);
			}
		}
		return s;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String code,copy,rec,zero="0000000000000000";
		System.out.println("Enter the message to be transmitted");
		code = s.nextLine();
		n = code.length();
		copy = code;
		code += "0000000000000000";
		code = divide(code);
		System.out.println("Message= "+copy);
		copy = copy.substring(0,n)+code.substring(n);
		System.out.println("CRC="+code.substring(n));
		System.out.println("Sender transmitted from is="+copy);
		System.out.println("Enter recieved data:");
		rec = s.nextLine();
		if (zero.equals(divide(rec).substring(n)))
			System.out.println("Frame recieved without errors");
		else
			System.out.println("Recieved frame contains one or more errrors");
		s.close();
		}
	}

