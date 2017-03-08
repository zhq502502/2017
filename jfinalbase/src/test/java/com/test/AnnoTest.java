package com.test;

public class AnnoTest {

	public static void main(String[] args) {
		t1(null);
	}
	
	public static void t1(@Anno(null_rep="你妹") String str){
		System.out.println(str);
	}

}
