
package edu.handong.csee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader {
/*
	public static void main(String[] args) {
		ZipReader zipReader = new ZipReader();
		zipReader.run(args);
	}

	private void run(String[] args) {
		//String path = args[0];
		
		readFileInZip("data");
		
	}
	*/

	public void readFileInZip(String path) {
		ZipFile zipFile;
		/*
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

	System.out.println(entries);
	
		    while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		     String name=   zipFile.getEncoding();
		     
		     System.out.println(name);
		      //  System.out.println(stream);
		        ExcelReader myReader = new ExcelReader();
		        
		        for(String value:myReader.getData(stream)) {
		        
		      //  	System.out.println(value);
		        }
		    }
		} 
		
		*/
	try {
		zipFile = new ZipFile(path);

		Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

		while(entries.hasMoreElements()){
			ZipArchiveEntry entry = entries.nextElement();
		    if(entry.isDirectory()){
		        System.out.println("dir  : " + entry.getName());
		    } else {
		        System.out.println("file : " + entry.getName());
		    }
		}
		
	}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}