package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;


@Table(name = "jl_complain_details")
public class JlComplainDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer cid;
	@Column(name = "type_id")
	private Integer typeId;
	@Column(name = "user_id")
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "type_name")
	private String typeName;
	private String content;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "user_name")
	private String userName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreateTimeHandle(){
		if(createTime!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
		
}