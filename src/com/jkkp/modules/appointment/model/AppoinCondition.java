package com.jkkp.modules.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "appoin_condition")
public class AppoinCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String community;
	private Integer oldnew;
	@Column(name = "use_id")
	private Integer useId;
	@Column(name = "use_remark")
	private String useRemark;
	@Column(name = "vkey")
	private Integer vkey;
	private String area;
	private String budget;
	private Integer method;
	@Column(name = "vrange")
	private String vrange;
	private String style;
	@Column(name = "quantity_time")
	private String quantityTime;
	@Column(name = "repair_time")
	private String repairTime;
	private String refer;
	private String special;
	private Integer favorite;
	@Column(name = "vmatch")
	private Integer vmatch;
	@Column(name = "match_remark")
	private String matchRemark;
	private String expediently;
	@Column(name = "create_time")
	private Date createTime;
	private Integer aid;
	@Column(name = "called")
	private Integer called;
	@Column(name = "status")
	private Integer status;
	@Column(name = "create_user")
	private String createUser;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getCalled() {
		return called;
	}

	public void setCalled(Integer called) {
		this.called = called;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Integer getOldnew() {
		return oldnew;
	}

	public void setOldnew(Integer oldnew) {
		this.oldnew = oldnew;
	}

	public Integer getUseId() {
		return useId;
	}

	public void setUseId(Integer useId) {
		this.useId = useId;
	}

	public String getUseRemark() {
		return useRemark;
	}

	public void setUseRemark(String useRemark) {
		this.useRemark = useRemark;
	}

	public Integer getVkey() {
		return vkey;
	}

	public void setVkey(Integer vkey) {
		this.vkey = vkey;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public String getVrange() {
		return vrange;
	}

	public void setVrange(String vrange) {
		this.vrange = vrange;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getQuantityTime() {
		return quantityTime;
	}

	public void setQuantityTime(String quantityTime) {
		this.quantityTime = quantityTime;
	}

	public String getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getVmatch() {
		return vmatch;
	}

	public void setVmatch(Integer vmatch) {
		this.vmatch = vmatch;
	}

	public String getMatchRemark() {
		return matchRemark;
	}

	public void setMatchRemark(String matchRemark) {
		this.matchRemark = matchRemark;
	}

	public String getExpediently() {
		return expediently;
	}

	public void setExpediently(String expediently) {
		this.expediently = expediently;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getCalledVal() {
		if (this.getCalled() != null) {
			if (called == 1) {
				return "是";
			}
			if (called == 0) {
				return "否";
			}
		}
		return "";
	}

	public String getStatusVal() {
		if (this.getStatus() != null) {
			if (status == 1) {
				return "未派出单";
			}
			if (status == 2) {
				return "追踪单";
			}
			if (status == 3) {
				return "成交单";
			}
			if (status == 4) {
				return "废单";
			}
		}
		return "";
	}
}
