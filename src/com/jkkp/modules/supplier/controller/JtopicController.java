package com.jkkp.modules.supplier.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.supplier.mapper.JtopicMapper;
import com.jkkp.modules.supplier.mapper.JtopicReplyMapper;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.IJtopicReplyService;
import com.jkkp.modules.supplier.service.IJtopicService;
import com.jkkp.modules.supplier.view.VJtopic;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/jtopic")
public class JtopicController extends BaseController {

	@Autowired
	private IJtopicService jtopicService;
	@Autowired
	private JtopicMapper jtopicMapper;
	@Autowired
	private IJtopicReplyService jtopicReplyService;
	@Autowired
	private JtopicReplyMapper jtopicReplyMapper;
	@Autowired
	private IAreaDomainService areaDomainService;

	// 商家日记@20150703黄宇健 web后台
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/businessTopic")
	public String businessTopic(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jtopicMapper,
				"selectAllBusinessTopic", "selectBusinessTopicCount");
		request.setAttribute("pagination", jtopicService.paginationCustom());
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("lst", areaDomainService.finAll());
		return "/jtopic/businessTopic_list";
	}

	// 商家日记@20150703黄宇健 商家后台
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/businessManageTopic")
	public String businessManageTopic(HttpServletRequest request) {
		SupplierUser su = (SupplierUser) request.getSession()
				.getAttribute("su");
		if (su != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("supplierId", su.getSpId());
			Pagination.setPageParams(request, jtopicMapper,
					"selectAllBusinessTopic", "selectBusinessTopicCount");
			request.setAttribute("pagination",
					jtopicService.paginationCustom(params));
			request.setAttribute("supplierId", su.getId());
		}
		return "/jtopic/busManageTopic_list";
	}

	@ResponseBody
	@RequestMapping(value = "/BTPagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, jtopicMapper,
				"selectAllBusinessTopic", "selectBusinessTopicCount");
		return new ResponsePagination(jtopicService.paginationCustom());
	}

	// 加载日记信息，与日记评论信息
	// id:日记id
	// cid:查看关于（日记评论cid==id或者子评论cid!=id）信息
	// storey:1:标识指向拿取日记评论 2:页面传参，标识指向子评论
	@AccessMenu
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		request.setAttribute("topic", jtopicService.businessDetail(id));
		int storey = request.getParameter("storey") != null ? CommonUtil
				.stringToInteger(request.getParameter("storey")) : 1;
		// 日记评论
		int cid = request.getParameter("cid") != null ? CommonUtil
				.stringToInteger(request.getParameter("cid")) : id;
		if (cid == id && storey == 1) {
			request.setAttribute("topicComment",
					jtopicReplyService.selectAllParentComment(cid));
		} else {
			request.setAttribute("topicComment",
					jtopicReplyService.selectAllChildComment(cid));
		}
		request.setAttribute("storey", storey);
		return "/jtopic/businessTopic_detail";
	}

	@ResponseBody
	@RequestMapping(value = "/pass.do")
	public Object pass(HttpServletRequest request) {
		try {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			jtopicService.pass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("商家日记审核出错");
			return new ResponseObject(false, "审核失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/noPass.do")
	public Object noPass(HttpServletRequest request) {
		try {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			jtopicService.noPass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("商家日记审核出错");
			return new ResponseObject(false, "审核失败");
		}
	}

	// 日记评论审核不通过
	@ResponseBody
	@RequestMapping(value = "/commentNoPass.do")
	public Object commentNoPass(HttpServletRequest requeset, int id) {
		try {
			jtopicReplyService.noPass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("审核商家日记评论出错", e);
			return new ResponseObject(false, "审核失败");
		} finally {

		}
	}

	// 日记评论审核通过
	@ResponseBody
	@RequestMapping(value = "/commentPass.do")
	public Object commentPass(HttpServletRequest requeset, int id) {
		try {
			jtopicReplyService.pass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("审核商家日记评论出错", e);
			return new ResponseObject(false, "审核失败");
		} finally {

		}
	}

	/**
	 * 异步审核操作
	 */
	@ResponseBody
	@RequestMapping("/operationApproval.do")
	public Object operationApproval(@RequestParam(value = "flag") boolean flag,
			@RequestParam(value = "id") Integer id) {
		try {
			Jtopic jtopic = jtopicService.findById(id);
			jtopic.setCheckTime(new Date());
			if (flag) {
				jtopic.setStatus(2);
			} else {
				jtopic.setStatus(-1);
			}
			jtopicService.update(jtopic);
			return new ResponseObject(true, "操作成功 !");
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject(false, "操作失败 !");
		}

	}

	@RequestMapping("/examination")
	public String examination(@RequestParam(value = "flag") boolean flag,
			@RequestParam(value = "id") Integer id) {
		Jtopic jtopic = jtopicService.findById(id);
		jtopic.setCheckTime(new Date());
		if (flag) {
			jtopic.setStatus(2);
		} else {
			jtopic.setStatus(-1);
		}
		jtopicService.update(jtopic);
		return "redirect:/jtopic/index.xhtml";
	}

	/**
	 * 查看日记
	 * 
	 * @param id
	 *            日记Id
	 * @return 监理日记
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/view")
	public String view(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			VJtopic jtopic = jtopicService.findVJtopicById(id);
			request.setAttribute("jtopic", jtopic);
		}

		int storey = request.getParameter("storey") != null ? CommonUtil
				.stringToInteger(request.getParameter("storey")) : 1;
		// 日记评论
		int cid = request.getParameter("cid") != null ? CommonUtil
				.stringToInteger(request.getParameter("cid")) : id;
		if (cid == id && storey == 1) {
			request.setAttribute("topicComment",
					jtopicReplyService.selectAllParentComment(cid));
		} else {
			request.setAttribute("topicComment",
					jtopicReplyService.selectAllChildComment(cid));
		}
		request.setAttribute("storey", storey);

		return "/jtopic/jtopic_view";
	}

	/**
	 * 监理日记列表 web后台
	 * 
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jtopicMapper, "findPageHandle",
				"countPageHandle");
		request.setAttribute("pagination", jtopicService.paginationCustom());
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		return "/jtopic/jtopic_list";
	}

	/**
	 * 上下分页 web后台
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object fenye(HttpServletRequest request) {
		Pagination.setPageParams(request, jtopicMapper, "findPageHandle",
				"countPageHandle");
		return new ResponsePagination(jtopicService.paginationCustom());
	}

	/**
	 * 监理日记列表 监理后台
	 * 
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/jlTopic")
	public String jlTopic(HttpServletRequest request) {
		SupplierUser su = (SupplierUser) request.getSession()
				.getAttribute("su");
		if (su != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("jlid", su.getId());
			request.setAttribute("jlid", su.getSpId());
			Pagination.setPageParams(request, jtopicMapper, "findPageHandle",
					"countPageHandle");
			request.setAttribute("pagination",
					jtopicService.paginationCustom(params));
		}
		return "/jtopic/jtopicManage_list";
	}

	/**
	 * 上下分页 监理后台
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/jlTopicPagination.do")
	public Object jlTopicPagination(HttpServletRequest request) {
		Pagination.setPageParams(request, jtopicMapper, "findPageHandle",
				"countPageHandle");
		return new ResponsePagination(jtopicService.paginationCustom());
	}

	/**
	 * 设置导航菜单参数
	 */
	public void setRequestAttribute(HttpServletRequest request) {
		request.setAttribute("mid",
				CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",
				CommonUtil.stringToInteger(request.getParameter("pid")));
	}

	/**
	 * 评论上下分页
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/paginationReply.do")
	public Object pagination(HttpServletRequest request,
			@RequestParam(value = "id") Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jtopicReplyMapper, "findPage",
				"countPage");
		return new ResponsePagination(
				jtopicReplyService.paginationCustom(params));
	}

	/**
	 * 删除一日记
	 * 
	 * @param requeset
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOneTopic.do")
	public Object deleteOneTopic(HttpServletRequest request) {
		try {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			jtopicService.deleteOneTopic(id);
			return new ResponseObject(true, "删除成功");
		} catch (Exception e) {
			logger.error("日记删除失败");
			return new ResponseObject(false, "删除失败");
		}
	}

	/**
	 * 获取一个实体对象
	 * 
	 * @param id
	 * @param map
	 */
	// @ModelAttribute
	// public void getJtopic(@RequestParam(value="id",required=false) Integer
	// id, Map<String, Object> map){
	// Jtopic jtopic = jtopicService.findById(id);
	// map.put("jtopic", jtopic);
	// }
	//
}
