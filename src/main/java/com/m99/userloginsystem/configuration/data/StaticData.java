package com.m99.userloginsystem.configuration.data;

import java.io.File;

public class StaticData {
	private static String dataDirectoryName = "UserLoginSystemData";
	private static String applicationDataDirectory = "";
	public static String getApplicationDataDirectory() {
		return applicationDataDirectory;
	}
	public static void setApplicationDataDirectoryParent(String parentDirectoryPath) {
		applicationDataDirectory = parentDirectoryPath + File.separator + dataDirectoryName;
	}
}
