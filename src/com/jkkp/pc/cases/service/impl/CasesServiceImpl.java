package com.jkkp.pc.cases.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.mapper.CaseMapper;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.pc.cases.service.ICasesService;
import com.jkkp.pc.cases.view.VCases;
import com.jkkp.utils.CheckedUtil;

@Component("/casesServicePC")
public class CasesServiceImpl extends ServiceSupport<Case, VCases, Integer> implements ICasesService {

	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private IAttachmentService attachmentService;
	
	@Override
	protected Mapper<Case> getMapper() {
		return caseMapper;
	}

	@Transactional(readOnly=true)
	public List<VCases> queryXCases(Integer id,Integer type,Integer count) {
		List<VCases> list = caseMapper.queryXCases(id,type,count);
		for (VCases vc : list) {
			if(CheckedUtil.isNotEmpty(vc.getDesignId())){
				List<VAttachment> attachments = attachmentService.findAttachment(vc.getDesignId(), AttachmentConstant.DESIGN_TYPE);
				for (VAttachment va : attachments) {
					if(CheckedUtil.isNotEmpty(va.getDownloadPath())){
						vc.setPath(va.getDownloadPath());
					}
				}
			}
		}
		return list;
	}

	

}
