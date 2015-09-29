package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "jtopic")
public class Jtopic {
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

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
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
	private Integer style;
	@Column(name = "house_type")
	private Integer houseType;
	@Column(name = "sp_id")
	private Integer spId;
	@Column(name = "gcd_id")
	private Integer gcdId;

	public Integer getGcdId() {
		return gcdId;
	}

	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}

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
	private String content;
	@Column(name = "stage_id")
	private Integer stageId;
	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	@Column(name = "browse_count")
	private Integer browseCount;

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	@Column(name = "city_domain")
	private String cityDomain;

	public String getCityDomain() {
		return cityDomain;
	}

	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTimeHandle() {
		if (createTime != null)
			return DateUtil.formatDateTime(createTime);
		else
			return "";
	}

	public String getCheckTimeHandle() {
		if (checkTime != null)
			return DateUtil.formatDateTime(checkTime);
		else
			return "";
	}

	public String getCloseTimeHandle() {
		if (closeTime != null)
			return DateUtil.formatDateTime(closeTime);
		else
			return "";
	}

	public String getUpdateTimeHandle() {
		if (updateTime != null)
			return DateUtil.formatDateTime(updateTime);
		else
			return "";
	}

	public String getStatusVal() {
		if (this.getStatus() != null) {
			if (status == 0)
				return "未审核";
			if (status == 1)
				return "通过";
			if (status == -1)
				return "未通过";
			return "";
		}
		return "";
	}

}
