package com.edu.math;

public class TEST01 {

	//2진수 연산
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "101100";
		String b = "1011";
		
		
		int a1 = Integer.parseInt(a, 2);
		int b1 = Integer.parseInt(b, 2);
		
		
		System.out.println(a1+b1);
		
		String result = Integer.toBinaryString(a1+b1);
		
		System.out.println(result);
		
		String format = String.format("%010d", Integer.parseInt(result));
		
		System.out.println(format);

	}
	

}


