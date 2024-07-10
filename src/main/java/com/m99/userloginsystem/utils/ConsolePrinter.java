package com.m99.userloginsystem.utils;

public class ConsolePrinter {

	private static final boolean doPrint = true;
	public static void printInfo(String s) {
		if(doPrint) {
			System.out.println("#[INFO]"+s);
		}
	}

	public static void printError(String s) {
		if(doPrint) {
			System.err.println("#[ERROR]"+s);
		}
	}

	public static void printException(Exception e, String message) {
		printError(message);
		printError(e.getMessage());
	}
}
