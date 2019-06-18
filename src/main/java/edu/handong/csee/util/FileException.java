package edu.handong.csee.util;

public class FileException  extends Exception{

	
	public FileException() {
		super("Wrong Excel File, Check the excel file.");
	}
	public  FileException(String file) {
		super("Wrong Excel File, Check the excel" +file);
	}
	
}


