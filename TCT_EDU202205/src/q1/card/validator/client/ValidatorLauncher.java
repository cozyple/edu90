package q1.card.validator.client;

import java.io.IOException;
import java.util.Scanner;

public class ValidatorLauncher {
	public static void main(String[] args) throws IOException {
		Validator v = new Validator();
		Scanner sc = new Scanner(System.in);
		
		String inspector, passwd;
		
		while(true) {
			String line = sc.nextLine();
			
			String word[] = line.split(" ");
			inspector = word[0];
			passwd = word[1];
			
			if(v.login(inspector, passwd)) {
				System.out.println("LOGIN SUCCESS");
				break;
			}else {
				System.out.println("LOGIN FAIL");
			}
			
		}
		
	}
}