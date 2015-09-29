package com.jkkp.appapi.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jkkp.appapi.common.service.interfaces.IAcceptanceService;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.modules.mapper.VAcceptanceComment;
import com.jkkp.appapi.modules.mapper.VAcceptanceDetail;
import com.jkkp.appapi.modules.mapper.VAcceptanceItem;
import com.jkkp.appapi.modules.mapper.VAcceptionMainInfo;
import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.appapi.modules.mapper.VSupervisorReport;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.CheckRequest;
import com.jkkp.modules.basedata.model.EngineeringStage;
import com.jkkp.modules.basedata.model.EngineeringStageInst;
import com.jkkp.modules.basedata.model.EngineeringStageInstSort;
import com.jkkp.modules.basedata.model.EngineeringStageMx;
import com.jkkp.modules.basedata.model.EngineeringStageMxInst;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.ICheckRequestService;
import com.jkkp.modules.basedata.service.IEngineeringStageInstService;
import com.jkkp.modules.basedata.service.IEngineeringStageInstSortService;
import com.jkkp.modules.basedata.service.IEngineeringStageMxInstService;
import com.jkkp.modules.basedata.service.IEngineeringStageMxService;
import com.jkkp.modules.basedata.service.IEngineeringStageService;
import com.jkkp.modules.basedata.service.IEngineeringsService;
import com.jkkp.modules.design.service.IAdBannerService;
import com.jkkp.modules.design.service.impl.AdBannerServiceImpl;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.MemberComment;
import com.jkkp.modules.member.service.IMemberCommentService;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.model.JlComment;
import com.jkkp.modules.supplier.service.IJlCommentService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.DateUtil;

@Service("acceptanceService")
public class AcceptanceServiceImpl implements IAcceptanceService {

	@Autowired
	private IEngineeringStageService engineeringStageService;
	@Autowired
	private IEngineeringStageMxService engineeringStageMxService;
	@Autowired
	private IEngineeringsService engineeringsService;
	@Autowired
	private IEngineeringStageInstService engineeringStageInstService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IEngineeringStageInstSortService engineeringStageInstSortService;
	@Autowired
	private IEngineeringStageMxInstService engineeringStageMxInstService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IJlCommentService jlCommentService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberCommentService memberCommentService;
	@Autowired
	private ICheckRequestService iCheckRequestService;
	@Autowired
	IBaseinf ibaseinfsv;
	@Autowired
	private IAdBannerService iAdBannerService;

	@Override
	public List<VSupervisorReport> queryList(Integer engineerId) {
		List<VSupervisorReport> viewList = new ArrayList<VSupervisorReport>();
		List<EngineeringStage> stageList = engineeringStageService
				.findStageList();
		for (EngineeringStage engineeringStage : stageList) {
			EngineeringStageInst inst = engineeringStageInstService
					.findInstance(engineerId, engineeringStage.getId());
			viewList.add(this.convertToView(engineeringStage, inst, engineerId));
		}
		return viewList;
	}

	private VSupervisorReport convertToView(EngineeringStage engineeringStage,
			EngineeringStageInst inst, int engineerId) {
		VSupervisorReport view = new VSupervisorReport();
		if (inst != null) {
			view.setInstanceId(inst.getId());
			if (inst.getFinishFlag() != null) {
				view.setStatus(inst.getFinishFlag() == 1 ? "合格" : "整改");
			}
			if (inst.getReportPass() != null) {
				view.setReportStatus(inst.getReportPass() == 1 ? "合格" : "整改");
			}
		}
		List<CheckRequest> checkRequestList = iCheckRequestService
				.selectByProperty(new String[] { "gcdId", "stageId" },
						new Object[] { engineerId, engineeringStage.id });
		view.setCheckRequestCount(checkRequestList.size());
		view.setStageId(engineeringStage.getId());
		view.setName(engineeringStage.getStagName());
		return view;
	}

	public VAcceptionMainInfo findAcceptanceInfo(Integer engineerId) {
		VAcceptionMainInfo view = new VAcceptionMainInfo();
		Engineerings engineerings = engineeringsService.findById(engineerId);
		view.setAddress(engineerings.getCommunity());
		view.setPointX(engineerings.getPointx());
		view.setPointY(engineerings.getPointy());
		Member member = memberService.findById(engineerings.getUid());
		view.setCustomerName(member.getNickname());
		return view;
	}
	
