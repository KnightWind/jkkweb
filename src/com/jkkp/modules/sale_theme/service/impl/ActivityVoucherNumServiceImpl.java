package com.jkkp.modules.sale_theme.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherNumMapper;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.service.IActivityVoucherNumService;
import com.jkkp.modules.sale_theme.view.VActivityVoucherNum;

@Service("activityVoucherNumService")
public class ActivityVoucherNumServiceImpl extends
		ServiceSupport<ActivityVoucherNum, VActivityVoucherNum, Integer>
		implements IActivityVoucherNumService {

	@Autowired
	private ActivityVoucherNumMapper activityVoucherNumMapper;

	@Override
	protected Mapper<ActivityVoucherNum> getMapper() {
		return activityVoucherNumMapper;
	}

	@Transactional
	public Integer insertOneVoucher(String phone, int voucherId) {
		ActivityVoucherNum entity=new ActivityVoucherNum();
		entity.setPhone(phone);
		entity.setVoucherId(voucherId);
		List<ActivityVoucherNum> list=this.select(entity);
		if(list.size()>0){
			return 0;  //已领取
		}
		ActivityVoucherNum bean = new ActivityVoucherNum();
		bean.setVoucherId(voucherId);
		bean.setPhone(phone);
		bean.setNum(1);
		bean.setHistoryNum(1);
		bean.setUpdateTime(new Date());
		this.save(bean);
		return 1;  //成功领取
	}

}
