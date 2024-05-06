package com.m99.userloginsystem.utils;

import java.io.File;

public class DirectoryUtils {
	public static void deleteDirectory(File directory) {
		if(directory.isFile())
			directory.delete();
		else {
			for(File file: directory.listFiles()){
				if(file.isFile())
					file.delete();
				else
					deleteDirectory(file);
			}
		}
	}
}
