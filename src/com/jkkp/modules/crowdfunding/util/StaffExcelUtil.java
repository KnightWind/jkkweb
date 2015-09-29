package com.jkkp.modules.crowdfunding.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;

public class StaffExcelUtil {

	/**
	 * 
	 * @param title  Excel标题
	 * @param dataList 数据源
	 * @param fileName 保存的文件名
	 * @param path 存储的路径
	 * @param req HttpServletRequest
	 * @throws Exception
	 */
	 public static void educe(String title,List<VSupplierCompanyStaff> dataList,List<String> cellName,String fileName,String path,HttpServletRequest req,HttpServletResponse res) throws Exception{
		// 第一步，创建一个workbook，对应一个Excel文件
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet(title);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow hssfRow = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
        //居中样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.TURQUOISE.index);
        font.setFontHeightInPoints((short) 16);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);     
        
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
        
        
        HSSFCell hssfCell = hssfRow.createCell(0);//列索引从0开始
        
        //设置顶部列名
        int i=1;
        for (String item : cellName) {
        	 hssfCell.setCellValue(item);//列名
        	 hssfCell.setCellStyle(style);//列居中显示
        	 hssfCell = hssfRow.createCell(i);
        	 i++;
		}
        
        Integer index=0;
        //把数据写入Excel
        for (VSupplierCompanyStaff bean : dataList) {
        	hssfRow = hssfSheet.createRow(index+1);

		    hssfRow.createCell(0).setCellValue(bean.getMobile()==null?"":bean.getMobile().toString());
		    hssfRow.createCell(1).setCellValue(bean.getName()==null?"":bean.getName().toString());
		    hssfRow.createCell(2).setCellValue(bean.getBranchName()==null?"":bean.getBranchName().toString());
		    hssfRow.createCell(3).setCellValue(bean.getLeaderName()==null?"":bean.getLeaderName().toString());
		    hssfRow.createCell(4).setCellValue(bean.getTotalSttleMoney()==null?"":bean.getTotalSttleMoney().toString());
		    hssfRow.createCell(5).setCellValue(bean.getStatusVal()==null?"":bean.getStatusVal().toString());
		    
		    index++;
				  
		}
        
        //将文件存储到指定位置
        String savePath=req.getServletContext().getRealPath(path);
        FileOutputStream outPutStream=new FileOutputStream(savePath+fileName);
        workbook.write(outPutStream);
        outPutStream.close();
        
        try {
        	downLoad(savePath+fileName, res,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 private static void downLoad(String filePath, HttpServletResponse response,boolean isOnLine) throws Exception {
		 File f = new File(filePath);
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;
			response.reset(); // 非常重要
			// 在线打开方式
			if (isOnLine) {
				URL u = new URL(filePath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; filename="+ toUTF8(f.getName()));
				// 文件名应该编码成UTF-8
			}
			// 纯下载方式
			else {
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ toUTF8(f.getName()));
			}
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			out.flush();
			br.close();
			out.close();
		}

		public static String toUTF8(String s) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes("utf-8");
					} catch (Exception ex) {
						System.out.println(ex);
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
			return sb.toString();
		}

}