	public void saveTimeCheckRequestTime(Date accepanceDate,int engineerId, int stageid){
		List<CheckRequest> checkRequestList = iCheckRequestService
				.selectByProperty(new String[] { "gcdId", "stageId" },
						new Object[] { engineerId, stageid });
		Date checkdate = null;
		int id=-1;;
		if (checkRequestList.size() > 0) {
			// 拿出最新的验收请求，因为插件产生的sql有问题
			for (CheckRequest checkRequest : checkRequestList) {
				if (checkRequest.getCreateTime() != null) {
					if (checkdate == null){
						checkdate = checkRequest.getCreateTime();
						id=checkRequest.getId();
					}
					if (checkRequest.getCreateTime().after(checkdate)){
						checkdate = checkRequest.getCreateTime();
						id=checkRequest.getId();
					}
				}
			}
		}else {//没有对应的验收请求就新建一条
			CheckRequest checkRequest=new CheckRequest();
			checkRequest.setCheckTime(accepanceDate);
			checkRequest.setCreateTime(new Date());
			checkRequest.setGcdId(engineerId);
			checkRequest.setStageId(stageid);
		}
		if(id!=-1){
			CheckRequest checkRequest=iCheckRequestService.findById(id);
			if(checkRequest.getCheckTime()==null){
				checkRequest.setCheckTime(accepanceDate);
				iCheckRequestService.update(checkRequest);
			}
		}
	}
	
	public Date getCheckRequestTime(int engineerId, int stageid) {
		List<CheckRequest> checkRequestList = iCheckRequestService
				.selectByProperty(new String[] { "gcdId", "stageId" },
						new Object[] { engineerId, stageid });
		// List<CheckRequest>
		// checkRequestList=iCheckRequestService.selectByProperty(new
		// String[]{"gcdId",}, new Object[]{engineerId},"checkTime",false);
		Date checkdate = null;
		if (checkRequestList.size() > 0) {
			// 拿出最新的时间，因为插件产生的sql有问题

			for (CheckRequest checkRequest : checkRequestList) {
				if (checkRequest.getCheckTime() != null) {
					if (checkdate == null)
						checkdate = checkRequest.getCheckTime();
					if (checkRequest.getCheckTime().after(checkdate))
						checkdate = checkRequest.getCheckTime();
				}
			}
		}
		/*if (checkdate == null) {
			//验收请求的时间为空，就从验收实例的时间获取。
			List<EngineeringStage> engineeringStageInstList;
			engineeringStageInstList = engineeringStageService
					.selectByProperty(new String[] { "gcdId", "pid" },
							new Object[] { engineerId, stageid });
			if (engineeringStageInstList.size() > 0) {
				checkdate = engineeringStageInstList.get(0).getCreateTime();
			}
		}*/
		return checkdate;
	}

	@Override
	public VAcceptionMainInfo findAcceptanceInfo(Integer engineerId,
			Integer instanceId) {
		VAcceptionMainInfo view = new VAcceptionMainInfo();
		Engineerings engineerings = engineeringsService.findById(engineerId);
		if (instanceId == null || instanceId == 0) {
			// view.setAcceptanceDate(new Date());
			// 没有实例就从工程单查询监理
			view.setSupervisorId(engineerings.getJlId());
			view.setSupervisorName(supplierService
					.findSupplierName(engineerings.getJlId()));
		} else {
			EngineeringStageInst inst = engineeringStageInstService
					.findById(instanceId);
			view.setSupervisorId(inst.getJlId());
			view.setSupervisorName(supplierService.findSupplierName(inst
					.getJlId()));
			// List<CheckRequest>
			// checkRequestList=iCheckRequestService.selectByProperty(new
			// String[]{"gcdId",}, new Object[]{engineerId},"checkTime",false);
			view.setAcceptanceDate(getCheckRequestTime(engineerId,
					inst.getPid()));
		}
		view.setAddress(engineerings.getCommunity());
		view.setPointX(engineerings.getPointx());
		view.setPointY(engineerings.getPointy());
		Member member = memberService.findById(engineerings.getUid());
		view.setCustomerName(member.getNickname());
		return view;
	}

