package com.edu.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileProc01 {
	
	private static String rootDir = ".\\INPUT";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		fileSearchAll(rootDir);
		
	}

	private static void fileSearchAll(String path) {
		// TODO Auto-generated method stub
		File f = new File(path);
		
		File[] fileList = f.listFiles();
		
		for (File file : fileList) {
			if(file.isDirectory()){
				fileSearchAll(file.getPath());
			}else {
				String partPath = path.substring(rootDir.length());
				if(file.length() > 3*1034) {
					System.out.println(partPath + file.getName() + " " + file.length());
					copyFile(partPath, file.getName());
				}
			}
			
		}
		

		
	}

	private static void copyFile(String partPath, String fileName) {
		// TODO Auto-generated method stub
		
		final int BUFFER_SIZE = 512;
		int readLen;
		try {
			//폴더생성
			File destFolder = new File(".\\OUTPUT" + partPath);
			if(!destFolder.exists()) {
				destFolder.mkdirs();
			}
			
			//copyFile	
	
			FileInputStream fis = new FileInputStream(".\\INPUT"+partPath+"\\"+ fileName);
			FileOutputStream fos = new FileOutputStream(".\\OUTPUT"+partPath+"\\"+ fileName);
			byte[] buffer = new byte[BUFFER_SIZE];
			
			while( (readLen = fis.read(buffer)) != -1 ) {
				fos.write(buffer, 0, readLen);
			}
			
			fis.close();
			fos.close();			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
