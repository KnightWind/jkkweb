package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.ActItemOrder;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.model.ActivityRefundOrder;
import com.jkkp.modules.crowdfunding.model.ItemMember;
import com.jkkp.modules.crowdfunding.model.QrCode;
import com.jkkp.modules.crowdfunding.view.VItemOrder;

public interface ActivtyOrderMapper extends Mapper<ActiivityOrder> {
	
public Map<String,Object> confirmOrder(Map<String, Object> map);
	
	public String maxOrderCode() ;

	public boolean saveActivtiyOrder(ActiivityOrder actiivityOrder);
	
	public void insertActItemOrder(ActItemOrder actItemOrder);
	
	public void insertActiivityOrder(ActiivityOrder actiivityOrder);
	
	public void insertActItemMember(ItemMember itemMember);
	
	public List<Map<String,Object>> getMyActiivityOrderList(Map<String,Object> map);
	
	public List<Map<String,Object>> getMyActiivityList(Map<String,Object> map);
	
	public boolean updateOrderStatus(ActiivityOrder actiivityOrder);
	
	public void insertQrCode(QrCode qrCode);
	
	public Map<String, Object> getPayInfoByQrCode(Map<String,Object> map) ;
	
	public ActiivityOrder getAcitvityOrderById(Integer orderId) ;

	public Map<String, Object> getActivityOrder(Map<String,Object> map) ;
	
	public Map<String,Object> getBillListById(String memberId);
	
	public Map<String,Object> getBillDetailsById(String recordId);
	
	public void insertActivityRefundOrder(ActivityRefundOrder activityRefundOrder);
	
	public void refundOrder();
	
	public Double selectJSTotal(@Param("spId") Integer spId);
	
	public List<VItemOrder> selectAllSupplierZCOrder(Map<String, Object> map);
	
	public Long selectAllSupplierZCOrderCount(Map<String, Object> map);
	
	public List<Map<String,Object>> getDeposit(Map<String,Object> map);
	
	public ActiivityOrder getAcitvityOrderByParam(Map<String,Object> map) ;
	
	public Map<String,Object> getCrowdOrderDeail(Map<String,Object> map);
	
	public Map<String,Object> getOrderInfo(Map<String,Object> map);
	
	public Map<String,Object> getAcitivityJoinMember(Map<String,Object> map);
	
	//店面发货
	public void updateZcOrderExpressToOne(@Param("id") Integer id);
	
	//物流发货
	public void updateZcOrderExpressToTwo(@Param("id") Integer id);
}


