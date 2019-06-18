package edu.handong.csee.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Util {
	public void writeAFile(ArrayList<String> lines, String targetFileName) throws IOException {
		PrintWriter outputStream = null;
		try {
			//outputStream = new PrintWriter(targetFileName);
			outputStream = new PrintWriter(new FileWriter(targetFileName,true));
		} catch (FileNotFoundException e) {
			File tmp = new File(targetFileName);
			tmp.getParentFile().mkdirs();
			try {
				tmp.createNewFile();
				outputStream = new PrintWriter(targetFileName);
			} catch (IOException e1) {

			}
		}
		for (String out : lines) {
			outputStream.println(out);
		}
		outputStream.close();
	}
}
