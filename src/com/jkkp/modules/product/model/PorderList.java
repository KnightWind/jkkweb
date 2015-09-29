package com.jkkp.modules.product.model;

import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "porder_list")
public class PorderList {
	@Column(name = "tid")
	private Integer tid;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getProdNanme() {
		return prodNanme;
	}

	public void setProdNanme(String prodNanme) {
		this.prodNanme = prodNanme;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getBuyAddr() {
		return buyAddr;
	}

	public void setBuyAddr(String buyAddr) {
		this.buyAddr = buyAddr;
	}

	public String getOfflineAddr() {
		return offlineAddr;
	}

	public void setOfflineAddr(String offlineAddr) {
		this.offlineAddr = offlineAddr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getMxDesc() {
		return mxDesc;
	}

	public void setMxDesc(String mxDesc) {
		this.mxDesc = mxDesc;
	}

	public Integer getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private Integer typeId;
	@Column(name = "prod_nanme")
	private String prodNanme;
	@Column(name = "total_price")
	private Float totalPrice;
	private Integer num;
	private String spec;
	@Column(name = "buy_addr")
	private String buyAddr;
	@Column(name = "pf_id")
	private Integer pfId;
	@Column(name = "offline_addr")
	private String offlineAddr;
	private String remark;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "create_user")
	private String createUser;
	@Column(name = "mx_desc")
	private String mxDesc;
	@Column(name = "pass_flag")
	private Integer passFlag;

	public Integer getPfId() {
		return pfId;
	}

	public void setPfId(Integer pfId) {
		this.pfId = pfId;
	}

	public String getPassFlagString() {
		if (passFlag != null) {
			if (passFlag == 1)
				return "通过";
			else
				return "未通过";
		}
		return "";
	}

	public String getCreateTimeHandle() {
		if (createTime != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
}