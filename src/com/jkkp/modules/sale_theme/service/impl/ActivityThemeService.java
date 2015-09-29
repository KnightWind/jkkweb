package com.jkkp.modules.sale_theme.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.mapper.AttachmentMapper;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.sale_theme.mapper.ActivityThemeMapper;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.modules.sale_theme.view.VActivityTheme;

@Service("wxActivityTheme")
public class ActivityThemeService extends ServiceSupport<ActivityTheme, VActivityTheme, Integer> implements IActivityThemeService {
	@Autowired
	private ActivityThemeMapper mapper;
	@Autowired
	private AttachmentMapper attachMapper;
	
	@Override
	protected Mapper<ActivityTheme> getMapper() {
		return mapper;
	}

	@Override
	public List<VActivityTheme> selectAllActivityThemeWX() {
		return mapper.selectAllActivityThemeWX();
	}

	@Override
	public ActivityTheme getlatest() {
		// TODO Auto-generated method stub
		return mapper.latest();
	}

	@Override
	public VActivityTheme getActivityThemeById(int id) {
		ActivityTheme at = mapper.selectByPrimaryKey(id);
		if(at != null) {
			Attachment attach = new Attachment();
			attach.setMainid(id);
			attach.setFiletype(AttachmentConstant.WX_ACTIVITY_THEME);
			attach = attachMapper.selectOne(attach);
			
			VActivityTheme vat = new VActivityTheme();
			if(attach != null) {
				vat.setImagePath(attach.filepath);
			}
			try {
				BeanUtils.copyProperties(vat, at);
				return vat;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}


}
