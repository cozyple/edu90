package q2.card.validator.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import q2.card.validator.utils.CardUtility;

public class ValidatorSeo {
	private String insId;
	private String onBusId;
	private String onBusTime;
	
	public boolean login(String id, String psw) throws NoSuchAlgorithmException, IOException {
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader("..//CLIENT//INSPECTOR.TXT"));
			String line;
			String encPsw = CardUtility.passwordEncryption(psw);
			while ((line = in.readLine()) != null) {
				String fileId = line.substring(0, 8);
				String filePsw = line.substring(9);
	
				if (id.equals(fileId) && filePsw.equals(encPsw)) {
					insId = id;
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) { in.close(); }
		}

		return false;
	}

	public void logout() {
		insId = null;
	}

	public void getOnBus(String busId) {
		
		onBusId = busId;
		onBusTime = CardUtility.getCurrentDateTimeString();
		
	}

	public void getOffBus() {
		onBusId = null;
		
	}
	//[카드ID(8)][버스ID(7)][승차/하차 코드(1)][최근 승차시각(14)]
	public void inspectCard(String cardInfo) throws Exception {
		//체크
		//System.out.println("11111"  + cardInfo);
		
		String ResultCode = null;
		
		if(onBusId != null) {
			String cardId = cardInfo.substring(0, 8);
			String busId = cardInfo.substring(8, 15);
			String getOnYn = cardInfo.substring(15, 16);
			String getOnTime = cardInfo.substring(16);
			
			//System.out.println(cardId + "#" + busId + "#" + getOnYn + "#" + getOnTime);
			
			String insTime = CardUtility.getCurrentDateTimeString();
			
			if(onBusId.equals(busId)) {
				if(getOnYn.equals("N")) {
					if(CardUtility.hourDiff(insTime, getOnTime) < 3) {
						ResultCode = "R1";
					}else {
						ResultCode = "R4";
					}
					
				}else {
					ResultCode = "R3";
				}
			}else {
				ResultCode = "R2";
			}
			
			saveFile(cardInfo, ResultCode, insTime);
		}

	}

	private void saveFile(String cardInfo, String resultCode, String insTime) throws IOException {
		// TODO Auto-generated method stub
		File destFolder = new File("../" + insId);
		if(!destFolder.exists()) {
			destFolder.mkdirs();
		}
		
		String filename = destFolder + "/" + insId + "_" + onBusTime + "TXT";
		
		FileWriter fw = new FileWriter(filename, true);
		
		fw.write(insId + "#" + onBusId + "#" + cardInfo + "#" + resultCode + "#" + insTime + "\n");
		
		if(fw !=null) fw.close();
	}



}