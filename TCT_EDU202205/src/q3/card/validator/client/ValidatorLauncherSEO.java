package q3.card.validator.client;

import java.util.Scanner;

public class ValidatorLauncherSEO {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String line, strId, strPassword;

		ValidatorSEO validator = new ValidatorSEO();
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
			
			if (line.equals("LOGOUT")) {
				validator.logout();
				break;
			}
			
			validator.getOnBus(line);

			// Card Validation
			while (true) {
				line = scanner.nextLine();	//cardInfo
				
				if (line.equals("DONE")) {
					validator.getOffBus();
					break;
				}
				
				validator.inspectCard(line);
			}
		}
	}
}