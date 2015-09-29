package com.jkkp.modules.sale_theme.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherMapper;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.service.IActivityVoucherService;
import com.jkkp.modules.sale_theme.view.VActivityVoucher;

@Service("ActivityVoucherService")
public class ActivityVoucherServiceImpl extends ServiceSupport<ActivityVoucher, VActivityVoucher, Integer>
		implements IActivityVoucherService {
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	private ActivityVoucherMapper ActivityVoucherMapper;
	@Autowired AttachmentServiceImpl attachmentServiceImpl;

	@Override
	protected Mapper<ActivityVoucher> getMapper() {
		return ActivityVoucherMapper;
	}

	@Override
	public List<VActivityVoucher> findActivityVouchers(Integer activityId, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		List<VActivityVoucher> list = ActivityVoucherMapper.qryall(map);
		String basePath = attachmentServiceImpl.getAccessPath();
		for (VActivityVoucher vv : list) {
			vv.setFilepath(basePath + vv.getFilepath());
		}
		return list;
	}

	@Transactional
	public VActivityVoucher getActivityVoucherById(Integer id) {
		return ActivityVoucherMapper.getActivityVoucherById(id);
	}

	@Override
	public void useActivityVoucher(Integer id) {
		ActivityVoucherMapper.useActivityVoucher(id);
	}
}
