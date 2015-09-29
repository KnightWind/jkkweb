package com.jkkp.modules.basedata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.modules.mapper.VAcceptanceDetail;
import com.jkkp.appapi.modules.mapper.VAcceptanceItem;
import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.EngineeringStageInstMapper;
import com.jkkp.modules.basedata.model.EngineeringStageInst;
import com.jkkp.modules.basedata.model.EngineeringStageInstSort;
import com.jkkp.modules.basedata.model.EngineeringStageMxInst;
import com.jkkp.modules.basedata.service.IEngineeringStageInstService;
import com.jkkp.modules.basedata.service.IEngineeringStageInstSortService;
import com.jkkp.modules.basedata.service.IEngineeringStageMxInstService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/engineeringStageInst")
public class EngineeringStageInstController extends BaseController {

	@Autowired
	private EngineeringStageInstMapper engineeringStageInstMapper;
	@Autowired
	private IEngineeringStageInstService engineeringStageInstService;
	@Autowired
	private IEngineeringStageInstSortService engineeringStageInstSortService;
	@Autowired
	private IEngineeringStageMxInstService engineeringStageMxInstService;

	// 监理报告
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (request.getParameter("reportPass") != null) {
			int reportPass = CommonUtil.stringToInteger(request
					.getParameter("reportPass"));
			params.put("roportPass", reportPass);
			request.setAttribute("reportPass", reportPass);
		}
		Pagination.setPageParams(request, engineeringStageInstMapper,
				"selectAllEngineeringStageInsts",
				"selectAllEngineeringStageInstCount");
		request.setAttribute("pagination",
				engineeringStageInstService.paginationCustom(params));
		return "/basedata/engineeringStageInst_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageInstMapper,
				"selectAllEngineeringStageInsts",
				"selectAllEngineeringStageInstCount");
		return new ResponsePagination(
				engineeringStageInstService.paginationCustom());
	}

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			EngineeringStageInst engineeringStageInst = engineeringStageInstService.findById(id);
			request.setAttribute("engineeringStageInst", engineeringStageInst);
			List<VAcceptanceDetail> viewList = new ArrayList<VAcceptanceDetail>();
			List<EngineeringStageInstSort> rootList = engineeringStageInstSortService
					.findParentSort(id);
			for (EngineeringStageInstSort root : rootList) {
				VAcceptanceDetail view = new VAcceptanceDetail();
				view.setId(root.getId());
				view.setTitle(root.getStageName());
				view.setContent(root.getRemarks());
				view.setChildren(new ArrayList<VAcceptanceDetail>());
				List<EngineeringStageInstSort> itemList = engineeringStageInstSortService
						.findByParentId(root.getId());
				for (EngineeringStageInstSort sort : itemList) {
					VAcceptanceDetail detail = new VAcceptanceDetail();
					view.getChildren().add(detail);
					detail.setId(sort.getId());
					detail.setTitle(sort.getStageName());
					detail.setItems(new ArrayList<VAcceptanceItem>());
					List<EngineeringStageMxInst> instList = engineeringStageMxInstService
							.findBySortId(sort.getId());
					for (EngineeringStageMxInst inst : instList) {
						VAcceptanceItem item = new VAcceptanceItem();
						item.setId(inst.getId());
						item.setName(inst.getStageName());
						item.setMxId(inst.getMxId());
						item.setStatus(inst.getPassFlag());
						item.setStatusName(inst.getPassFlagVal());
						detail.getItems().add(item);
					}
				}
				viewList.add(view);
			}
			request.setAttribute("stageInstResult", viewList);
		}
		return "/basedata/engineeringStageInst_detail";
	}
}
