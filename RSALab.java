import java.math.BigInteger;
import java.util.Random;


public class RSALab {
	private BigInteger p,q,N,phi,e,d;
	@SuppressWarnings("unused")
	private int bitlength = 1024,blocksize = 256;
	private Random r;
	
	public RSALab(){
		r = new Random();
		p = BigInteger.probablePrime(bitlength,r);
		q = BigInteger.probablePrime(bitlength, r);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(bitlength/2, r);
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi)<0)
			e.add(BigInteger.ONE);
		d = e.modInverse(phi);
	}
	public RSALab(BigInteger e,BigInteger d,BigInteger N){
		this.e = e;
		this.d = d;
		this.N = N;
	}
	public byte[] encrypt(byte[] message){
		return (new BigInteger(message).modPow(e, N).toByteArray());
	}
	public byte[] decrypt(byte[] message){
		return (new BigInteger(message).modPow(d, N).toByteArray());
	}
}
