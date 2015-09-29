package com.jkkp.appapi.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.FileUtil;
import com.jkkp.utils.ProjectContext;

public class HttpFileTools {

	public static String path = Sysconfig.CONFIG_PARAM.get(ConfigConstant.PHOTO_SAVE_DIR);
//	public static String path = "D:/apache-tomcat-7.0.40/webapps/jkkpInter/image/";
	public static Integer fileNumParam=6;
	
	public static Map<String, Object> uploadMulti(MultipartRequest request) {
		

		Map<String, Object> dealResult = new HashMap<String, Object>();
		Date date=new Date();
		String name="";
		StringBuffer fileBuffer=new StringBuffer();
		boolean result=true;

		
		try {
			Map<String, MultipartFile> map = request.getFileMap();
			for (String fileName : map.keySet()) {
				MultipartFile file = map.get(fileName);
				if(file.getSize()>0){
					if (file != null) {

						name = date.getTime()+file.getOriginalFilename();
						
						File localFile = new File(path+name);
						//将上传文件写入到指定文件出、核心！
						file.transferTo(localFile);
						//非常重要、有了这个想做什么处理都可以
						//fileDetail.getInputStream();
	
						//保存文件名返回一般存数据库
						fileBuffer.append(name+",");
						}
				}else{
					result=false;
				}
			}
		}catch(Exception e){
			result=false;
			e.printStackTrace();
		}finally{
			dealResult.put("result", result);
			dealResult.put("fileName", fileBuffer.toString());
			return dealResult;
		}
	}
	
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String fileName) {  
	   boolean flag = false;  
	   File file = new File(getDir()+fileName);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	
	//获取app端删除的url图片文件名
	
	public static String  getPhotoUrl(ArrayList<String> fileUrl){
		StringBuffer fileName=new StringBuffer();
		for(int i=0;i<fileUrl.size();i++){
			String[] fileArrayV=fileUrl.get(i).split("/");
			if(fileArrayV.length>0)
				fileName.append(fileArrayV[fileNumParam]+",");
		}
		
		return fileName.toString();
	}
	
	public static String getDir(){
		String filePath = AttachmentConstant.FILE_PATH + FileUtil.createPath(null);
		String realPath = getBasePath() + filePath;
		return realPath;
	}
	
	public static String getBasePath() {
		// return basepath;
		return ProjectContext.getRealPath();
	}
}
