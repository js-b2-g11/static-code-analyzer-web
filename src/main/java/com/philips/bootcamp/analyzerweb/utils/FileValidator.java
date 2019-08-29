package com.philips.bootcamp.analyzerweb.utils;

import java.io.File;

public class FileValidator {	
	
	public static boolean isValidPath(String filepath) {		
		
		boolean exists = false;
		try
		{
		File tempFile = new File(filepath);
		
		 exists = tempFile.exists();
		}
		catch(Exception e) 
		{
			System.err.println("Hey, an error here!");
			exists = false;
		}		
		return exists;		
	}
	
}
