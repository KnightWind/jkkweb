package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.utils.DateUtil;

public class VRedPackage extends RedPackage {

	/**
	 * 已发出去的红包个数
	 */
	private Integer sendCount;
	/**
	 * 已发出红包金额
	 */
	private Double sendMoneyCount;
	/**
	 * 已使用红包金额
	 */
	private Double usedMoneyCount;
	
	
	
	public String getUseConditionString(){
		if(useCondition != null){
			switch (useCondition) {
			case 0:
				return "全款";
			case 1:
				return "定金";
			default:
				return "未知";
			}
		}
		return "未知";
	}
	
	
	public String getIsSplitString(){
		if(isSplit != null){
			switch (isSplit) {
			case 0:
				return "否";
			case 1:
				return "是";
			default:
				return "未知";
			}
		}
		return "未知";
	}
	
	public String getPlatformString(){
		if(platform != null){
			switch (platform) {
			case 0:
				return "家可可全平台";
			case 1:
				return "家装";
			case 2:
				return "建材";
			default:
				return "未知";
			}
		}
		return "未知";
	}
	
	
	public String getBeginTimeString(){
		return DateUtil.formatDate(beginTime);
	}
	
	public String getEndTimeString(){
		return DateUtil.formatDate(endTime);
	}
	
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
	public Double getSendMoneyCount() {
		return sendMoneyCount;
	}
	public void setSendMoneyCount(Double sendMoneyCount) {
		this.sendMoneyCount = sendMoneyCount;
	}
	public Double getUsedMoneyCount() {
		return usedMoneyCount;
	}
	public void setUsedMoneyCount(Double usedMoneyCount) {
		this.usedMoneyCount = usedMoneyCount;
	}
	
}
