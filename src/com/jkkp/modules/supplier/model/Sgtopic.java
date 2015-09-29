package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "sgtopic")
public class Sgtopic {
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Float getSpace() {
		return space;
	}

	public void setSpace(Float space) {
		this.space = space;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private Float budget;
	private Float space;
	private String style;
	@Column(name = "house_type")
	private String houseType;
	private Integer sid;
	@Column(name = "create_time")
	private Date createTime;
	private Integer status;
	@Column(name = "check_time")
	private Date checkTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "close_time")
	private Date closeTime;
	private String community;
	@Column(name = "comment_count")
	private Integer commentCount;
	@Column(name = "gcd_id")
	private Integer gcdId;
	@Column(name = "browse_count")
	private Integer browseCount;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Integer getGcdId() {
		return gcdId;
	}

	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}

	// 状态值
	public String getStatusVal() {
		if (this.getStatus() != null) {
			if (status == 1) {
				return "通过";
			}
			if (status == 0) {
				return "未审核";
			}
			if (status == -1) {
				return "不通过";
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

	// 审核时间
	public String getCheckTimeHandle() {
		if (this.getCheckTime() != null) {
			return DateUtil.formatDateTime(checkTime);
		}
		return "";
	}

	// 屏蔽时间
	public String getCloseTimeHandle() {
		if (this.getCloseTime() != null) {
			return DateUtil.formatDateTime(closeTime);
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
