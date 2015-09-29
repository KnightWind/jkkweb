package com.jkkp.modules.supplier.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;


@Table(name = "jtopic_reply")
public class JtopicReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String content;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "user_id")
	private Integer userId;
	private Integer storey;
	private int hcomment;
	@Column(name = "check_time")
	private Date checkTime;
	private Integer status;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setHcomment(int hcomment) {
		this.hcomment = hcomment;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	public Integer getHcomment() {
		return hcomment;
	}
	public void setHcomment(Integer hcomment) {
		this.hcomment = hcomment;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStorey() {
		return storey;
	}
	public void setStorey(Integer storey) {
		this.storey = storey;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Column(name = "create_user")
	private String createUser;
	private Integer pid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	private Integer type;
	
	
	public String getCreateTimeString(){
		if(createTime != null){
			return DateUtil.formatDateTime(createTime);
		}else{
			return "";
		}
	}
	
	// 评论用户类型
		public String getTypeVal() {
			if (this.getType() != null) {
				if (type == 1) {
					return "监理";
				}
				if (type == 2) {
					return "设计师";
				}
				if (type == 3) {
					return "商家";
				}
				if (type == 4) {
					return "工长";
				}
				if (type == 5) {
					return "客服";
				}
				return "";
			}
			return "";
		}

		// 状态值
		public String getStatusVal() {
			if (this.getStatus()!=null) {
				if (status == 1) {
					return "通过";
				}
				if (status == -1) {
					return "未通过";
				}
				return "";
			}
			return "";
		}

		public String getCreateTimeHandle() {
			if (this.getCreateTime() != null) {
				return DateUtil.formatDateTime(createTime);
			}
			return "";
		}

		public String getCheckTimeHandle() {
			if (this.getCheckTime() != null) {
				return DateUtil.formatDateTime(checkTime);
			}
			return "";
		}
}
