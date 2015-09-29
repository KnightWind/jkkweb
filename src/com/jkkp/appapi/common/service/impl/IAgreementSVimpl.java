package com.jkkp.appapi.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IAgreementSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IDesignSV;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VAgreement;
import com.jkkp.appapi.modules.mapper.VAgreementDetail;
import com.jkkp.appapi.modules.mapper.VHeTong;
import com.jkkp.common.IService;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.order.mapper.AgreementMapper;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.system.model.Admin;

@Service("AgreementApiService")
public class  IAgreementSVimpl extends ServiceSupport<Agreement,VAgreement,Integer> implements IAgreementSV{
	@Autowired
	private AgreementMapper OneMapper;
	@Autowired 
	private IAttachmentService attachmentService;
	@Autowired
	IAppointmentPushSV appointmentSV;
	@Autowired
	IDesignService designService;
	@Override
	protected Mapper<Agreement> getMapper() {		
		return OneMapper;
	}
	@Override
	public VHeTong queryhetong(Integer id) {
		return OneMapper.queryhetong(id);
	}
	@Override
	@Transactional
	public Map doTranscAddAgreement(HttpServletRequest request,
			Map<String, Object> map, Map<String, Object> mapBusi,
			List<Object> ret) {
		Map rmap = new HashMap();
		Integer id;
//		Integer gcd_id = null;
//		if ((String) map.get("gcd_id") != null) {
//			gcd_id = Integer.valueOf((String) map.get("gcd_id"));// 现在合同关联主键用
//		}
		String agreementId = (String)map.get("agreementId");
		
		String content = null;
		if (map.containsKey("content")){
			content = (String) map.get("content");
		}
		boolean isAdd=true;
		Agreement a1 = new Agreement();
		List<Agreement> alist = null;
		if(agreementId!=null){
			//查询是否存在合同信息，如果存在后续则做更新，不存在则做插入
			a1.setId(Integer.parseInt(agreementId));
			alist = this.select(a1);
			if(alist!=null&&alist.size()>0){
				a1 = alist.get(0);
				isAdd=false;//本次操作为更新
			}
		}
		a1.setRemark(content);
		a1.setCreateTime(StringAndDate.getTime());
//		a1.setGcdId(gcd_id);
		
		// 保存文件
		List<Attachment> attachment = null;
		//if add
		if(request instanceof MultipartRequest){
			attachment = attachmentService
					.uploadMulti((MultipartRequest) request);
		}
		//如果是新增合同，必须上传图片
		if (attachment == null&&isAdd) {
			throw new RuntimeException("新增合同必须上传图片");
		}
		// 保存合同信息或更新
		if(isAdd){
			if (map.containsKey("appointmentPushId")){
				String appointmentPushId = (String) map.get("appointmentPushId");
				int pushid=Integer.valueOf(appointmentPushId);
				a1.setAppointmentpushId(pushid);
				}
			this.save(a1);
			
			if (map.containsKey("appointmentPushId")){
				String appointmentPushId = (String) map.get("appointmentPushId");
				int pushid=Integer.valueOf(appointmentPushId);
				AppointmentPush push = appointmentSV.findById(pushid);
				Design design = new Design();
				design.setAid(push.getAid());
				design.setSpId(push.getSpId());
				 List<Design> ds = designService.select(design);
				 if(ds.size()>0){
					 Design ds1=ds.get(0);
					 ds1.setAgreementId(a1.getId());
					 designService.update(ds1);
				 }
			}
		}else{
			this.update(a1);
		}
		if(attachment!=null){
			Admin admin = new Admin();
			// 保存附件信息
			attachmentService.saveAttachment(attachment, admin, a1.getId(),
					AttachmentConstant.AGREEMENT_TYPE);
		}
		
//		List<VAttachment> imgurlpathList;
//		imgurlpathList = attachmentService.findAttachment(a1.getId(),
//				AttachmentConstant.AGREEMENT_TYPE);
//		ret.add(a1);
//		ret.add(imgurlpathList);
		rmap.put("id", a1.getId());
		return rmap;
	}
	@Override
	public VAgreementDetail detail(int id) {
		Agreement agreement= this.findById(id);
		VAgreementDetail vAgreementDetail=new VAgreementDetail();
		if(agreement==null)
			return vAgreementDetail;
		else {
			vAgreementDetail.setCreateTime(agreement.getCreateTime());
			vAgreementDetail.setId(agreement.getId());
			if(agreement.getRemark()!=null)
				vAgreementDetail.setRemark(agreement.getRemark());
			List<String> imglist=attachmentService.findForDownload(agreement.getId(), AttachmentConstant.AGREEMENT_TYPE);
			if(imglist.size()>0)
				vAgreementDetail.setImglist(imglist);
			return vAgreementDetail;
		}
	}
}
