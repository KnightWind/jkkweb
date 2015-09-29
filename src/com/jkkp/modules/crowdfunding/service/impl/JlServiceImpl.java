package com.jkkp.modules.crowdfunding.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.JlServiceMapper;
import com.jkkp.modules.crowdfunding.model.JlService;
import com.jkkp.modules.crowdfunding.service.IJlService;
import com.jkkp.modules.crowdfunding.view.VJlService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;

@Service("zcjlService")
public class JlServiceImpl extends ServiceSupport<JlService, VJlService, Integer> implements
		IJlService {

	@Autowired
	private JlServiceMapper jlServiceMapper;
	
	@Override
	protected Mapper<JlService> getMapper() {
		return jlServiceMapper;
	}

	@Override
	public void addOne(JlService bean) {
		bean.setCreateDate(new Date());
		bean.setCispay(0);
		bean.setOrderCode(DateUtil.format(new Date(), "yyyyMMddHHmmss") + CommonUtil.getRandomStr(6));
		this.save(bean);
		
	}

	@Override
	public List<JlService> hasBuyJLService(String phone) {
		return selectByProperty(new String[]{"phone","cispay"}, new Object[]{phone,1});
	}

	@Override
	public void updateInfo(Integer id) {
		jlServiceMapper.updateInfo(id);
	}

	@Override
	public JlService getByOrderCode(String orderCode) {
		List<JlService> list = selectByProperty(new String[]{"orderCode"}, new Object[]{orderCode});
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
      
}
