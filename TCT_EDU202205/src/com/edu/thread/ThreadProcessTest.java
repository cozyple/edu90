package com.edu.thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ThreadProcessTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(String.format("Start - " + new Date().toString()));
		
		try {
			System.setIn(new FileInputStream("src/com/edu/thread/NUM.TXT"));
			Scanner sc = new Scanner(System.in);
			
			List<ProcessThread1> listThread = new ArrayList<ProcessThread1>();
			while(sc.hasNextLine()) {
				//System.out.println(sc.nextLine());
				String line = sc.nextLine();
				String[] word = line.split(" ");
				ProcessThread1 th = new ProcessThread1(Integer.parseInt(word[0]), Integer.parseInt(word[1]));
				th.start();
				listThread.add(th);
			}
			
			for (ProcessThread1 processThread : listThread) {
				processThread.join();
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(String.format("End - " + new Date().toString()));
	}

}

class ProcessThread1 extends Thread { // 
	int num1;
	int num2;
    public ProcessThread1(int n1, int n2) { 
    	num1 = n1;
    	num2 = n2;
    } 

    public void run() {
		String output;
		try {
			output = getProcessOutput(Arrays.asList("add_2sec.exe",Integer.toString(num1),Integer.toString(num2)));
			System.out.println(num1 + " + " + num2 + " = " + output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				    	
    }

	private String getProcessOutput(List<String> cmdList) throws IOException {
		ProcessBuilder pb = new ProcessBuilder(cmdList);
		Process p = pb.start();
		InputStream is = p.getInputStream();
		byte[] buffer = new byte[1024];
		int len = is.read(buffer);
		return (new String(buffer, 0, len));
	} 
}
