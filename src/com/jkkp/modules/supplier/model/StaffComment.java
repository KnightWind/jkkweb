package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;


@Table(name = "staff_comment")
public class StaffComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public Integer uid;
	public String content;
	public Integer status;
	public Integer type;
	public  Integer sid;
	public Float level;
	public String uname;
	public Integer pid;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Float getLevel() {
		return level;
	}
	public void setLevel(Float level) {
		this.level = level;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	@Column(name = "create_time")
	public Date createTime;
	@Column(name = "close_time")
	public Date closeTime;
	@Column(name = "check_time")
	public Date checkTime;
	
	
	public String getCreateTimeString(){
		if(this.createTime != null)
			return DateUtil.formatDateTime(createTime);
		return "";
	}
	
	public String getCloseTimeString(){
		if(this.closeTime != null)
			return DateUtil.formatDateTime(closeTime);
		return "";
	}
	
	public String getCheckTimeString(){
		if(this.checkTime != null)
			return DateUtil.formatDateTime(checkTime);
		return "";
	}
	
	public String getShortContent(){
		if(this.content != null){
			if(content.length() > 15)
				return content.substring(0, 14) + "...";
			else{
				return content;
			}
		}
		return "";
	}
	
	public String getUnameString(){
		if(this.uname != null)
			if(uname == "")
				return "匿名用户";
			else
				return uname;
		return "";
	}
	
	
	public String getStatusString(){
		if(status == 0)
			return "屏蔽";
		else
			return "发布";
	}
	
	
	public String getUserType(){
		switch(type){
		case 1:
			return "商家";
		case 2:
			return "监理";
		case 3:
			return "工长";
		case 4:
			return "设计师";
		case 5:
			return "客服";
		case 6:
			return "业主";
		default:
			return "未知";
		}
	}
	
	
}