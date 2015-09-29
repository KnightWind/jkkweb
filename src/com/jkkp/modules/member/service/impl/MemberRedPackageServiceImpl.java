package com.jkkp.modules.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.MemberRedPackageVO;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.member.mapper.MemberRedPackageMapper;
import com.jkkp.modules.member.model.MemberRedPackage;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.service.MemberRedPackageService;

@Service("memberRedPackageService")
public class MemberRedPackageServiceImpl extends ServiceSupport<MemberRedPackage, MemberRedPackageVO, Integer> implements MemberRedPackageService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	MemberRedPackageMapper mapper; 
	@Autowired
	ActivtyOrderMapper activtyOrderMapper;
	@Autowired
	IPaymentRecordService paymentRecordService;

	@Override
	protected Mapper<MemberRedPackage> getMapper() {
		return mapper;
	}
	/**
	 * 获取我的钱包红包列表
	 * @author lx
	 * @return List<MemberRedPackageVO>
	 */
	@Override
	public List<MemberRedPackageVO> findMemberPurseRedPackgeList(Map<String, Object> params) {
		return mapper.findMemberPurseRedPackgeList(params);
	}
	/**
	 * 获取提交订单可用红包列表
	 * @author lx
	 * @return List<MemberRedPackageVO>
	 */
	@Override
	public List<MemberRedPackageVO> findOrderRedPackageList(Map<String, Object> params) {
		return mapper.findOrderRedPackageList(params);
	}

	/**
	 * 使用红包
	 * @author lx
	 * @return void
	 */
	@Override
	public Float usedMyRedPackge(Map<String, Object> params) {
		@SuppressWarnings("unused")
		Integer id = null,memberId,isSplit=1;//默认不可拆分
		Float orderNum = null;//订单金额
		Float sucNum;//成功抵扣金额
		double fee = 0 ;
		if(params.get("id")!=null){
			  id= Integer.parseInt(params.get("id").toString());
		}
		if(params.get("memberId")!=null){
			 memberId = Integer.parseInt(params.get("memberId").toString());
		}
		if(params.get("orderNum")!=null){
			 orderNum = Float.parseFloat(params.get("orderNum").toString());
		}
		if(params.get("isSplit")!=null){
			 isSplit = Integer.parseInt(params.get("isSplit").toString());
		}
		if(params.get("fee")!=null){
			fee = Double.valueOf(params.get("fee").toString());
		}
		MemberRedPackage redPackage = mapper.findById(id);
		float balance= redPackage.getPrice()-redPackage.getUsedMoney();//红包可用余额
		//如果订单金额大于红包可用金额直接抵扣完红包剩余金额
		if(orderNum >= balance){
			sucNum = balance;
			mapper.usedMyRedPackge(params);
		}else{//订单金额小于红包金额
			//红包可拆分
			if(isSplit==0){
				sucNum = orderNum;
//				float curMoney = balance-orderNum;//剩余可用红包余额
				mapper.usedSplitRedPackge(params);
			}else{//不可拆分直接扣除红包可用余额
				sucNum = balance;
				mapper.usedMyRedPackge(params);
			}
		}
		if(sucNum >= fee){
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			boolean finalPayment = Boolean.valueOf(params.get("finalPayment").toString());
			int moneyState = Integer.valueOf(params.get("moneyState").toString());
			if(finalPayment){
				actiivityOrder.setMoneyState(2);
				actiivityOrder.setExpressState(0);
			}else{
				if(moneyState == 0){
					actiivityOrder.setMoneyState(1);
				}if(moneyState == 1){
					actiivityOrder.setMoneyState(2);
				}
			}
			params.put("redFee", sucNum);
			actiivityOrder.setOrderCode(params.get("orderNo").toString());
			activtyOrderMapper.updateOrderStatus(actiivityOrder);
			paymentRecordService.savePaymentRecord(params);
		}
		return sucNum;
	}
	
	/**
	 * 后台发放红包
	 * @author lx
	 * @return void
	 */
	@Override
	public String sendRedPackge(List<Map<String, Object>> params){
		String message="";
		int sucNum=0;
		MemberRedPackage bean = null;
		List<MemberRedPackage> dataList = new ArrayList<MemberRedPackage>();
		for (Map<String, Object> map : params) {
			Integer memberId = Integer.parseInt(map.get("memberId").toString());
			Integer redPackageId = Integer.parseInt(map.get("redPackageId").toString());
			Float price = Float.parseFloat(map.get("price").toString());
			Integer limitNum = Integer.parseInt(map.get("limitNum").toString());
			String phone = map.get("phone").toString();
			Integer getNum = mapper.findUserGetNum(map);
			//如果用户已领个数超出红包限领个数则不能再领取
			if(getNum>=limitNum){
				message+=memberId+"已超过限领个数,不能再次领取!/n";
			}else{
				bean=new MemberRedPackage();
				bean.setMemberId(memberId);
				bean.setRedPackageId(redPackageId);
				bean.setPrice(price);
				bean.setUsedMoney(0.0F);
				bean.setCreateTime(new Date());
				bean.setMobile(phone);
				dataList.add(bean);
				sucNum++;
			}
		}
		if(dataList.size()>0){
			mapper.batchInsertRedPackage(dataList);
			message+="发送成功!共发送红包:"+sucNum+"个。";
		}
		return message;
	}
	
}
