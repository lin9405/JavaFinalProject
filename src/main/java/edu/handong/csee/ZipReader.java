
package edu.handong.csee;

import java.util.*;
import java.io.*;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader  {
	
	ArrayList<String> fileName = new ArrayList<>();
	ArrayList<Integer> zipSort = new ArrayList<>();
	public void run(String input, String output) {

		File dir = new File(input);
		File[] fileList = dir.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			if (file.isFile()) {
				fileName.add(file.getName());
			} else if (file.isDirectory()) {
			}
		}
		
		Iterator it = fileName.iterator();
		while (it.hasNext()) {
			String value = (String) it.next();
			System.out.println(value);

			String[] a = value.split(".zip");
			int sortzip = Integer.parseInt(a[0]);
			int[] sortArray = new int[5];
			zipSort.add(sortzip);
			Collections.sort(zipSort);
		}
		ArrayList<Thread> threadReader = new ArrayList<>();
		ArrayList<String> finalPath =new ArrayList<>();
		ArrayList<Thread> threadZip = new ArrayList<Thread>();
		String Id = null;
		for (int a : zipSort) {
			Id =String.format("%04d", a);
			String b = (input+"/" +Id+".zip");
			finalPath.add(b);
		}	
		
		for(String zipPath : finalPath) {
			Thread thread= new Thread(new ReadFileInZip(zipPath,output));
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			threadZip.add(thread);
		}
		
	}

}