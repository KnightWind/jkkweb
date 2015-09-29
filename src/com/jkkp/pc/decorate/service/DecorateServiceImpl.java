package com.jkkp.pc.decorate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.design.mapper.DesignCateMapper;
import com.jkkp.modules.design.mapper.DesignMapper;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.pc.decorate.view.VDecorate;
import com.jkkp.utils.CheckedUtil;

@Component("decorateServicePC")
public class DecorateServiceImpl extends
		ServiceSupport<DesignCate, VDecorate, Integer> implements
		IDecorateService {

	@Autowired
	private DesignCateMapper designCateMapper;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private AttachmentServiceImpl attachmentServiceImpl;
	@Autowired
	private DesignMapper designMapper;

	@Override
	protected Mapper<DesignCate> getMapper() {
		return designCateMapper;
	}

	@Transactional(readOnly=true)
	public List<DesignCate> getDesignCases() {
		DesignCate cate=new DesignCate();
		return this.select(cate);
	}

	@Transactional(readOnly=true)
	public List<VDecorate> queryDecorateByPBL(Map<String, Object> paramMap) {
		List<VDecorate> list = designMapper.queryDecorateByPBL(paramMap);
		for (VDecorate vd : list) {
			if(CheckedUtil.isNotEmpty(vd.getId())){
				//String basePath = attachmentServiceImpl.getAccessPath();
				//String path = attachmentService.findForDownloadById(vd.getPid());
				List<VAttachment> attachments = attachmentService.findAttachment(vd.getId(), AttachmentConstant.DESIGN_TYPE);
				for (VAttachment va : attachments) {
					if(CheckedUtil.isNotEmpty(va.getDownloadPath())){
						vd.setPath(va.getDownloadPath());
						break;
					}
				}
			}
		}
		return list;
	}

	@Transactional(readOnly=true)
	public VDecorate queryDecorateById(Integer id) {
		return designMapper.queryDecorateById(id);
	}

	@Transactional(readOnly=true)
	public long queryDecorateByPBLCount(Map<String, Object> paramMap) {
		return designMapper.queryDecorateByPBLCount(paramMap);
	}

}
