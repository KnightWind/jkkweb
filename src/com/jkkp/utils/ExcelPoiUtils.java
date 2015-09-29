package com.jkkp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * author: ccn<br/>
 * describe：Excel文档操作工具类，支持.xls .xlsx excel文件的读、写操作<br/>
 * 使用poi.jar实现
 */
public class ExcelPoiUtils {
	public static enum Type {
		XLS,
		XLSX
	}

	/**
	 * @param file excel类型文件
	 * @return excel文件的内容
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<String[]> readExcelFromFile(File file) throws IOException, InvalidFormatException {
		Type type = getExcelType(file);
		List<String[]> contentList = new ArrayList<String[]>();
		if(Type.XLS.equals(type)) {
			readHSSF(contentList, new NPOIFSFileSystem(file)); // File, needs less memory
		} else {
			readXSSF(contentList,OPCPackage.open(file)); // File, needs less memory
		}
		return contentList;
	}
	
	/**
	 * 推荐优先使用readExcelFromFile方法(内存相对占用更小)
	 * @param in excel类型的输入流
	 * @param fileType 
	 * @return 从输入流读取的excel文件的内容
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<String[]> readExcelFromInputStream(InputStream in, Type excelType) throws IOException, InvalidFormatException {
		List<String[]> contentList = new ArrayList<String[]>();
		if(Type.XLS.equals(excelType)) {
			readHSSF(contentList, new NPOIFSFileSystem(in)); // InputStream, needs more memory
		} else {
			readXSSF(contentList,OPCPackage.open(in)); // InputStream, needs more memory
		}
		in.close();
		return contentList;
	}

	/**
	 * 把内容写到excel文件
	 * @param file 要生成的Excel文件
	 * @param title  标题内容，如为null则不显示标题
	 * @param contenList 内容列表，列表中的元素类型为String[]
	 * @throws IOException
	 */
	public static void writeToExcelFile(File file, String title,List<String[]> contenList) throws IOException {
		Type type = getExcelType(file);
		OutputStream out = new FileOutputStream(file);
		writeToExcelOutputStream(out,title,contenList,type);
	}

	/**
	 * 把内容写到输出流
	 * @param out Excel文件输出流
	 * @param title  标题内容
	 * @param contenList 内容列表，列表中的元素类型为String[]
	 * @param fileType excel文件类型,xls/xlsx
	 * @throws IOException
	 */
	public static void writeToExcelOutputStream(OutputStream out, String title,List<String[]> contenList,Type fileType) throws IOException {
		long begin = System.currentTimeMillis();
		if(Type.XLS.equals(fileType)) { //HSSFWorkbook
			HSSFWorkbook wb = new HSSFWorkbook();
			writeContent(out, title, contenList, wb);
		} else { //XSSFWorkbook
			XSSFWorkbook wb = new XSSFWorkbook();
			writeContent(out, title, contenList, wb);
		}
		out.flush();
		out.close();
		long end = System.currentTimeMillis();
		System.out.println("生成了一个excel文件," + (end - begin) / 1000 + "秒");
	}
	
	/**向输出流写入excel内容*/
	private static void writeContent(OutputStream out, String title,List<String[]> contentList, Workbook wb) throws IOException {
		String time = new SimpleDateFormat("yyyy-MM-dd hh mm ss").format(new Date());
		String preSheetName = time + " sheet ";
		CellStyle style = wb.createCellStyle();
		Font f = wb.createFont();
		f.setFontHeightInPoints((short) 13);// 字号
		f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗
		style.setFont(f);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		CellRangeAddress range = new CellRangeAddress(0,  0,  0, contentList.get(0).length - 1);
		boolean hasTitle = org.apache.commons.lang.StringUtils.isNotBlank(title);
		if (null != contentList && !contentList.isEmpty()) {
			final int excelMaxRow = 65535;// 一个excel表格sheet最多只能存放65535条记录
			int contentSize = contentList.size();
			int sheetNum = (contentSize / excelMaxRow) + ((contentSize % excelMaxRow) > 0 ? 1 : 0);
			for (int i = 0; i < sheetNum; i++) {
				Sheet sheet = wb.createSheet();
				wb.setSheetName(i, preSheetName + (i + 1));
				Row row = null;
				Cell cell = null;
				int index = 0;// 记录数是否达到excelMaxRow
				if(hasTitle) {
					// region.setColumnTo((short)(contenList.get(0).length-1));
					sheet.addMergedRegion(range);
					// sheet.addMergedRegion(region);//合并第一行的单元格，用于设置标题
					row = sheet.createRow(0);
					cell = row.createCell(0);
					cell.setCellValue(title);// 设置标题
					cell.setCellStyle(style);
					index +=1; // 因为标题占用了一行，所以从index+1开始插入行
				}
				while (!contentList.isEmpty()) {
					row = sheet.createRow(index);
					String[] content = contentList.remove(0);
					int len = content.length;
					for (int k = 0; k < len; k++) {
						sheet.setColumnWidth(k, 7500);
						cell = row.createCell(k);
						cell.setCellValue(content[k]);
					}
					index++;
					if (index % excelMaxRow == 0 || index > contentSize) {
						index = 0;
						break;
					}
				}
				index = 0;
			}
		} else {
			title += "（无相关记录）";
			Sheet sheet = wb.createSheet();
			sheet.setColumnWidth(0, 9000);
			wb.setSheetName(0, preSheetName + 1);
			range.setLastColumn(10);
			sheet.addMergedRegion(range);
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue(title);// 设置标题
			cell.setCellStyle(style);
		}
		wb.write(out);
	}
	
