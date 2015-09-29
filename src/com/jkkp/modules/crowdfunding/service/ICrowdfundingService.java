package com.jkkp.modules.crowdfunding.service;

import java.util.List;
import java.util.Map;

import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.model.ActivityBanner;
import com.jkkp.modules.crowdfunding.model.ActivityItem;
import com.jkkp.modules.crowdfunding.model.ResultItemList;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.supplier.model.Supplier;

/**
 * 众筹活动接口
 * @author xiaozhenyu
 *
 */
public interface ICrowdfundingService {
	
	/**
	 * 获取众筹首页banner信息
	 * @param num 获取数量
	 * @return List<ItemCategoryInfo> banner DTO 集合
	 */
	public List<ActivityBanner> getBannerByNum(Integer num);
	
	/**
	 * 获取首页众筹活动分类信息
	 * @return 众筹活动品类信息
	 */
	public List<Map<String, Object>> getActivityList();
	
	/**
	 * 获取众筹活动热门商品
	 * @param num 查询条数
	 * @param regionid 区域id
	 * @return
	 */
	public List<Map<String, Object>> getActivityHotItem(Map<String,Object> map);
	
	/**
	 * 众筹品类列表
	 * @param crowdId 活动id
	 * @return List<Activity> 众筹品类集合
	 */
	public List<Map<String, Object>> getCrowdItemType(String regionName);
	
	/**
	 * 根据品类获取相关众筹产品列表
	 * @param map key1:categoryId 品类id
	 * @return List<ActivityItem> 众筹商品集合
	 */
	public List<ResultItemList> findCrowdItemList(Map<String,Object> map);
	
	/**
	 * 众筹活动商品详情
	 * @param key1:itemId 商品id key2:activityId
	 * @return ActivityItem 众筹商品dto
	 */
	public ActivityItem getCrowdItemDetail(Integer itemId);
	
	/**
	 * 获取众筹商品图文详细
	 * @param itemId
	 * @return
	 */
	public Item getItemInfo(Integer itemId);
	
	/**
	 * 生成订单信息页面
	 * @param key1:itemId 商品id
	 * @return ActivityItem 众筹商品
	 */
	public ActivityItem newCrowdOrder(Map<String,Object> map);
	
	/**
	 * 查询商家详情
	 * @param spId 商家id
	 * @return Supplier 商家dto
	 */
	public Supplier getSupplierById(Integer spId);
	
	/**
	 * 确定订单
	 * @param map
	 * @return
	 */
	public Map<String, Object> confirmOrder(Map<String,Object> map);
	
	/**
	 * 获取当前最大的订单code
	 * @return
	 */
	public String maxOrderCode();
	
	/**
	 * 保存订单信息，生成订单
	 * @return
	 */
	public boolean saveActivtiyOrder(ActiivityOrder actiivityOrder);
	
	/**
	 * 获取订单列表
	 * @param key1:memberId 用户id orderStatus 订单状态
	 * @return List<ActiivityOrder> 订单信息合集
	 */
	public List<Map<String,Object>> getMyActiivityOrderList(Map<String,Object> map);
	
	/**
	 * 获取我参与的活动列表
	 * @param map key1:memberId
	 * @return
	 */
	public List<Map<String,Object>> getMyActiivityList(Map<String,Object> map);
	
	/**
	 * 更改订单状态
	 * @param orderId  订单id
	 * @return 
	 */
	public boolean updateOrderStatus(ActiivityOrder actiivityOrder);
	
	/**
	 * 
	 * @param activityId 活动id
	 * @param itemId 商品id
	 * @param pageNo 当前页码
	 * @param pageSize 每页条数
	 * @return List<Map<String,Object>> 用户及活动信息
	 */
	public List<Map<String,Object>>  getJoinUserInfo(Map<String,Object> map);
	
	/**
	 * 生成支付二维码
	 * @param cid
	 * @param fee
	 * @return 
	 */
	public void createQrcode(Integer spId,  Double fee,String qrCode); 
	
	/**
	 * 获取扫码支付信息
	 * @param key1:qrCode 扫码支付编码
	 * @return 1.成功：返回公司ID、公司名称、商品名称、支付价格
     *		   2.失败：返回提示 “无法识别的二维码”
	 */
	public Map<String,Object> getPayInfoByQrCode(Map<String,Object> map);
	
	/**
	 * 付款
	 * @param map
	 * @return
	 */
	public Object payCrowdItem(Map<String,Object> map);
	
	/**
	 * 获取用户账单列表
	 * @param memberId 用户id
	 * @return
	 */
	public Map<String,Object> getBillListById(String memberId);
	
	/**
	 * 获取用户账单详情
	 * @param memberId 用户id
	 * @return
	 */
	public Map<String,Object> getBillDetailsById(String memberId);
	
	/**
	 * 用户退款操作
	 * @param orderId 订单id
	 * @param itemId 产品id
	 */
	public void refundOrder(Map<String,Object> map);
	
	
	/**
	 * 付款
	 * @param map
	 * @return
	 */
	public Object paymentByQrCode(Map<String,Object> map);
	
	/**
	 * 获取订单详情
	 * @param orderId 订单id
	 * @return
	 */
	public Map<String,Object> getCrowdOrderDeail(Integer orderId);
	
	public boolean insertRefundAudit(RefundApplyAudit refundApplyAudit);
}
