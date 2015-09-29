package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.view.VActivityVoucherNum;
import com.jkkp.modules.sale_theme.view.VMyVouchers;

public interface ActivityVoucherNumMapper extends Mapper<ActivityVoucherNum> {

	List<VMyVouchers> qryall(Map<String, Object> inMap);

	// web后台
	public List<VActivityVoucherNum> selectAllActivityVoucherNum(Map<String, Object> map);

	public Long selectAllActivityVoucherNumCount(Map<String, Object> map);

}