	@Override
	public List<VAcceptanceDetail> findInitialList(Integer stageId) {
		List<VAcceptanceDetail> viewList = new ArrayList<VAcceptanceDetail>();
		List<EngineeringStage> rootList = engineeringStageService
				.findByParentId(stageId);
		for (EngineeringStage stage : rootList) {
			VAcceptanceDetail view = new VAcceptanceDetail();
			view.setId(stage.getId());
			view.setTitle(stage.getStagName());
			view.setChildren(new ArrayList<VAcceptanceDetail>());
			List<EngineeringStage> itemList = engineeringStageService
					.findByParentId(stage.getId());
			for (EngineeringStage childStage : itemList) {
				VAcceptanceDetail detail = new VAcceptanceDetail();
				view.getChildren().add(detail);
				detail.setId(childStage.getId());
				detail.setTitle(childStage.getStagName());
				detail.setItems(new ArrayList<VAcceptanceItem>());
				List<EngineeringStageMx> mxList = engineeringStageMxService
						.findByStageId(childStage.getId());
				for (EngineeringStageMx mx : mxList) {
					VAcceptanceItem item = new VAcceptanceItem();
					item.setMxId(mx.getId());
					item.setName(mx.getMxDesc());
					detail.getItems().add(item);
				}
			}
			viewList.add(view);
		}
		return viewList;
	}

	@Override
	public List<VAcceptanceDetail> findAcceptanceDetail(Integer instanceId) {
		List<VAcceptanceDetail> viewList = new ArrayList<VAcceptanceDetail>();
		List<EngineeringStageInstSort> rootList = engineeringStageInstSortService
				.findParentSort(instanceId);
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
		return viewList;
	}

