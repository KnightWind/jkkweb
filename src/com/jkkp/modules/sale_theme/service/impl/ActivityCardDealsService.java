package com.jkkp.modules.sale_theme.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.view.VPreced;
import com.jkkp.modules.sale_theme.mapper.ActivityCardDealsMapper;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.view.Statistics;
import com.jkkp.modules.sale_theme.view.VActivityCardDeals;

@Service
public class ActivityCardDealsService extends ServiceSupport<ActivityCardDeals, VActivityCardDeals, Integer> implements IActivityCardDealsService{
	@Autowired
	private ActivityCardDealsMapper acdMapper;
	
	@Override
	protected Mapper<ActivityCardDeals> getMapper() {
		return acdMapper;
	}

	@Override
	public VActivityCardDeals findById(Integer businessId, int fileType) {
		return acdMapper.findActivityCardDealsById(businessId,fileType);
	}

	@Override
	public List<ActivityCardDeals> check(String phone) {
		return this.selectByProperty("phone", phone);
	}

	@Override
	public long select(String phone, Integer cardId, Integer status) {
		// TODO Auto-generated method stub
		return acdMapper.selectCountWX(phone,cardId,status);
	}

	@Transactional(readOnly=true)
	public List<Statistics> caclSaleInfo(Map<String, Object> params) {
		return acdMapper.caclSaleInfo(params);
	}

	@Transactional(readOnly=true)
	public long getPrecedBySpId(int spId) {
		List<VPreced> list = acdMapper.getPrecedList();
		for (VPreced p : list) {
			if(p.getSpId().equals(spId)){
				return p.getId();
			}
		}
		return 0;
	}

	@Transactional(readOnly=true)
	public String randomLottery(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return acdMapper.randomLottery(paramMap);
	}
	
	
}
