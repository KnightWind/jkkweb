package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.QrCode;
import com.jkkp.modules.crowdfunding.view.VQrCode;

public interface QrCodeMapper extends Mapper<QrCode> {
	public List<VQrCode> selectAllQrCode(Map<String, Object> map);

	public Long selectAllQrCodeCount(Map<String, Object> map);
	
	public void updateProductInfo(@Param("productInfo")String productInfo,@Param("id")Integer id);
	
	public void updateAddress(@Param("address")String address,@Param("id")Integer id);
	
	public void updateRemark(@Param("remark")String remark,@Param("id")Integer id);
}
