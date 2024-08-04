package com.m99.userloginsystem.configuration.data;

import java.io.File;

import com.m99.userloginsystem.utils.DirectoryUtils;

public class StaticData {
	private static String dataDirectoryName = "UserLoginSystemData";
	private static String applicationDataDirectory = "";
	public static String getApplicationDataDirectory() {
		return applicationDataDirectory;
	}
	public static void setApplicationDataDirectoryParent(String parentDirectoryPath, ApplicationDataDirectoryPolicy policy) {
		applicationDataDirectory = parentDirectoryPath + File.separator + dataDirectoryName;
		File file = new File(applicationDataDirectory);
		if(!file.exists())
			file.mkdirs();
		else {
			if(policy.equals(ApplicationDataDirectoryPolicy.CREATE)) {
				DirectoryUtils.deleteDirectory(file);
				file.mkdirs();
			}
		}
	}
}
