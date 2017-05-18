package functioncodetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class readXml {
//	public static void main(String[] args) {  
//		readXml obj = new readXml();  
//        File file = new File("src/main/resources/comment.xls");  
//        obj.readExcel(file);  
//    }  
    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象  
    public List<String> readExcel(File file) {  
    	List<String> strList = new ArrayList<String>();
        try {  
            // 创建输入流，读取Excel  
            InputStream is = new FileInputStream(file.getAbsolutePath());  
            // jxl提供的Workbook类  
            Workbook wb = Workbook.getWorkbook(is);  
            // Excel的页签数量  
            int sheet_size = wb.getNumberOfSheets();  
            for (int index = 0; index < sheet_size; index++) {  
                // 每个页签创建一个Sheet对象  
                Sheet sheet = wb.getSheet(index);  
                // sheet.getRows()返回该页的总行数  
                for (int i = 1; i < sheet.getRows(); i++) {  
                    // sheet.getColumns()返回该页的总列数  
//                    for (int j = 0; j < sheet.getColumns(); j++) {  
                        String cellinfo = sheet.getCell(8, i).getContents();  
                        strList.add(cellinfo);
//                        System.out.println(sheet.getCell(0, i).getContents() +" : "+cellinfo);  
//                    }  
//                    System.out.println("----------------------");
                }  
            } 
            return strList;
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            return null;
        } catch (BiffException e) {  
            e.printStackTrace();   
            return null; 
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;  
        }
    }  
}
