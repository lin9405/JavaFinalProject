package edu.handong.csee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import edu.handong.csee.util.*;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ReadFileInZip implements Runnable {
	String path;
	String output;
	String Id;

	ReadFileInZip(String path,String output) {
		this.path = path;
		String[] Idx = path.split(".zip");
 		String[] ID = Idx[0].split("/");
		this.Id = ID[1];
		this.output = output;
		
	}

	public void run() {
		ZipFile zipFile;

		try {
			zipFile = new ZipFile(path, "EUC-KR");
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				InputStream stream = zipFile.getInputStream(entry);
				ExcelReader myReader = new ExcelReader();
				if (entry.isDirectory()) {
					if (entry.getName().contains("요약문")) {
						myReader.getDataSummary(stream,Id,output);
					} else if (entry.getName().contains("표")) {
						myReader.getDataTable(stream,Id,output);
						
					}
				} else {
					if (entry.getName().contains("요약문")) {
						myReader.getDataSummary(stream,Id,output);
					} else if (entry.getName().contains("표")) {
						myReader.getDataTable(stream,Id,output);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
