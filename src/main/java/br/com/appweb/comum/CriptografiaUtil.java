package br.com.appweb.comum;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriptografiaUtil implements Serializable{

	private static final long serialVersionUID = -7906780466670143177L;
	
	private static final Logger LOG = Logger.getLogger(CriptografiaUtil.class.getName());       
	
	public static String MD5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
}
