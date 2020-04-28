package it.gis.egeosDCL.client;

import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

/**
 * CLASSE DI LETTURA E CIFRATURA DEI PARAMETRI OTTENUTI DALLA GESTIONE DEGLI UTENTI
 * @version 1.0
 *
 */
public class SguParam {

	private static final byte[] GWT_DES_KEY = new byte[]{
		(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16,(byte)17,(byte)18,
		(byte)34,(byte)97,(byte)65,(byte)33,(byte)11,(byte)98,(byte)1,(byte)7,
		(byte)33,(byte)21,(byte)46,(byte)77,(byte)14,(byte)90,(byte)1,(byte)14};

	/**
	 * DECRIPTA L'UTENTE OTTENUTO DA SGU
	 * @param us
	 * @return
	 */
	public static String decUser(String us)
	{
		//////System.out.println("US "+us);
		if(us == null || us.length()==0)
		{
			return "unknown";
		}

		TripleDesCipher cipher = new TripleDesCipher();
	
		cipher.setKey(GWT_DES_KEY);
		String idUtCript="";
	
		try {
			idUtCript = cipher.decrypt(us);
		} catch (DataLengthException e1) {
			//e1.printStackTrace();
		} catch (IllegalStateException e1) {
			//e1.printStackTrace();
		} catch (InvalidCipherTextException e1) {
			//e1.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException e1) {
		//	////System.out.println("Warning utente non autenticato");
			return "unknown";
		}

		return idUtCript;
	}

	/**
	 * DECRIPTA IL RUOLO DELL'UTENTE OTTENUTO DA SGU
	 * @param role
	 * @return
	 */
	public static String decRole(String role)
	{
		if(role == null || role.length()==0)
		{
			return "unknown";
		}
	//	////System.out.println("role "+role);
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(GWT_DES_KEY);
		String roleUtCript="";

		try {
			roleUtCript = cipher.decrypt(role);
		} catch (DataLengthException e1) {
			//e1.printStackTrace();
		} catch (IllegalStateException e1) {
			//e1.printStackTrace();
		} catch (InvalidCipherTextException e1) {
			//e1.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException e1) {
			
			return "unknown";
		}

		return roleUtCript;
	}
	
	/**
	 * CRIPTA IL RUOLO OTTENUTO DA SGU
	 * @param role
	 * @return
	 */
	public static String encRole(String role)
	{
		if(role == null || role.length()==0)
		{
			return "unknown";
		}
		
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(GWT_DES_KEY);
		String roleUtCript="";
		role = "p1";
		try {
			roleUtCript = cipher.encrypt(role);
		} catch (DataLengthException e1) {
			//e1.printStackTrace();
		} catch (IllegalStateException e1) {
			//e1.printStackTrace();
		} catch (InvalidCipherTextException e1) {
			//e1.printStackTrace();
		}

		return roleUtCript;
	}
	
	
	/**
	 * CRIPTA L'UTENTE OTTENUTO DA SGU
	 * @param us
	 * @return
	 */
	public static String encUser(String us)
	{
		
		if(us == null || us.length()==0)
		{
			return "unknown";
		}

		TripleDesCipher cipher = new TripleDesCipher();
		us="admin";
		cipher.setKey(GWT_DES_KEY);
		String idUtCript="";
		try {
			idUtCript = cipher.encrypt(us);
		} catch (DataLengthException e1) {
			//e1.printStackTrace();
		} catch (IllegalStateException e1) {
			//e1.printStackTrace();
		} catch (InvalidCipherTextException e1) {
			//e1.printStackTrace();
		}

		return idUtCript;
	}
	public static String enc(String us)
	{
		
		if(us == null || us.length()==0)
		{
			return "unknown";
		}

		TripleDesCipher cipher = new TripleDesCipher();
		
		cipher.setKey(GWT_DES_KEY);
		String idUtCript="";
		try {
			idUtCript = cipher.encrypt(us);
		} catch (DataLengthException e1) {
			//e1.printStackTrace();
		} catch (IllegalStateException e1) {
			//e1.printStackTrace();
		} catch (InvalidCipherTextException e1) {
			//e1.printStackTrace();
		}

		return idUtCript;
	}	
	
	public static String dec(String us)
	{
		//////System.out.println("US "+us);
		if(us == null || us.length()==0)
		{
			return "unknown";
		}

		TripleDesCipher cipher = new TripleDesCipher();
	
		cipher.setKey(GWT_DES_KEY);
		String idUtCript="";
	
		try {
			idUtCript = cipher.decrypt(us);
		} catch (DataLengthException e1) {
			//e1.printStackTrace();
		} catch (IllegalStateException e1) {
			//e1.printStackTrace();
		} catch (InvalidCipherTextException e1) {
			//e1.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException e1) {
		//	////System.out.println("Warning utente non autenticato");
			return "unknown";
		}

		return idUtCript;
	}
	
	
}
