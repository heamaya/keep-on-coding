package com.tipuana.csa.action.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
	
	public static void save(File sourceFile, String destinationPath, String sourceFileName) throws Exception  {        

    	FileInputStream fileInputStream = new FileInputStream(sourceFile);
    	FileOutputStream fileOutputStream = new FileOutputStream(new File(destinationPath, sourceFileName));
			
	    int sourceFileByte;

	    while ((sourceFileByte = fileInputStream.read()) != -1) {
	    	fileOutputStream.write(sourceFileByte);
	    }

		fileInputStream.close();
		fileOutputStream.close();
	}
	
	public static void delete(String filePath, String fileName) {
		File uploadedFile = new File(filePath, fileName);
		
		uploadedFile.delete();
	}

}