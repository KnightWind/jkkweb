package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 邮递dto
 * 
 * @author xiaozhenyu
 *
 */
@Table( name = "zc_express")
public class ActivityExpress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String expressCode; //快递编号

	private String expressName; //快递共色

	private Date createDate; //创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
