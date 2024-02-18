package com.m99.userloginsystem.utils;

public class ConsolePrinter {

	private static final boolean doPrint = true;
	public static void print(String s) {
		if(!doPrint)
			return;
		System.out.print(s);
	}

	public static void println(String s) {
		if(!doPrint)
			return;
		System.out.println(s);
	}

	public static void handleException(Exception e, String message) {
		println(message);
		e.printStackTrace();
	}
}
