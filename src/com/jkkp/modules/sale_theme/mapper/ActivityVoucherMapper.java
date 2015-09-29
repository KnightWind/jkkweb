package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.view.VActivityVoucher;

public interface ActivityVoucherMapper extends Mapper<ActivityVoucher> {

	List<VActivityVoucher> qryall(Map<String, Object> map);
	
	List<VActivityVoucher> selectActivityVoucher(Map<String, Object> map);

	long selectActivityVoucherCount(Map<String, Object> map);
	
	VActivityVoucher getActivityVoucherById(Integer id);
	
	List<VActivityVoucher> selectSupplierVoucher(Map<String, Object> map);
	
	Long selectSupplierVoucherCount(Map<String, Object> map);
	
	void useActivityVoucher(@Param("id")Integer id);
	
}
