package q1.card.validator.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import q1.card.validator.utils.CardUtility;

public class Validator {
	
	public boolean login(String inspector, String passwd) throws IOException {
		BufferedReader br = null;
		
		try {
			String line;
			br = new BufferedReader(new FileReader("CLIENT//INSPECTOR.TXT"));
			
			String pw = CardUtility.passwordEncryption(passwd);

			while((line = br.readLine()) != null) {
				String id = line.substring(0, 8);
				String pass = line.substring(9);
				
				if(id.equals(inspector)&& pass.equals(pw)) {
					return true;
				}
			}			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (br != null) { br.close(); }
		}
		return false;
	}

}