	/**读取xlsx文件的内容*/
	private static void readXSSF(List<String[]> contentList, OPCPackage pkg) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		int sheetNum = wb.getNumberOfSheets();
		for (int index = 0; index < sheetNum; index++) {
			XSSFSheet xssfSheet = wb.getSheetAt(index);
			for (Row row : xssfSheet) {
				int cellNum = row.getLastCellNum();
				String[] cells = new String[cellNum];
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j);
					cells[j] = getCellContent(cell);
				}
				contentList.add(cells);
			}
		}
		pkg.close();
	}
	
	/**读取xls文件的内容*/
	private static void readHSSF(List<String[]> contentList, NPOIFSFileSystem fs) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fs.getRoot(),false);
		int sheetNum = hssfWorkbook.getNumberOfSheets();
		for (int index = 0; index < sheetNum; index++) {
			HSSFSheet sheet = hssfWorkbook.getSheetAt(index);
			for (Row row : sheet) {
				int cellNum = row.getLastCellNum();
				String[] cells = new String[cellNum];
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j);
					cells[j] = getCellContent(cell);
				}
				contentList.add(cells);
			}
		}
		fs.close();
	}
	
	/**获取单元格的内容，转换为String类型*/
	public static String getCellContent(Cell cell) {
		String content = null;
		if (cell == null)
			return null;	
		if(Cell.CELL_TYPE_NUMERIC == cell.getCellType() && DateUtil.isCellDateFormatted(cell)) {
			content = cell.getDateCellValue().getTime() + ""; //将时间转换为long，以字符串形式返回
		} else {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			content = cell.getRichStringCellValue().getString();
		}
		/*switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			content = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				content = cell.getDateCellValue().getTime() + "";
			} else {
				content = cell.getNumericCellValue() + "";
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			content = cell.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_FORMULA:
			content = cell.getCellFormula();
			break;
		default:
			content = cell.toString();
		}*/
		return content;
	}
	
	public static Type getExcelType(File file) {
		String fileName = file.getName();
		return getExcelType(fileName);
	}
	
	public static Type getExcelType(String fileName) {
		if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx"))
			throw new RuntimeException( "excel file name must end with '.xls' or '.xlsx'");
		Type type = Type.XLS;
		if(fileName.endsWith(".xlsx"))
			type = Type.XLSX;
		return type;
	}

	public static void main(String[] args) throws IOException, InvalidFormatException {
		String fileName = "";
		fileName = System.getProperty("user.dir") + File.separator + "workbook.xls";
		//fileName = System.getProperty("user.dir") + File.separator + "wb3.xlsx";
		List<String[]> contentList = new ArrayList<String[]>();
		File file = new File(fileName);
		System.out.println(file.getAbsolutePath());
		 FileOutputStream fileOut = new FileOutputStream(file); 
		 String  title = ""; 
		 int col = 4; 
		 for(int row=0;row<5;row++){ 
			 String[] content = new String[col]; 
			 for(int i = 0;i<col;i++){ 
				 content[i] = "单元格内容("+i+","+row+")"; 
			} 
			 contentList.add(content); 
		} 
	   writeToExcelOutputStream(fileOut,title,contentList,getExcelType(file));
		 
		contentList = readExcelFromFile(file);
		for (String[] cells : contentList) {
			for (String cell : cells) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------");
		InputStream fin = new FileInputStream(file);
		contentList = readExcelFromInputStream(fin,getExcelType(file));
		for (String[] cells : contentList) {
			for (String cell : cells) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
	}
}