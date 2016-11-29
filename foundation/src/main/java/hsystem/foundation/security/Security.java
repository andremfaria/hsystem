package hsystem.foundation.security;

import java.security.*;

public class Security {

	public static String encryptPassword(String p)
	{
		byte[] bytesOfString = p.getBytes();
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA");
			return md.digest(bytesOfString).toString();
			
		} catch (NoSuchAlgorithmException e) 
		{
			return null;
		}
	}
}
