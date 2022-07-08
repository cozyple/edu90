package com.edu.thread;

class ThreadClass extends Thread{
	private String name;
	
	public ThreadClass(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("[" + name + "]" + i);
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}


public class ThreadTest01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new ThreadClass("Thread1");
		Thread t2 = new ThreadClass("Thread2");
		t1.start();
		t2.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("[Main]" + i);
			sleep(1);
			
		}
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void sleep(int i) {
		// TODO Auto-generated method stub
		
	}

}
