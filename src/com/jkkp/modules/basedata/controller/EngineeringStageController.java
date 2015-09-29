package com.jkkp.modules.basedata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.EngineeringStageMapper;
import com.jkkp.modules.basedata.model.EngineeringStage;
import com.jkkp.modules.basedata.service.IEngineeringStageService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/engineeringStage")
public class EngineeringStageController extends BaseController {
	@Autowired
	private EngineeringStageMapper engineeringStageMapper;

	@Autowired
	private IEngineeringStageService engineeringStageService;

	// 删除节点
	@ResponseBody
	@RequestMapping(value = "/deleteOne.do")
	public Object deleteOne(Integer id) {
		try {
			if (id != null) {
				engineeringStageService.deleteOneStage(id);
				return new ResponseObject(true, "删除成功");
			}
			return new ResponseObject(false, "删除节点失败");
		} catch (Exception e) {
			logger.error("删除验收节点失败");
			return new ResponseObject(false, "删除节点失败");
		}
	}

	// 查找子验收节点
	@ResponseBody
	@RequestMapping(value = "/stageChile.do")
	public List<EngineeringStage> stageChile(HttpServletRequest request, int pid) {
		return engineeringStageService.selectByProperty("pid", pid);
	}

	// 创建节点
	@ResponseBody
	@RequestMapping(value = "/saveOne.do")
	public Object saveOne(HttpServletRequest request,EngineeringStage stage) {
		try {
			if (stage.getId() == null && stage.getPid() == null) {
				return new ResponseObject(false, "请选择父节点");
			}
			if (stage.getStagName().isEmpty()) {
				return new ResponseObject(false, "请输入节点名称");
			}
			engineeringStageService.saveOrUpdate(stage);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
			logger.error("保存验收节点失败");
			return new ResponseObject(false, "保存节点失败");
		}

	}

	// 创建节点--针对一级节点
	@ResponseBody
	@RequestMapping(value = "/saveOneHandle.do")
	public Object saveOneHandle(HttpServletRequest request,
			EngineeringStage stage) {
		try {
			if (stage.getAbbreviation().isEmpty()) {
				return new ResponseObject(false, "请输入节点简称");
			}
			if (stage.getStagName().isEmpty()) {
				return new ResponseObject(false, "请输入节点名称");
			}
			stage.setPid(0);
			engineeringStageService.saveOrUpdate(stage);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
			logger.error("保存验收节点失败");
			return new ResponseObject(false, "保存节点失败");
		}

	}

