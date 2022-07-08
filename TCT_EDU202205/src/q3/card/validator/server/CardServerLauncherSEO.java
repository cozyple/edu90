package q3.card.validator.server;

import java.util.Scanner;

public class CardServerLauncherSEO {	
	public static void main(String[] args) throws Exception {
		CardSocketServerSEO cardSvr = new CardSocketServerSEO();
		
		Thread th = new Thread(cardSvr);
		th.start();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String line = sc.nextLine();
			
			if(line.equals("QUIT")) {
				cardSvr.stop();
				break;
			}
		}
		
		sc.close();
	}
	
}