package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.SupplierCompanyStaff;

public class VSupplierCompanyStaff extends SupplierCompanyStaff{
    
	public String photoUrl;		//设计师头像
	public Integer jds;			//接单数
	public Integer scs;			//收藏数
	public String spName;		//公司名称
    
	private String leaderName;//主管名称
	private String branchName;//分店名称
	private String bankName;//员工银行卡所在银行
	
	//结算相关的字段
	private Double jsMoneyTotal;//已结算总额
	private Double amountTotal;//总交易额
	private Integer ddCount;//总交易订单数
	
	//计算可获得的提成总额（总交易额*员工分成比例）
	public Double getTiChengTotal(){
		return (amountTotal==null?0.0:amountTotal)*(gainRate==null?0:gainRate);
	}
	
	//计算未结算的总提成
	public Double getDaiJieSuanTotal(){
		return (amountTotal==null?0.0:amountTotal)*(gainRate==null?0:gainRate)-(jsMoneyTotal==null?0:jsMoneyTotal);
	}
	public Double getJsMoneyTotal() {
		return jsMoneyTotal;
	}

	public void setJsMoneyTotal(Double jsMoneyTotal) {
		this.jsMoneyTotal = jsMoneyTotal;
	}

	public Double getAmountTotal() {
		return amountTotal==null?0.0:amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Integer getDdCount() {
		return ddCount==null?0:ddCount;
	}

	public void setDdCount(Integer ddCount) {
		this.ddCount = ddCount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public Integer getJds() {
		return jds;
	}

	public void setJds(Integer jds) {
		this.jds = jds;
	}

	public Integer getScs() {
		return scs;
	}

	public void setScs(Integer scs) {
		this.scs = scs;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	//审核状态
	public String getStatusVal(){
		if(super.getStatus()!=null){
			if(status==0){
				return "待审核";
			}
			if(status==1){
				return "通过";
			}
			if(status==2){
				return "未通过";
			}
		}
		return "待审核";
	}
	
}
