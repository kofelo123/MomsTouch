import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JDBCEncryptor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("rktwlsrud");
		System.out.println(enc.encrypt(""));
		System.out.println(enc.encrypt(""));


	}

}
