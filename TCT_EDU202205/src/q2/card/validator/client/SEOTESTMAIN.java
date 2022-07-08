package q2.card.validator.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SEOTESTMAIN {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String line, strId, strPassword;

		ValidatorSeo validator = new ValidatorSeo();
		while (true) {
			
			String [] words = scanner.nextLine().split(" "); // id, password
			
			strId = words[0]; 
			strPassword = words[1]; 

			if (validator.login(strId, strPassword)) {
				System.out.println("LOGIN SUCCESS");
				break;
			}
			else {
				System.out.println("LOGIN FAIL");
			}			
		}
		
		// Inspection
		while (true) {
			line = scanner.nextLine();	// busId
			
			//logout
			if (line.equals("LOGOUT")) {
				validator.logout();
				break;
			}
			//bus get on
			validator.getOnBus(line);
			
			// Card Validation
			while (true) {
				line = scanner.nextLine();	//cardInfo
				
				//get off
				if (line.equals("DONE")) {
					validator.getOffBus();
					break;
				}
				
				validator.inspectCard(line);
			}
			
		}
	}

}