	// 123验收节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMapper,
				"selectAllEngineeringStage", "selectAllEngineeringStageCount");
		request.setAttribute("pagination",
				engineeringStageService.paginationCustom());
		return "/basedata/engineeringStage_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMapper,
				"selectAllEngineeringStage", "selectAllEngineeringStageCount");
		return new ResponsePagination(
				engineeringStageService.paginationCustom());
	}

	// 添加三级节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/stageThreeAdd")
	public String stageThreeAdd(HttpServletRequest request) {
		request.setAttribute("oneStage",
				engineeringStageService.selectAllParentStage());
		return "/basedata/engineeringStage_threeAdd";
	}

	// 添加二级节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/stageTwoAdd")
	public String stageTwoAdd(HttpServletRequest request) {
		request.setAttribute("oneStage",
				engineeringStageService.selectAllParentStage());
		return "/basedata/engineeringStage_twoAdd";
	}

	// 添加一级节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/stageOneAdd")
	public String stageOneAdd() {
		return "/basedata/engineeringStage_OneAdd";
	}

	// 12验收节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/oneTwoStage")
	public String oneTwo(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMapper,
				"selectAllOTEngineeringStage",
				"selectAllOTEngineeringStageCount");
		request.setAttribute("pagination",
				engineeringStageService.paginationCustom());
		return "/basedata/engineeringStageOneTwo_list";
	}

	@ResponseBody
	@RequestMapping(value = "/oneTwoPagination.do")
	public Object oneTwoPagination(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMapper,
				"selectAllOTEngineeringStage",
				"selectAllOTEngineeringStageCount");
		return new ResponsePagination(
				engineeringStageService.paginationCustom());
	}

	// 1验收节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/oneStage")
	public String oneStage(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMapper,
				"selectAllParentStages", "selectAllParentStagesCount");
		request.setAttribute("pagination",
				engineeringStageService.paginationCustom());
		return "/basedata/engineeringStageOne_list";
	}

	@ResponseBody
	@RequestMapping(value = "/onePagination.do")
	public Object onePagination(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMapper,
				"selectAllParentStages", "selectAllParentStagesCount");
		return new ResponsePagination(
				engineeringStageService.paginationCustom());
	}

	// // 保存子节点
	// @ResponseBody
	// @RequestMapping(value = "/saveChildren.do")
	// public Object saveChildren(EngineeringStage eng) {
	// try {
	// if (eng.getPid() == null) {
	// return new ResponseObject(false, "请选择验收节点");
	// }
	// if (eng.getStagName() == "" || eng.getStagName() == null) {
	// return new ResponseObject(false, "请输入验收项目");
	// }
	// engineeringStageService.saveOrUpdate(eng);
	// return new ResponseObject(true, "操作成功！");
	// } catch (Exception e) {
	// logger.error("保存节点出错", e);
	// return new ResponseObject(false, "操作失败！");
	// } finally {
	//
	// }
	// }
	//
	// // 保存父节点
	// @ResponseBody
	// @RequestMapping(value = "/saveParent.do")
	// public Object saveParent(EngineeringStage eng) {
	// try {
	// if (eng.getStagName() == "" || eng.getStagName() == null) {
	// return new ResponseObject(false, "请输入验收节点");
	// }
	// // 设置节点pid为0
	// eng.setPid(0);
	// engineeringStageService.saveOrUpdate(eng);
	// return new ResponseObject(true, "操作成功！");
	// } catch (Exception e) {
	// logger.error("保存节点出错", e);
	// return new ResponseObject(false, "操作失败！");
	// } finally {
	//
	// }
	// }
	//
	// // 查看验收节点的详细信息
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping(value = "/detail")
	// public String detail(HttpServletRequest request, Integer id) {
	// if (id > 0) {
	// request.setAttribute("eng", engineeringStageService.findById(id));
	// EngineeringStageMx engMX = new EngineeringStageMx();
	// engMX.setPid(id);
	// request.setAttribute("engMXList",
	// engineeringStageMxService.select(engMX));
	// return "/basedata/engineeringStage_detail";
	// }
	// return "";
	// }
	//
	// // 进入新增验收项目页面
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping(value = "/add")
	// public String add(HttpServletRequest request) {
	// EngineeringStage eng = new EngineeringStage();
	// eng.setPid(0);
	// request.setAttribute("pEng", engineeringStageService.select(eng));
	// return "/basedata/engineeringStage_add";
	// }
	//
	// // 进入管理验收节点页面
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping(value = "/manage")
	// public String manage(HttpServletRequest request) {
	// Pagination.setPageParams(request, engineeringStageMapper,
	// "selectAllPareatEng", "selectPareatEngCount");
	// request.setAttribute("pagination",
	// engineeringStageService.paginationCustom());
	// return "/basedata/engineeringStage_manage";
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/managePagin.do")
	// public Object managePagin(HttpServletRequest request) {
	// Pagination.setPageParams(request, engineeringStageMapper,
	// "selectAllPareatEng", "selectPareatEngCount");
	// return new ResponsePagination(
	// engineeringStageService.paginationCustom());
	// }
	//
	// // 节点隐藏
	// @ResponseBody
	// @RequestMapping(value = "/hide.do")
	// public Object hide(int id) {
	// try {
	// engineeringStageService.hide(id);
	// return new ResponseObject(true, "隐藏成功！");
	// } catch (Exception e) {
	// logger.error("隐藏节点出错", e);
	// return new ResponseObject(false, "隐藏失败！");
	// } finally {
	//
	// }
	// }
	//
	// // 节点显示
	// @ResponseBody
	// @RequestMapping(value = "/show.do")
	// public Object show(int id) {
	// try {
	// engineeringStageService.show(id);
	// return new ResponseObject(true, "隐藏成功！");
	// } catch (Exception e) {
	// logger.error("隐藏节点出错", e);
	// return new ResponseObject(false, "隐藏失败！");
	// } finally {
	//
	// }
	// }

}
