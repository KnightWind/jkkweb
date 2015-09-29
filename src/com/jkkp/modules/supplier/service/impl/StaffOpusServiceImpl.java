package com.jkkp.modules.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.supplier.mapper.StaffOpusMapper;
import com.jkkp.modules.supplier.model.StaffOpus;
import com.jkkp.modules.supplier.service.IStaffOpusService;
import com.jkkp.modules.supplier.view.VStaffOpus;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.model.Admin;

@Service("staffOpusService")
public class StaffOpusServiceImpl extends
		ServiceSupport<StaffOpus, VStaffOpus, Integer> implements
		IStaffOpusService {

	@Autowired
	private StaffOpusMapper staffOpusMapper;
	@Autowired
	private IAttachmentService attachmentService;

	@Override
	protected Mapper<StaffOpus> getMapper() {
		return staffOpusMapper;
	}

	@Transactional(readOnly = true)
	public VStaffOpus getVStaffOpusById(Integer id) {
		return staffOpusMapper.getVStaffOpusById(id);
	}

	@Transactional(readOnly = false)
	public void savaOrUpdate(VSupplierUser su, StaffOpus opus, Integer[] imgId,HttpServletRequest request) {
		if (opus != null) {
			if (opus.getId() != null && opus.getId() > 0) {
				// 删除更换后的图片记录跟 imgId[] 更换图片后对应附件表的id集合
				List<Integer> ids = new ArrayList<Integer>();
				if (imgId != null) {
					for (Integer attId : imgId) {
						if (attId != null) {
							Attachment at = attachmentService.findById(attId);
							if (at != null) {
								attachmentService.delete(at);
								ids.add(attId);
							}
						}
					}
				}
				StaffOpus opusOld = this.findById(opus.getId());
				opusOld.setTitle(opus.getTitle());
				opusOld.setStaffId(opus.getStaffId());
				opusOld.setStatus(0);
				this.update(opusOld);
			} else {

				this.save(opus);
			}
			List<Attachment> attachmentList = attachmentService
					.uploadMulti((MultipartRequest) request);
			if (attachmentList != null) {
				for (Attachment attachment : attachmentList) {
					attachmentService.saveAttachment(attachment,
							new Admin(), opus.getId(),
							AttachmentConstant.STAFF_OPUS_TYPE);
				}
			}
		}

	}
}
