package com.jkkp.modules.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "purchase_for")
public class PurchaseFor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer type;
	private String gmdname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getGmdname() {
		return gmdname;
	}

	public void setGmdname(String gmdname) {
		this.gmdname = gmdname;
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

	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "create_user")
	private String createUser;

	public String getTypeVal() {
		if (this.getType() != null) {
			if (this.type == 1) {
				return "线上";
			}
			if (this.type == 0) {
				return "线下";
			}
			return "";
		}
		return "";
	}

	//创建时间
	public String getCreateTimeHandle() {
		if (this.getCreateTime() != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
}