	@Override
	@Transactional
	public void saveAcceptance(Integer projectId, Date accepanceDate,
			Integer supervisorId, Integer stageId, JSONArray data,
			Map<String, Attachment> attachment) {
		EngineeringStage stage = engineeringStageService.findById(stageId);
		EngineeringStageInst inst = engineeringStageInstService.saveAcceptance(
				projectId, accepanceDate, supervisorId, stageId,
				stage.getStagName());
		//更新验收报告，假如最新验收请求时间为空，就保存提交的时间。
		saveTimeCheckRequestTime(accepanceDate,projectId,stageId);
		boolean isPass = true;
		int index = 1;
		for (Iterator it = data.iterator(); it.hasNext();) {
			JSONObject object = (JSONObject) it.next();
			EngineeringStageInstSort root = new EngineeringStageInstSort();
			root.setInstId(inst.getId());
			root.setStageName(object.getString("name"));
			root.setPid(0);
			root.setRemarks(object.getString("content"));
			root.setOrderBy(index++);
			engineeringStageInstSortService.save(root);

			int index2 = 1;
			JSONArray children = object.getJSONArray("children");
			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				JSONObject item = (JSONObject) iterator.next();
				EngineeringStageInstSort detail = new EngineeringStageInstSort();
				detail.setInstId(inst.getId());
				detail.setStageName(item.getString("name"));
				detail.setPid(root.getId());
				detail.setOrderBy(index2++);
				engineeringStageInstSortService.save(detail);

				int index3 = 1;
				JSONArray items = item.getJSONArray("items");
				for (Iterator itemIt = items.iterator(); itemIt.hasNext();) {
					JSONObject mxObject = (JSONObject) itemIt.next();
					EngineeringStageMxInst mxInst = new EngineeringStageMxInst();
					mxInst.setPid(detail.getId());
					mxInst.setPassFlag(mxObject.getInt("status"));
					mxInst.setCreateTime(new Date());
					mxInst.setOrderBy(index3++);
					mxInst.setMxId(mxObject.getInt("mxId"));
					mxInst.setStageName(mxObject.getString("mxName"));
					mxInst.setJlId(supervisorId);
					engineeringStageMxInstService.save(mxInst);
					// 第一次提交意见时候是第1次
					JlComment jlComment = this.saveOpinion(mxInst.getId(),
							mxObject.getString("content"), 1, null);
					this.saveAttachment(mxInst.getMxId(), jlComment.getId(),
							attachment);
					isPass = isPass && mxInst.isPass();
				}
			}
		}
		this.updateStageInst(inst, isPass);
	}

	@Transactional
	private void updateStageInst(EngineeringStageInst inst, boolean isPass) {
		inst.setPass(isPass);
		engineeringStageInstService.update(inst);

		if (!isPass) {
			this.updateReportPass(inst.getId(), isPass, true);
		}
	}

	@Transactional
	private JlComment saveOpinion(Integer mxInstId, String content,
			Integer checkNum, List<Attachment> attachments) {
		int count;

		JlComment jlComment = new JlComment();
		jlComment.setInstId(mxInstId);
		jlComment.setContent(content);
		jlComment.setCreateTime(new Date());

		if (checkNum != null && checkNum > 0) {
			// 第一次上传意见
			count = checkNum;
			jlComment.setCheckNum(count);
		} else {
			// 非第一次上传意见
			List<JlComment> list = jlCommentService.selectByProperty("instId",
					mxInstId);
			count = list.size();
			jlComment.setCheckNum(count + 1);
		}

		jlCommentService.save(jlComment);

		if (attachments != null && !attachments.isEmpty()) {
			for (Attachment attachment : attachments) {
				attachmentService.saveAttachment(attachment, jlComment.getId(),
						AttachmentConstant.JLREPORT_SUPPLIER_ADVICE);
			}
		}
		return jlComment;
	}

	private void saveAttachment(Integer mxId, Integer commentId,
			Map<String, Attachment> attachment) {
		for (String filename : attachment.keySet()) {
			if (filename.startsWith(String.valueOf(mxId))) {
				attachmentService.saveAttachment(attachment.get(filename),
						commentId, AttachmentConstant.JLREPORT_SUPPLIER_ADVICE);
			}
		}
	}

	@Override
	@Transactional
	public void updateAcceptance(Integer instanceId, Date accepanceDate,
			List<VAcceptanceDetail> detailList, List<VAcceptanceItem> mxList,
			Map<String, Attachment> attachment) {
		EngineeringStageInst inst = engineeringStageInstService
				.findById(instanceId);
		inst.setCreateTime(accepanceDate);
		//更新验收报告，假如最新验收请求时间为空，就保存当前时间。
		saveTimeCheckRequestTime(new Date(),inst.getGcdId(),inst.getPid());
		for (VAcceptanceDetail view : detailList) {
			engineeringStageInstSortService.updateContent(view.getId(),
					view.getContent());
		}
		boolean isPass = true;
		for (VAcceptanceItem mxItem : mxList) {
			EngineeringStageMxInst mxInst = engineeringStageMxInstService
					.findById(mxItem.getId());
			mxInst.setPassFlag(mxItem.getStatus());
			engineeringStageMxInstService.update(mxInst);
			List<Attachment> attachmentList = this.findAttachment(
					mxInst.getMxId(), attachment);
			if (StringUtils.isNotEmpty(mxItem.getContent())
					|| !attachmentList.isEmpty()) {
				this.saveOpinion(mxInst.getId(), mxItem.getContent(), null,
						attachmentList);
			}
			isPass = isPass && mxInst.isPass();
		}
		this.updateStageInst(inst, isPass);
		;
	}

	private List<Attachment> findAttachment(Integer mxId,
			Map<String, Attachment> attachment) {
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		for (String filename : attachment.keySet()) {
			if (filename.startsWith(String.valueOf(mxId))) {
				attachmentList.add(attachment.get(filename));
			}
		}
		return attachmentList;
	}

	@Override
	public String findStageStandard(Integer mxId) {
		EngineeringStageMx mxEntity = engineeringStageMxService.findById(mxId);
		return mxEntity.getCheckRule();
	}

	@Override
	public List<VAcceptanceComment> findAcceptOpinion(Integer mxInstId) {
		EngineeringStageMxInst inst = engineeringStageMxInstService
				.findById(mxInstId);
		String supervisor = supplierService.findSupplierName(inst.getJlId());
		List<VAcceptanceComment> opinionList = new ArrayList<VAcceptanceComment>();
		List<JlComment> jlCommentList = jlCommentService
				.findByMxInstId(mxInstId);
		for (JlComment jlComment : jlCommentList) {
			VAcceptanceComment view = new VAcceptanceComment();
			view.setName(supervisor);
			view.setContent(jlComment.getContent());
			view.setCreateDate(jlComment.getCreateTime());
			view.setStatus(jlComment.getResult());
			view.setCheckNum(jlComment.getCheckNum());
			view.setHeadImg(ibaseinfsv.getHeadimg(inst.getJlId(),
					AttachmentConstant.SUPPLIER_COMPANY_TYPE));
			view.setImagePath(attachmentService.findForDownload(
					jlComment.getId(),
					AttachmentConstant.JLREPORT_SUPPLIER_ADVICE));
			opinionList.add(view);
		}
		return opinionList;
	}

	@Override
	public List<VAcceptanceComment> findOwnerOpinion(Integer instId) {
		List<VAcceptanceComment> opinionList = new ArrayList<VAcceptanceComment>();
		List<MemberComment> commentList = memberCommentService
				.findByInstanceId(instId);
		for (MemberComment memberComment : commentList) {
			VAcceptanceComment view = new VAcceptanceComment();
			view.setName(memberComment.getUserName());
			view.setContent(memberComment.getContent());
			view.setCreateDate(memberComment.getCreateTime());
			view.setHeadImg(ibaseinfsv.getHeadimg(memberComment.getUid(),
					AttachmentConstant.MEMBER_TYPE));
			view.setImagePath(attachmentService.findForDownload(
					memberComment.getId(),
					AttachmentConstant.JLREPORT_MEMBER_ADVICE));
			opinionList.add(view);
		}
		return opinionList;
	}

	@Override
	@Transactional
	public void saveOwnerOpinion(Integer instanceId, Integer memberId,
			String content, List<Attachment> attachmentList) {
		MemberComment comment = new MemberComment();
		comment.setUid(memberId);
		Member member = memberService.findById(memberId);
		comment.setUserName(member.getNickname());
		comment.setUserType(1);
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setType(1);
		comment.setPid(instanceId);
		memberCommentService.save(comment);

		for (Attachment attachment : attachmentList) {
			attachmentService.saveAttachment(attachment, member,
					comment.getId(), AttachmentConstant.JLREPORT_MEMBER_ADVICE);
		}
	}

	public Boolean checkAcceptanceDetail(int instanceId) {
		Boolean result = true;
		List<VAcceptanceDetail> acceptanceDetails = this
				.findAcceptanceDetail(instanceId);
		if (acceptanceDetails.size() > 0) {
			for (VAcceptanceDetail vAcceptanceDetail : acceptanceDetails) {
				if (vAcceptanceDetail.getItems() != null) {
					List<VAcceptanceItem> items = vAcceptanceDetail.getItems();
					for (VAcceptanceItem vAcceptanceItem : items) {
						if (vAcceptanceItem.getStatus() == 0) {
							result = false;
						}
					}
				}
			}
		} else {
			result = false;
		}
		return result;
	}

	public Boolean checkBeforeAcceptanceDetail(int engineerId) {
		Boolean result = true;
		List<EngineeringStageInst> engineeringStageInsts = engineeringStageInstService
				.selectByProperty("gcdId", engineerId, "gcdId", true);
		if (engineeringStageInsts.size() > 0) {
			for (EngineeringStageInst engineeringStageInst : engineeringStageInsts) {
				if (engineeringStageInst.getPid() != 50) {// 不判断竣工阶段的业主标志
					if (checkAcceptanceDetail(engineeringStageInst.getId()) == false) {
						result = false;
						return result;
					}
				}
			}
		}
		return result;
	}

	public Boolean checkInstans(int engineerId, int thisZXstage) {
		Boolean result = true;
		List<EngineeringStageInst> engineeringStageInsts = engineeringStageInstService
				.selectByProperty("gcdId", engineerId, "pid", true);// 一定要排序，这样才能按顺序来判断
		if (engineeringStageInsts.size() > 0) {
			for (EngineeringStageInst engineeringStageInst : engineeringStageInsts) {
				if (engineeringStageInst.getPid() != thisZXstage) {// 判断之前的阶段是否通过验收
					if (engineeringStageInst.getReportPass() == null) {
						result = false;
						return result;
					} else if (engineeringStageInst.getReportPass() == 0) {
						result = false;
						return result;
					}
				}
				if (engineeringStageInst.getPid() == thisZXstage) {
					// 到了本阶段就返回结果
					break;
				}
			}
		}
		return result;
	}

	@Override
	public void updateReportPass(Integer instanceId, boolean isPass,
			boolean updateProject) {
		EngineeringStageInst inst = engineeringStageInstService
				.findById(instanceId);
		if (inst != null) {
			inst.setReportPass(isPass ? 1 : 0);
			if (isPass) {
				inst.setPassTime(new Date());
			}
			engineeringStageInstService.update(inst);
		}
	}

	@Override
	public Map<String, Object> updateReportBeforecheck(Integer instanceId,
			boolean isPass, boolean updateProject) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "9998");
		map.put("mess", "没有本阶段验收");
		EngineeringStageInst inst = engineeringStageInstService
				.findById(instanceId);
		if (inst != null) {
			if (checkInstans(inst.getGcdId(), inst.getPid()) == false) {
				map.put("result", "0003");
				map.put("mess", "前面有验收没有同意");
				return map;
			}
			if (inst.getPid() != 50) {// 非竣工阶段
				if (this.checkAcceptanceDetail(instanceId) == true) {
					if (inst.getReportPass() != null) {
						if (inst.getReportPass() == 1) {
							map.put("result", "0004");
							map.put("mess", "已经同意了");
							return map;
						}
					}
					inst.setReportPass(isPass ? 1 : 0);
					if (isPass) {
						inst.setPassTime(new Date());
						map.put("result", "0000");
						map.put("mess", "保存成功");
						engineeringStageInstService.update(inst);
						return map;
					}
					engineeringStageInstService.update(inst);
				} else {
					map.put("result", "0001");
					map.put("mess", "本阶段验收不及格");
					return map;
				}
			}
			if (inst.getPid() == 50) {// 竣工阶段
				// 竣工阶段验收，要判断之前的验收是否合格
				if (checkBeforeAcceptanceDetail(inst.getGcdId()) == false) {
					map.put("result", "0002");
					map.put("mess", "有未经同意的监理报告，无法通过");
					return map;
				}
				if (checkAcceptanceDetail(instanceId) == false) {
					map.put("result", "0001");
					map.put("mess", "本阶段验收不及格");
					return map;
				}
				if (inst.getReportPass() != null) {
					if (inst.getReportPass() == 1) {
						map.put("result", "0004");
						map.put("mess", "已经同意了");
						return map;
					}
				}
				inst.setReportPass(isPass ? 1 : 0);
				if (isPass) {
					inst.setPassTime(new Date());
					map.put("result", "0000");
					map.put("mess", "保存成功");
					engineeringStageInstService.update(inst);
					Engineerings eng = engineeringsService.findById(inst
							.getGcdId());
					eng.setEndTime(new Date());// 竣工时间
					eng.setStatus(0);// 设置工程状态为未结款
					engineeringsService.update(eng);
					return map;
				}
			}
		}
		return map;
	}

	@Override
	public VAcceptionMainInfo findAcceptance(Integer engineerId, Integer stageId) {
		VAcceptionMainInfo view = new VAcceptionMainInfo();
		Engineerings engineerings = engineeringsService.findById(engineerId);
		view.setAcceptanceDate(getCheckRequestTime(engineerId, stageId));
		view.setAddress(engineerings.getCommunity());
		view.setPointX(engineerings.getPointx());
		view.setPointY(engineerings.getPointy());
		Member member = memberService.findById(engineerings.getUid());
		view.setCustomerName(member.getNickname());
		return view;
	}

	@Override
	public VJlGcd querycount(Integer gcdId, Integer stageId) {
		return iAdBannerService.querycount(gcdId, stageId);
	}

}
