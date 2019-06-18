package edu.handong.csee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.handong.csee.util.*;
public class ExcelReader  {
	private static   boolean headerSummary =false ; 
	private static   boolean headerTable =false ; 

	
	public ArrayList<String> getDataSummary(InputStream is, String Id, String output) throws IOException {
		
		ArrayList<String> values = new ArrayList<String>();
		String[] combineSummary = new String[7];
		ArrayList<String> lines = new ArrayList();
		Util util = new Util();
	 String headerofSummary = "제목,요약문(300자 내외),핵심어(keyword,쉼표로 구분),조회날짜,실제자료조회 출저(웹자료링크),원출저(기관명 등),제작자(Copyright.소유처)";
	if(!headerSummary) {
		lines.add(headerofSummary);
		headerSummary = true;
	}
	 try (InputStream inp = is) {
		 	XSSFWorkbook workbook=new XSSFWorkbook(inp);
	    	int rowindex=0;
	    	int columnindex=0;
	    
	    	XSSFSheet sheet=workbook.getSheetAt(0);
	    	int rows=sheet.getPhysicalNumberOfRows();
	    	for(rowindex=1;rowindex<rows;rowindex++){

	    	    XSSFRow row=sheet.getRow(rowindex);
	    	    if(row !=null){
	    	     
	    	        int cells=row.getPhysicalNumberOfCells();
	    	        for(columnindex=0;columnindex<=cells;columnindex++){

	    	            XSSFCell cell=row.getCell(columnindex);
	    	            String value="";
	   
	    	            if(cell==null){
	    	                continue;
	    	            }else{
	    	                switch (cell.getCellType()){
	    	                case FORMULA:
	    	                    value=cell.getCellFormula();
	    	                    break;
	    	                case NUMERIC:
	    	                    value=cell.getNumericCellValue()+"";
	    	                    break;
	    	                case STRING:
	    	                    value=cell.getStringCellValue()+"";
	    	                    break;
	    	                case BLANK:
	    	                    value=cell.getBooleanCellValue()+"";
	    	                    break;
	    	                case ERROR:
	    	                    value=cell.getErrorCellValue()+"";
	    	                    break;
	    	                }
	    	                
	    	            }
	    	            combineSummary[columnindex] = value;
	    	        }
	    	   ExcelReader ne = new ExcelReader();
	    	       String line = ne.conversionSummary(combineSummary,Id);
	    	       if(line != null) {
	    	    	     lines.add(line);
	    	       }
	    	    }
	}   	 
	          util.writeAFile(lines, output+"1.csv");
	  	
		}catch(Exception e){
			try {
				throw new FileException();
			} catch (FileException e1) {
				
				e1.printStackTrace();
			
			}
		}
	 // System.out.println(lines);
    	return values;	
	}

	public String conversionSummary(String[] original, String Id) {
	
		String title;
		String summery;
		String keyword;
		String date;
		String web;
		String refer;
		String copyright;
		  String line = null;
		  
		  
		if(!original[0].contains("false")) {
			 title = original[0];
		        summery =original[1].trim().replace("\"", " ");
		        keyword =original[2].trim();
		        date =original[3].trim();
		        web = original[4].trim();
		        refer = original[5].trim();
		        copyright =original[6].trim();
		       System.out.println(keyword);
				//System.out.println(summery);
		    line = "\""+Id+"\""+","+"\""+title+ "\""+","+ "\""+summery+ "\""+ ","+"\""+keyword +"\""+  
		    		","+"\""+date +"\""+ ","+"\""+ web + "\""+ ","+"\""+refer + "\""+ ","+"\""+copyright+"\"" ;
		}
       
	    return line;
	}
	
	public ArrayList<String> getDataTable(InputStream is, String Id,String output) throws IOException {
	
		ArrayList<String> values = new ArrayList<String>();
		String[] combineTable= new String[5];
		   ArrayList<String> lines = new ArrayList();
		   Util util = new Util();
		   String headeroftable = "제목(반드시 요약문 양식에 입력한 제목과 같아야 함.),표/그림 일련번,자료유형(표,그림...),자료에 나온 표나 그림 설명(캡션),자료가 나온 쪽 번호";
			if(!headerTable) {
				lines.add(headeroftable);
				headerTable = true;
			}
	 
		try (InputStream inp = is) {
		 	XSSFWorkbook workbook=new XSSFWorkbook(inp);
	    	int rowindex=0;
	    	int columnindex=0;
	    
	    	XSSFSheet sheet=workbook.getSheetAt(0);
	    	int rows=sheet.getPhysicalNumberOfRows();
	    	for(rowindex=2;rowindex<rows;rowindex++){

	    	    XSSFRow row=sheet.getRow(rowindex);
	    	    if(row !=null){
	    	     
	    	        int cells=row.getPhysicalNumberOfCells();
	    	        for(columnindex=0;columnindex<=cells;columnindex++){

	    	            XSSFCell cell=row.getCell(columnindex);
	    	            String value="";
	   
	    	            if(cell==null){
	    	                continue;
	    	            }else{
	    	                switch (cell.getCellType()){
	    	                case FORMULA:
	    	                    value=cell.getCellFormula();
	    	                    break;
	    	                case NUMERIC:
	    	                    value=cell.getNumericCellValue()+"";
	    	                    break;
	    	                case STRING:
	    	                    value=cell.getStringCellValue()+"";
	    	                    break;
	    	                case BLANK:
	    	                    value=cell.getBooleanCellValue()+"";
	    	                    break;
	    	                case ERROR:
	    	                    value=cell.getErrorCellValue()+"";
	    	                    break;
	    	                }
	    	                
	    	            }
	    	            combineTable[columnindex] = value;
	    	        }
	    	   ExcelReader ne = new ExcelReader();
	    	       String line = ne.conversionTable(combineTable,Id);
	    	       if(line != null) {
	    	    	     lines.add(line);
	    	       }
	    	    }
	}   	 
	          util.writeAFile(lines, output+"2.csv");
	  	
		}catch(Exception e){
			try {
				throw new FileException();
			} catch (FileException e1) {
				
				e1.printStackTrace();
			
			}
		}
	 // System.out.println(lines);
    	return values;	
	}
	

	public String conversionTable(String[] original, String Id) {
	
		String title;
		String table;
		String type;
		String explain;
		String page;
		
		  String line = null;
		  
		if(!original[0].contains("false")) {
				title = original[0];
	
		        table = original[1].trim();
		        type = original[2].trim();
		        explain =original[3].trim();
		        page = original[4].trim();
		    line = "\""+Id+"\""+","+"\""+title+ "\""+","+"\""+ table+ "\""+","+"\""+type +"\""+ ","+"\""+explain + "\""+","+ "\""+page+"\"";
		}else {
			
	        table = original[1].trim();
	        type =original[2].trim();
	        explain = original[3].trim();
	        page =original[4].trim();
	        line = "\""+Id+"\""+","+"\""+" "+ "\""+","+"\""+ table+ "\""+","+"\""+type +"\""+ ","+"\""+explain + "\""+","+ "\""+page+"\"";
	    	
	
		}
       
	    return line;
	}
	
	
	
	

}	