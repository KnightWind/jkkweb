package com.jkkp.appapi.common.control.supervisor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.appapi.common.service.interfaces.IAcceptanceService;
import com.jkkp.appapi.modules.mapper.VAcceptanceComment;
import com.jkkp.appapi.modules.mapper.VAcceptanceDetail;
import com.jkkp.appapi.modules.mapper.VAcceptanceItem;
import com.jkkp.appapi.modules.mapper.VAcceptionMainInfo;
import com.jkkp.appapi.modules.mapper.VSupervisorReport;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/")
public class AcceptanceController extends BaseController {

	@Autowired
	private IAcceptanceService acceptanceService;
	@Autowired
	private IAttachmentService attachmentService;

	@ResponseBody
	@RequestMapping("acceptance_report.do")
	public Object list(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer engineerId = CommonUtil.stringToInteger(data.get("engineerId"));
			List<VSupervisorReport> datalist = acceptanceService.queryList(engineerId);
			return new ApiResponse<List<VSupervisorReport>>(datalist);
		} catch (Exception e) {
			logger.error("查询监理报告出现异常", e);
			return new ApiResponse<String>(false, "查询监理报告出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("acceptance_info.do")
	public Object acceptance(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer engineerId = CommonUtil.stringToInteger(data.get("engineerId"));
			Integer instanceId = CommonUtil.stringToInteger(data.get("instanceId"));
			return new ApiResponse<VAcceptionMainInfo>(acceptanceService.findAcceptanceInfo(engineerId, instanceId));
		} catch (Exception e) {
			logger.error("查询验收信息出现异常", e);
			return new ApiResponse<String>(false, "查询验收信息出现异常");
		}
	}

	
	@ResponseBody
	@RequestMapping("acceptance_main.do")
	public Object acceptanceMain(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer engineerId = CommonUtil.stringToInteger(data.get("engineerId"));
			Integer stageId = CommonUtil.stringToInteger(data.get("stageId"));
			return new ApiResponse<VAcceptionMainInfo>(acceptanceService.findAcceptance(engineerId,stageId));
		} catch (Exception e) {
			logger.error("查询验收信息出现异常", e);
			return new ApiResponse<String>(false, "查询验收信息出现异常");
		}
	}
	
	@ResponseBody
	@RequestMapping("acceptance_detail.do")
	public Object acceptanceList(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer instanceId = CommonUtil.stringToInteger(data.get("instanceId"));
			return new ApiResponse<List<VAcceptanceDetail>>(acceptanceService.findAcceptanceDetail(instanceId));
		} catch (Exception e) {
			logger.error("查询验收信息出现异常", e);
			return new ApiResponse<String>(false, "查询验收信息出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("acceptance_init.do")
	public Object acceptanceInit(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer stageId = CommonUtil.stringToInteger(data.get("stageId"));
			return new ApiResponse<List<VAcceptanceDetail>>(acceptanceService.findInitialList(stageId));
		} catch (Exception e) {
			logger.error("查询验收项目出现异常", e);
			return new ApiResponse<String>(false, "查询验收项目出现异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "acceptance_save.do", method = RequestMethod.POST)
	public Object acceptanceSave(HttpServletRequest request, @RequestParam String json) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			JSONArray data = jsonObject.getJSONArray("data");
			Integer projectId = jsonObject.getInt("projectId");
			Integer supervisorId = jsonObject.getInt("supervisorId");
			Integer stageId = jsonObject.getInt("stageId");
			Date accepanceDate = DateUtil.parse(jsonObject.getString("accepanceDate"));

			Map<String, Attachment> attachment = attachmentService.uploadMapMulti((MultipartRequest) request);
			acceptanceService.saveAcceptance(projectId, accepanceDate, supervisorId, stageId, data, attachment);
			return new ApiResponse<String>(true);
		} catch (Exception e) {
			logger.error("保存验收项目出现异常", e);
			return new ApiResponse<String>(false, "保存验收项目出现异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "acceptance_update.do", method = RequestMethod.POST)
	public Object acceptanceUpdate(HttpServletRequest request, @RequestParam String json) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			Date accepanceDate = DateUtil.parse(jsonObject.getString("accepanceDate"));
			Integer instanceId = jsonObject.getInt("instanceId");
			List<VAcceptanceDetail> detailList = JSONArray.toList(jsonObject.getJSONArray("memo"),
					VAcceptanceDetail.class);
			List<VAcceptanceItem> mxList = JSONArray.toList(jsonObject.getJSONArray("detail"), VAcceptanceItem.class);
			Map<String, Attachment> attachment = attachmentService.uploadMapMulti((MultipartRequest) request);
			acceptanceService.updateAcceptance(instanceId, accepanceDate, detailList, mxList, attachment);
			return new ApiResponse<String>(true);
		} catch (Exception e) {
			logger.error("更新验收项目出现异常", e);
			return new ApiResponse<String>(false, "更新验收项目出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("acceptance_standard.do")
	public Object acceptanceStandard(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer mxId = CommonUtil.stringToInteger(data.get("mxId"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", acceptanceService.findStageStandard(mxId));
			return new ApiResponse<Map<String, Object>>(map);
		} catch (Exception e) {
			logger.error("查询验收项目出现异常", e);
			return new ApiResponse<String>(false, "查询验收项目出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("acceptance_opinion.do")
	public Object acceptanceOpinion(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer mxInstId = CommonUtil.stringToInteger(data.get("mxInstId"));
			return new ApiResponse<List<VAcceptanceComment>>(acceptanceService.findAcceptOpinion(mxInstId));
		} catch (Exception e) {
			logger.error("查询监理意见出现异常", e);
			return new ApiResponse<String>(false, "查询监理意见出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("owner_opinion_list.do")
	public Object ownerOpinion(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer instId = CommonUtil.stringToInteger(data.get("instId"));
			return new ApiResponse<List<VAcceptanceComment>>(acceptanceService.findOwnerOpinion(instId));
		} catch (Exception e) {
			logger.error("查询业主意见出现异常", e);
			return new ApiResponse<String>(false, "查询业主意见出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("owner_opinion_save.do")
	public Object saveOwnerOpinion(HttpServletRequest request, @RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer instanceId = CommonUtil.stringToInteger(data.get("instanceId"));
			Integer memberId = CommonUtil.stringToInteger(data.get("memberId"));
			String content = data.get("content");
			List<Attachment> attachmentList = attachmentService.uploadMulti((MultipartRequest) request);
			acceptanceService.saveOwnerOpinion(instanceId, memberId, content, attachmentList);
			acceptanceService.updateReportPass(instanceId, false, false);
			return new ApiResponse<String>(true);
		} catch (Exception e) {
			logger.error("保存业主意见出现异常", e);
			return new ApiResponse<String>(false, "保存业主意见出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("acceptance_pass.do")
	public Object acceptancePass(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer instanceId = CommonUtil.stringToInteger(data.get("instanceId"));
			//return new ApiResponse<Map<String, Object>>(acceptanceService.updateReportPass(instanceId, true, true));
			Map<String, Object> map=acceptanceService.updateReportBeforecheck(instanceId, true, true);
			return new ApiResponse(map.get("mess").toString(),map.get("result").toString()) ;
		} catch (Exception e) {
			logger.error("保存验收通过出现异常", e);
			return new ApiResponse<String>(false, "保存验收通过出现异常");
		}
	}
}
