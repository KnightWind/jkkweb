package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;


@Table(name = "staff_complain")
public class StaffComplain {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer sid;
	@Column(name = "admin_id")
	private Integer adminId;
	private String title;
	private String content;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	private Integer level ;
	private Integer status;
	private Integer mid;
    private Integer bid;
    private Integer tid;
    private Integer type;
	private Integer source;
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
	// 级别值
		public String getLevelVal() {
			if (this.getLevel() != null) {
				if (level == 1) {
					return "低";
				}
				if (level == 2) {
					return "中";
				}
				if (level == 3) {
					return "高";
				}
				return "";
			}
			return "";
		}

		// 状态值
		public String getStatusVal() {
			if (this.getStatus() != null) {
				if (status == 1) {
					return "已解决";
				}
				if (status == 2) {
					return "待处理";
				}
				return "";
			}
			return "";
		}

		// 类型值
		public String getTypeVal() {
			if (this.getType() != null) {
				if (type == 1) {
					return "业主投诉设计师";
				}
				if (type == 2) {
					return "设计师投诉业主";
				}
				return "";
			}
			return "";
		}

		// 来源值
		public String getSourceVal() {
			if (this.getSource() != null) {
				if (source == 1) {
					return "预约时";
				}
				if (source == 2) {
					return "看工地";
				}
				if (source == 3) {
					return "施工工程";
				}
				return "";
			}
			return "";
		}

		// 创建时间
		public String getCreateTimeHandle() {
			if (this.getCreateTime() != null) {
				return DateUtil.formatDateTime(createTime);
			}
			return "";
		}

		// 更新时间
		public String getUpdateTimeHandle() {
			if (this.getUpdateTime() != null) {
				return DateUtil.formatDateTime(updateTime);
			}
			return "";
		}
}