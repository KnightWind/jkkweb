package com.jkkp.modules.crowdfunding.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.MemberRedPackageVO;
import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.appapi.common.service.interfaces.IRegionSV;
import com.jkkp.client.alipay.service.AlipayService;
import com.jkkp.client.yeepay.api.ZGTService;
import com.jkkp.client.yeepay.constant.YeePayCode;
import com.jkkp.client.yeepay.service.YeePayUtil;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.crowdfunding.mapper.ActivityItemMapper;
import com.jkkp.modules.crowdfunding.mapper.ActivityMapper;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.mapper.ItemCategoryInfoMapper;
import com.jkkp.modules.crowdfunding.model.ActItemOrder;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.model.ActivityBanner;
import com.jkkp.modules.crowdfunding.model.ActivityItem;
import com.jkkp.modules.crowdfunding.model.ActivityRefundOrder;
import com.jkkp.modules.crowdfunding.model.QrCode;
import com.jkkp.modules.crowdfunding.model.ResultItemList;
import com.jkkp.modules.crowdfunding.service.ICrowdfundingService;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.IRegionService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Escape;
import com.service.MemberRedPackageService;

@Service("crowdfoundService")
public class CrowdfundingServiceImpl implements ICrowdfundingService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private AlipayService alipayService = new AlipayService();
	@Autowired
	private ItemCategoryInfoMapper crowdfundingMapper;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ActivityItemMapper activityItemMapper;
	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired
	private AttachmentServiceImpl attachmentService;
	@Autowired
	private RefundApplyAuditMapper refundApplyAuditMapper;
	@Autowired
	private IRegionService regionService;
	@Autowired 
	private IRegionSV regionsv;
	@Autowired
	private MemberRedPackageService memberRedPackageService;
	
	public ItemCategoryInfoMapper getCrowdfundingMapper() {
		return crowdfundingMapper;
	}

	@Override
	public List<ActivityBanner> getBannerByNum(Integer num) {
		List<ActivityBanner> list = crowdfundingMapper.getBannerByNum(num);
		if(list.size() > 0 && list!=null){
			for (ActivityBanner activityBanner : list) {
				activityBanner.setUrl(attachmentService.getAccessPath() + activityBanner.getUrl());
			}
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getActivityList() {
		List<Map<String, Object>> list = activityMapper.getActivityList();
		if(list.size() > 0 && list!=null){
			for (Map<String, Object> map : list) {
				List<VAttachment> imglist = attachmentService.findAttachment((int)map.get("id"), AttachmentConstant.ACTIVITY_HOME);
				if(imglist!=null && imglist.size() > 0){
					map.put("url", attachmentService.getAccessPath() + imglist.get(0).getFilepath());
				}else{
					map.put("url", "");
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getActivityHotItem(Map<String,Object> maps) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		List<Integer> regionidList = new ArrayList<Integer>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		mapParam.put("num", maps.get("num"));
		if(maps.get("regionName") == null){
			return list;
		}
		
		mapParam.put("cityname", maps.get("regionName"));
		mapParam.put("status", 1);
		mapParam.put("level", 2);
		Region region = regionsv.findRegionByName(mapParam);
		mapParam.put("regionidList", regionService.getRegionIdList(region.getRegionid(), regionidList));
		list = activityMapper.getActivityHotItem(mapParam);
		if(list.size() > 0 && list!=null){
			for (Map<String, Object> map : list) {
				List<VAttachment> imglist = attachmentService.findAttachment((int)map.get("id"), AttachmentConstant.ACTIVITY_ZC_ITEM);
				if(imglist!=null && imglist.size() > 0){
					map.put("url", attachmentService.getAccessPath() + imglist.get(0).getFilepath());
				}else{
					map.put("url", "");
				}
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCrowdItemType(String regionName) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(regionName == null){
			return list;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cityname", regionName);
		map.put("status", 1);
		map.put("level", 2);
		Region region = regionsv.findRegionByName(map);
		List<Integer> regionidList = new ArrayList<Integer>();
		map.put("regionidList", regionService.getRegionIdList(region.getRegionid(), regionidList));
		return activityMapper.getCrowdItemType(map);
	}

	@Override
	public List<ResultItemList> findCrowdItemList(Map<String, Object> map) {
		List<ResultItemList> list = new ArrayList<ResultItemList>();
		if(map.get("regionName")==null){
			return list;
		}
		map.put("cityname", map.get("regionName"));
		map.put("status", 1);
		map.put("level", 2);
		Region region = regionsv.findRegionByName(map);
		List<Integer> regionidList = new ArrayList<Integer>();
		map.put("regionidList", regionService.getRegionIdList(region.getRegionid(), regionidList));
		list = activityItemMapper.findCrowdItemList(map);
		if(list!=null && list.size() > 0){
			for (ResultItemList resultItemList : list) {
				List<VAttachment> itemimg = attachmentService.findAttachment(resultItemList.getItemId(), AttachmentConstant.ITEM_PICTURE_PATH);
				if(itemimg!=null && itemimg.size() >0){
					resultItemList.setImg(attachmentService.getAccessPath() + itemimg.get(0).getFilepath());
				}else{
					resultItemList.setImg("");
				}
			}
		}
		return list;
	}

	@Override
	public ActivityItem getCrowdItemDetail(Integer itemId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		ActivityItem activityItem = new ActivityItem();
		activityItem = activityItemMapper.getCrowdItemDetail(map);
		if(activityItem!=null){
			if(activityItem.getDetail()!=null){
				activityItem.setDetail(Escape.unescape(activityItem.getDetail()));
			}
			List<VAttachment> itemimg = attachmentService.findAttachment(activityItem.getItemId(), AttachmentConstant.ITEM_PICTURE_PATH);
			if(itemimg != null && itemimg.size() > 0){
				List<String> url = new ArrayList<String>();
				for (VAttachment vAttachment : itemimg) {
					url.add(attachmentService.getAccessPath() + vAttachment.getFilepath());
				}
				activityItem.setImgslist(url);
			}
			List<VAttachment> spimg = attachmentService.findAttachment(activityItem.getSpId(), AttachmentConstant.SUPPLIER_STAFF_TYPE);
			if(spimg != null && spimg.size() > 0){
				for (VAttachment vAttachment : spimg) {
					activityItem.setSpImg(attachmentService.getAccessPath() + vAttachment.getFilepath());
				}
			}
		}
		return activityItem;
	}

	public Item getItemInfo(Integer itemId){
		Item item = new Item();
		item = activityItemMapper.getItemInfo(itemId);
//		item.setImgList(activityItemMapper.getImagesByItemId(itemId));
		return item;
	}

	@Override
	public ActivityItem newCrowdOrder(Map<String,Object> map) {
		ActivityItem activityItem = new ActivityItem();
		activityItem = activityItemMapper.newCrowdOrder(map);
		if(activityItem!=null){
			List<VAttachment> itemimg = attachmentService.findAttachment(activityItem.getItemId(), AttachmentConstant.ITEM_PICTURE_PATH);
			if(itemimg!=null && itemimg.size() >0){
				activityItem.setImages(attachmentService.getAccessPath() + itemimg.get(0).getFilepath());
			}else{
				activityItem.setImages("");
			}
			List<VAttachment> spImglist = attachmentService.findAttachment(activityItem.getSpId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			if(spImglist!=null && spImglist.size() >0){
				activityItem.setSpImg(attachmentService.getAccessPath() + spImglist.get(0).getFilepath());
			}else{
				activityItem.setSpImg("");
			}
		}
		return activityItem;
	}

	@Override
	public Supplier getSupplierById(Integer spId) {
		return activityItemMapper.getSupplierById(spId);
	}

	@Override
	@Transactional
	public Map<String, Object> confirmOrder(Map<String, Object> map) {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		ActivityItem activityItem = new ActivityItem();
		ActiivityOrder actiivityOrder = new ActiivityOrder();
		ActItemOrder actItemOrder = new ActItemOrder();
		activityItem = activityItemMapper.newCrowdOrder(map);
		if(activityItem==null){
			throw new RuntimeException("暂无相关的众筹活动产品信息!"); 
		}
		int orderNum = (int) map.get("orderNum");
		double deposit = activityItem.getDeposit(); //定金
		double orderPrice = activityItem.getActivityPrice()*orderNum; //订单价格
		double retainage = orderPrice - deposit;
		int memberId = (int) map.get("memberId");
		String type = map.get("type").toString();
		
		String maxcode = maxOrderCode();
		actiivityOrder.setOrderCode(maxcode);
		actiivityOrder.setSupplierId(activityItem.getSpId());
		actiivityOrder.setMemberId(memberId);
		actiivityOrder.setDeposit(deposit);
		actiivityOrder.setOrderPrice(orderPrice);
		 if("2".equals(type)){  //支付定金和尾款操作
			actiivityOrder.setRetainage(retainage);
		}
		 
		actiivityOrder.setPayType(1); //普通支付
		actiivityOrder.setMoneyState(0);
		actiivityOrder.setExpressId(0);
		
		actItemOrder.setItemId(String.valueOf(activityItem.getItemId()));
		actItemOrder.setEndPrice(activityItem.getActivityPrice());
		actItemOrder.setItemNum(orderNum);
		actItemOrder.setOrderCode(maxcode);
		actItemOrder.setSalePrice(activityItem.getTopPrice());
		actItemOrder.setItemType(1); //普通商品
		
		activtyOrderMapper.insertActiivityOrder(actiivityOrder);
		activtyOrderMapper.insertActItemOrder(actItemOrder);

		resultmap.put("orderCode", maxcode);
		resultmap.put("itemName", activityItem.getTitle());
		resultmap.put("itemId", activityItem.getItemId());
		resultmap.put("activityPrice", activityItem.getActivityPrice());
		resultmap.put("counts", activityItem.getCounts());
		resultmap.put("orderNum", orderNum);
		resultmap.put("deposit", deposit);
		resultmap.put("retainage", retainage);
		resultmap.put("spId", activityItem.getSpId());
		resultmap.put("supplierName", activityItem.getSupplierName());
		resultmap.put("type", map.get("type"));
		resultmap.put("price", activityItem.getTopPrice());
		
		List<VAttachment> spimglist = attachmentService.findAttachment(activityItem.getSpId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		List<VAttachment> itemimglist = attachmentService.findAttachment(activityItem.getItemId(), AttachmentConstant.ITEM_PICTURE_PATH);
		String spImg = "";
		String itemImg = "";
		if(spimglist!=null && spimglist.size() > 0){
			spImg = attachmentService.getAccessPath() + spimglist.get(0).getFilepath();
		}
		if(itemimglist!=null && itemimglist.size() > 0){
			itemImg = attachmentService.getAccessPath() +itemimglist.get(0).getFilepath();
		}
		resultmap.put("splogo", spImg);
		resultmap.put("itemImg", itemImg);
		
		Map<String,Object> redMap = new HashMap<String, Object>();
		redMap.put("memberId", memberId);
		redMap.put("platform", 2);
		redMap.put("orderNo", maxcode);
		redMap.put("categoryId", activityItem.getCategoryId());
		if("1".equals(type)){
			redMap.put("useCondition", 1);
			redMap.put("orderNum", deposit);
		}else if("2".equals(type)){
			redMap.put("useCondition", 0);
			redMap.put("orderNum", retainage);
		}
		
		List<MemberRedPackageVO> redPkgList = memberRedPackageService.findOrderRedPackageList(redMap);
		resultmap.put("redpacklist", redPkgList);
		return resultmap;
	}

	@Transactional
	@Override
	public String maxOrderCode() {
		Date today=new Date();
	    SimpleDateFormat f=new SimpleDateFormat("yyyyMMddhhmmssSSS");
	    String time = f.format(today) + getRandomStr(6);
		return time;
	}
	
	

	@Override
	public boolean saveActivtiyOrder(ActiivityOrder actiivityOrder) {
		return false;
	}

	@Override
	public List<Map<String,Object>> getMyActiivityOrderList(Map<String,Object> map) {
		List<Map<String,Object>> list = activtyOrderMapper.getMyActiivityOrderList(map);
		if(list!=null && list.size() > 0){
			for (Map<String, Object> map2 : list) {
				if(Integer.valueOf(map2.get("itemType").toString()) == 1){
					List<VAttachment> spimglist = attachmentService.findAttachment(Integer.parseInt(map2.get("itemId").toString()), AttachmentConstant.ITEM_PICTURE_PATH);
					if(spimglist!=null && spimglist.size() > 0){
						map2.put("url", attachmentService.getAccessPath() + spimglist.get(0).getFilepath());
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMyActiivityList(Map<String, Object> map) {
		List<Map<String, Object>> list = activtyOrderMapper.getMyActiivityList(map);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list.size() > 0){
			for (Map<String, Object> map2 : list) {
				if(map2.get("endTime")!=null){
					map2.put("endTime", fmt.format(map2.get("endTime")));
				}
				List<VAttachment> spimglist = attachmentService.findAttachment((Integer)map2.get("item_id"), AttachmentConstant.ITEM_PICTURE_PATH);
				if(spimglist!=null && spimglist.size() > 0){
					map2.put("url", attachmentService.getAccessPath() + spimglist.get(0).getFilepath());
				}
			}
		}
		return list;
	}

	@Override
	public boolean updateOrderStatus(ActiivityOrder actiivityOrder) {
		return activtyOrderMapper.updateOrderStatus(actiivityOrder);
	}

	@Override
	public List<Map<String, Object>> getJoinUserInfo(Map<String,Object> map) {
		List<Map<String, Object>> list = activityMapper.getJoinUserInfo(map);
		if(list!=null && list.size() > 0){
			for (Map<String, Object> map2 : list) {
				List<VAttachment> spimglist = attachmentService.findAttachment(Integer.valueOf((String.valueOf(map2.get("memberId")))), AttachmentConstant.MEMBER_TYPE);
				if(spimglist!=null && spimglist.size() > 0){
					map2.put("img", attachmentService.getAccessPath() + spimglist.get(0).getFilepath());
				}else{
					map2.put("img", "");
			    }
			}
		}
		return list;
	}

	@Override
	public void createQrcode(Integer spId, Double fee,String cd) {
		QrCode qrCode = new QrCode();
		qrCode.setFee(fee);
		qrCode.setSpId(spId);
		qrCode.setQrCode(cd);
		activtyOrderMapper.insertQrCode(qrCode);
	}

	@Override
	@Transactional
	public Map<String, Object> getPayInfoByQrCode(Map<String,Object> map) {
		Map<String, Object> resultMap = activtyOrderMapper.getPayInfoByQrCode(map);
		if(resultMap!=null){
			resultMap.put("url", resultMap.get("url"));
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			ActItemOrder actItemOrder = new ActItemOrder();
			
			String maxcode = maxOrderCode();
			actiivityOrder.setOrderCode(maxcode);
			actiivityOrder.setSupplierId((Integer)resultMap.get("spId"));
			actiivityOrder.setMemberId(CommonUtil.stringToInteger((String)map.get("memberId")));
			actiivityOrder.setDeposit(Double.parseDouble(String.valueOf(resultMap.get("final_fee"))));
			actiivityOrder.setMoneyState(0);
			actiivityOrder.setExpressState(0);
			actiivityOrder.setPayType(2); //表示扫码支付
			
			actItemOrder.setItemId((String)map.get("qrCode"));
			actItemOrder.setEndPrice(Double.parseDouble(String.valueOf(resultMap.get("final_fee"))));
			actItemOrder.setItemNum(1);
			actItemOrder.setOrderCode(maxcode);
			actItemOrder.setSalePrice(Double.parseDouble(String.valueOf(resultMap.get("final_fee"))));
			actItemOrder.setItemType(2);
			activtyOrderMapper.insertActiivityOrder(actiivityOrder);
			activtyOrderMapper.insertActItemOrder(actItemOrder);
			resultMap.put("orderCode", maxcode);
			resultMap.put("final_fee",resultMap.get("final_fee")); //尾款
			resultMap.put("type", resultMap.get("type"));
			resultMap.put("order_fee", 0);
			resultMap.put("deposit_id", null);
			
			List<VAttachment> spimglist = attachmentService.findAttachment((Integer)resultMap.get("spId"), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			if(spimglist!=null && spimglist.size()>0){
				resultMap.put("url", attachmentService.getAccessPath() + spimglist.get(0).getFilepath());
			}
			List<Map<String,Object>> rtmap = activtyOrderMapper.getDeposit(map); //是否有定金
			if(rtmap!=null && rtmap.size() > 0){
				resultMap.put("order_fee", rtmap.get(0).get("deposit"));
				resultMap.put("deposit_id", rtmap.get(0).get("order_code"));
			}
			
			Map<String,Object> redMap = new HashMap<String, Object>();
			redMap.put("memberId", map.get("memberId"));
			redMap.put("platform", 2);
			redMap.put("orderNo", maxcode);
			redMap.put("categoryId", 0);
			redMap.put("orderNum", resultMap.get("final_fee"));
			if("1".equals(resultMap.get("type"))){
				redMap.put("useCondition", 1);
			}else if("2".equals(resultMap.get("type"))){
				redMap.put("useCondition", 0);
			}
			
			List<MemberRedPackageVO> redPkgList = memberRedPackageService.findOrderRedPackageList(redMap);
			resultMap.put("redpacklist", redPkgList);
		}
		return resultMap;
	}

	@Override
	public Object payCrowdItem(Map<String, Object> map) {
		try {
			//订单是否已经支付
			Map<String,Object> result = new HashMap<String, Object>();
			Map<String,Object> mp = new HashMap<String, Object>();
			mp.put("orderCode", map.get("orderCode"));
			ActiivityOrder actiivityOrder = activtyOrderMapper.getAcitvityOrderByParam(mp);
			if(actiivityOrder != null){
				int moneyState = actiivityOrder.getMoneyState();
				String type = (String) map.get("type");
				if("1".equals(type) && moneyState == 1){
					return result.put("msgCode", "已支付定金，不用重复提交！");
				}else if ("2".equals(type) && moneyState == 2){
					return result.put("msgCode", "已支付尾款，不用重复提交！");
				}
			}
			
			PaymentRecord record = paymentRecordService.saveBuildingActivityOrder(map);
			if("1".equals(map.get("paymentModel"))){ //支付宝
				return new ApiResponse<String>(alipayService.createRequestParams(record, (String)map.get("returnUrl")));
			} else if("2".equals(map.get("paymentModel"))){//易宝支付
				return createResponseObject(record,(String)map.get("returnUrl"));
			} else if("3".equals(map.get("paymentModel"))) {//微信支付
				return record;
			}
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
		return null;
	}	
	
	
	private Object createResponseObject(PaymentRecord record, String returnUrl) {
		Map<String, String> result = this.payment(record, returnUrl);
		String returnCode = result.get("code");
		if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
			return new ApiResponse<String>(result.get("payurl"));
		} else {
			return new ApiResponse<String>(false, YeePayCode.getErrorMessage(returnCode));
		}
	}
	
	public Map<String, String> payment(PaymentRecord record, String returnUrl) {
		Map<String, String> params = createPaymentRequest(record, returnUrl);
		return ZGTService.paymentRequest(params);
	}
	
	private Map<String, String> createPaymentRequest(PaymentRecord record, String returnUrl) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("requestid", record.getSerialCode());
		params.put("amount", String.valueOf(record.getAmount()));
		params.put("assure", YeePayUtil.ASSURE_NO);
		params.put("productname", record.getTitle());
		if(StringUtils.isBlank(YeePayUtil.CALLBACK_URL)) {
			YeePayUtil.CALLBACK_URL = Sysconfig.CONFIG_PARAM.get(ConfigConstant.YEEPAY_CALLBACK_URL);
		}
		System.out.println("yeepay callbackurl==" + YeePayUtil.CALLBACK_URL);
		params.put("callbackurl", YeePayUtil.CALLBACK_URL);
		params.put("webcallbackurl", returnUrl);
		params.put("payproducttype", "ONEKEY");
		return params;
	}

	@Override
	public Map<String, Object> getBillListById(String memberId) {
		return activtyOrderMapper.getBillListById(memberId);
	}

	@Override
	public void refundOrder(Map<String,Object> map) {
		ActivityRefundOrder activityRefundOrder = new ActivityRefundOrder();
		int orderId= CommonUtil.stringToInteger((String)map.get("orderId"));
		int memberId= CommonUtil.stringToInteger((String)map.get("memberId"));
		String remark = String.valueOf(map.get("remark"));
		
		activityRefundOrder.setOrderId(orderId);
		activityRefundOrder.setMemberId(memberId);
		activityRefundOrder.setRemark(remark);
		activtyOrderMapper.insertActivityRefundOrder(activityRefundOrder);
	}

	@Override
	public Map<String, Object> getBillDetailsById(String recordId) {
		return activtyOrderMapper.getBillDetailsById(recordId);
	}

	@Override
	public Object paymentByQrCode(Map<String, Object> map) {
		try {
			System.out.println("1 paymentByQrCode map====>" + map);
			//订单是否已经支付
			Map<String,Object> result = new HashMap<String, Object>();
			ActiivityOrder actiivityOrder = activtyOrderMapper.getAcitvityOrderByParam(map);
			System.out.println("2 paymentByQrCode actiivityOrder====>" + actiivityOrder);
			
			if(actiivityOrder != null){
				int moneyState = actiivityOrder.getMoneyState();
				String type = (String) map.get("type");
				if("1".equals(type) && moneyState == 1){
					return result.put("msgCode", "已支付定金，不用重复提交！");
				}else if ("2".equals(type) && moneyState == 2){
					return result.put("msgCode", "已支付尾款，不用重复提交！");
				}
			}
			
			map.put("orderId", actiivityOrder.getId());
			double fee = actiivityOrder.getDeposit();
			float redFee = memberRedPackageService.usedMyRedPackge(map);
			if(redFee >= fee){
				return result.put("doneCode", "8888");
			}
			
			map.put("redFee", redFee);
			PaymentRecord record = paymentRecordService.paymentByQrCode(map);
			System.out.println("3 paymentByQrCode record====>" + record);
			if("1".equals(map.get("pay_type"))){
				String ret = alipayService.createAppParams(record);
				System.out.println("alipayService--->" + ret);
				return new ApiResponse<String>(ret);
			}else if("2".equals(map.get("pay_type"))){
				String ret = createResponseObject(record,(String)map.get("returnUrl")).toString();
				System.out.println("yeepay--->" + ret);
				return ret;
			}
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
		return null;
	}
	
	public String getRandomStr(int length) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i < length; i++) {
			sb.append((int)(10 * Math.random()));
		}
		return sb.toString();
	}

	@Override
	public Map<String, Object> getCrowdOrderDeail(Integer orderId) {
		Map<String,Object> mapparam = new HashMap<String, Object>();
		Map<String,Object> resultMap = new HashMap<String, Object>();
		mapparam.put("orderId", orderId);
		resultMap = activtyOrderMapper.getCrowdOrderDeail(mapparam);
		if(resultMap!=null){
			if("1".equals(resultMap.get("itemType"))){
				List<VAttachment> itemimg = attachmentService.findAttachment(Integer.valueOf(String.valueOf(resultMap.get("itemId"))), AttachmentConstant.ITEM_PICTURE_PATH);
				if(itemimg!=null && itemimg.size() > 0){
					resultMap.put("itemImg", attachmentService.getAccessPath() + itemimg.get(0).getFilepath());
				}
			}
			List<VAttachment> spImg = attachmentService.findAttachment(Integer.valueOf(String.valueOf(resultMap.get("spId"))), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			if(spImg!=null && spImg.size() > 0){
				resultMap.put("spImg", attachmentService.getAccessPath() + spImg.get(0).getFilepath());
			}
		}
		
		Map<String,Object> redMap = new HashMap<String, Object>();
		redMap.put("memberId", resultMap.get("memberId"));
		redMap.put("platform", 2);
		redMap.put("orderNo", resultMap.get("orderCode"));
		redMap.put("categoryId", resultMap.get("cate"));
		int moneyState = Integer.valueOf(resultMap.get("moneyState").toString());
		float deposit = Float.valueOf(resultMap.get("deposit").toString());
		float retainage = Float.valueOf(resultMap.get("activityPrice").toString());
		if(moneyState == 0){
			redMap.put("orderNum", deposit);
			redMap.put("useCondition", 1);
		}else if(moneyState == 1){
			redMap.put("orderNum", retainage + retainage);
			redMap.put("useCondition", 0);
		}
		
		if("1".equals(resultMap.get("type"))){
			redMap.put("useCondition", 1);
		}else if("2".equals(resultMap.get("type"))){
			redMap.put("useCondition", 0);
		}
		
		List<MemberRedPackageVO> redPkgList = memberRedPackageService.findOrderRedPackageList(redMap);
		resultMap.put("redpacklist", redPkgList);
		return resultMap;
	}
	
	@Transactional
	public boolean insertRefundAudit(RefundApplyAudit refundApplyAudit) {
		ActiivityOrder actiivityOrder = new ActiivityOrder();
		actiivityOrder.setId(refundApplyAudit.getBusinessId());
		actiivityOrder.setMoneyState(1);
		actiivityOrder.setCreateDate(new Date());
		activtyOrderMapper.updateOrderStatus(actiivityOrder);
		return refundApplyAuditMapper.insertRefundAudit(refundApplyAudit);
	}
}
