package com.jkkp.modules.sale_theme.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.sale_theme.mapper.ActivityJoinSignMapper;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.view.VDaoChu;
import com.jkkp.modules.sale_theme.view.VRecommend;
import com.jkkp.utils.DateUtil;
import com.jkkp.utils.ExcelPoiUtils;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("wxActivityJionSign")
public class ActivityJionSignController extends BaseController {

	@Autowired
	private IActivityJionSignService activityJionSignService;
	@Autowired
	private ActivityJoinSignMapper activityJoinSignMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityJoinSignMapper,"selectAllActivityJoinSign", "selectAllActivityJoinSignCount");
		request.setAttribute("pagination",activityJionSignService.paginationCustom());
		return "/saleActivity/activityJoinSign_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityJoinSignMapper,"selectAllActivityJoinSign", "selectAllActivityJoinSignCount");
		return new ResponsePagination(activityJionSignService.paginationCustom());
	}
	@RequestMapping(value = "/daochu.do")
	public void daochu(Date start,Date end,HttpServletRequest req,HttpServletResponse res) {		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> contentList = new ArrayList<String[]>();
			String[] title = new String[]{"手机号码","VPI卡","姓名","代金券ID","报名时间"};
			contentList.add(title);
			Map<String,Object> map = new HashMap<String,Object>();
	        if(start != null) {
	        	map.put("start",DateUtil.format(start, "yyyy-MM-dd 00:00:00"));
	        }
	        if(end != null) {
	        	map.put("end",DateUtil.format(end, "yyyy-MM-dd 23:59:59"));
	        }
	        List<VDaoChu> list = activityJionSignService.querydaochu(map);
		        for (int i = 0; i < list.size(); i++) {
		        	VDaoChu daoChu = list.get(i);
		        	String[] item = new String[]{daoChu.getPhone(),daoChu.getKa(),daoChu.getName(),daoChu.getAid(),daoChu.getTime()};
		        	contentList.add(item);
		        }
			String fileName = "报名统计" + sdf.format(new Date()) + ".xls";
			res.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream out = res.getOutputStream();
			res.setContentType("application/vnd.ms-excel");
			ExcelPoiUtils.writeToExcelOutputStream(out, fileName, contentList, ExcelPoiUtils.Type.XLS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
    }
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/activity")
	public String activity(HttpServletRequest request) {
		return "/saleActivity/activity";
	}
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/derive")
	public String derive(HttpServletRequest request) {
		return "/saleActivity/recommend";
	}
	@RequestMapping(value = "/recommend.do")
	public void recommend(Date start,Date end,HttpServletRequest req,HttpServletResponse res) {		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> contentList = new ArrayList<String[]>();
			String[] title = new String[]{"推荐人手机号码","被推荐人手机号码","推荐时间"};
			contentList.add(title);
			Map<String,Object> map = new HashMap<String,Object>();
	        if(start != null) {
	        	map.put("start",DateUtil.format(start, "yyyy-MM-dd 00:00:00"));
	        }
	        if(end != null) {
	        	map.put("end",DateUtil.format(end, "yyyy-MM-dd 23:59:59"));
	        }
			  List<VRecommend> list = activityJionSignService.queryRecommend(map);
		        for (int i = 0; i < list.size(); i++) {
		        	VRecommend daoChu = list.get(i);
		        	String[] item = new String[]{daoChu.getPhone(),daoChu.getTphone(),daoChu.getTime()};
		        	contentList.add(item);
		        }
			String fileName = "推荐统计" + sdf.format(new Date()) + ".xls";
			res.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream out = res.getOutputStream();
			res.setContentType("application/vnd.ms-excel");
			ExcelPoiUtils.writeToExcelOutputStream(out, fileName, contentList, ExcelPoiUtils.Type.XLS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
    }
}
