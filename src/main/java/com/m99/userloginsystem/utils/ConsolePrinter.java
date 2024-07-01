package com.m99.userloginsystem.utils;

public class ConsolePrinter {

	private static final boolean doPrint = true;
	public static void print(String s) {
		if(doPrint) {
			System.out.println(s);
		}
	}

	public static void handleException(Exception e, String message) {
		print(message);
		e.printStackTrace();
	}
}
