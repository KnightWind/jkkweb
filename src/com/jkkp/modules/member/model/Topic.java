package com.jkkp.modules.member.model;

import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "topic")
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@Column(name = "gcd_id")
	public Integer gcdId;
	

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Integer getGcdId() {
		return gcdId;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
    public String  content;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 日记标题
	 */
	public String subject;

	/**
	 * 预算，单位（万）
	 */
	public Integer budget;

	/**
	 * 装修面积，单位（平米）
	 */
	public Integer space;

	/**
	 * 装修风格：1.中式，2.美式，3.欧式，4.日式，5.田园，6.现代
	 */
	public Byte style;

	/**
	 * 户型，1：一居，2：两居，3.三居，4.四居，5.复式，6.别墅
	 */
	@Column(name = "house_type")
	public Byte houseType;

	/**
	 * 用户id
	 */
	public Integer uid;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	public Date createTime;

	/**
	 * 所在城市域名
	 */
	@Column(name = "city_domain")
	public String cityDomain;

	/**
	 * 0未审核1审核通过（显示）-1审核失败（屏蔽）
	 */
	public Byte status;

	/**
	 * 审核时间
	 */
	@Column(name = "check_time")
	public Date checkTime;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	public Date updateTime;

	/**
	 * 屏蔽时间
	 */
	@Column(name = "close_time")
	public Date closeTime;

	/**
	 * 小区名称
	 */
	public String community;

	/**
	 * 工长名字
	 */
	public String forman;
	/**
	 * 发布时间
	 */
	@Column(name = "pub_time")
	public Date pubTime;
	/**
	 * 评论次数
	 */
	@Column(name = "comment_count")
	public Integer commentCount;
	
	@Column(name = "browse_count")
	public Integer  browseCount;
	
	

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取日记标题
	 * 
	 * @return subject - 日记标题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置日记标题
	 * 
	 * @param subject
	 *            日记标题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取预算，单位（万）
	 * 
	 * @return budget - 预算，单位（万）
	 */
	public Integer getBudget() {
		return budget;
	}

	/**
	 * 设置预算，单位（万）
	 * 
	 * @param budget
	 *            预算，单位（万）
	 */
	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	/**
	 * 获取装修面积，单位（平米）
	 * 
	 * @return space - 装修面积，单位（平米）
	 */
	public Integer getSpace() {
		return space;
	}

	/**
	 * 设置装修面积，单位（平米）
	 * 
	 * @param space
	 *            装修面积，单位（平米）
	 */
	public void setSpace(Integer space) {
		this.space = space;
	}

	/**
	 * 获取装修风格：1.中式，2.美式，3.欧式，4.日式，5.田园，6.现代
	 * 
	 * @return style - 装修风格：1.中式，2.美式，3.欧式，4.日式，5.田园，6.现代
	 */
	public Byte getStyle() {
		return style;
	}

	/**
	 * 设置装修风格：1.中式，2.美式，3.欧式，4.日式，5.田园，6.现代
	 * 
	 * @param style
	 *            装修风格：1.中式，2.美式，3.欧式，4.日式，5.田园，6.现代
	 */
	public void setStyle(Byte style) {
		this.style = style;
	}

	/**
	 * 获取户型，1：一居，2：两居，3.三居，4.四居，5.复式，6.别墅
	 * 
	 * @return house_type - 户型，1：一居，2：两居，3.三居，4.四居，5.复式，6.别墅
	 */
	public Byte getHouseType() {
		return houseType;
	}

	/**
	 * 设置户型，1：一居，2：两居，3.三居，4.四居，5.复式，6.别墅
	 * 
	 * @param houseType
	 *            户型，1：一居，2：两居，3.三居，4.四居，5.复式，6.别墅
	 */
	public void setHouseType(Byte houseType) {
		this.houseType = houseType;
	}

	/**
	 * 获取用户id
	 * 
	 * @return uid - 用户id
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * 设置用户id
	 * 
	 * @param uid
	 *            用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取所在城市域名
	 * 
	 * @return city_domain - 所在城市域名
	 */
	public String getCityDomain() {
		return cityDomain;
	}

	/**
	 * 设置所在城市域名
	 * 
	 * @param cityDomain
	 *            所在城市域名
	 */
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
	 * 获取0未审核1审核通过（显示）-1审核失败（屏蔽）
	 * 
	 * @return status - 0未审核1审核通过（显示）-1审核失败（屏蔽）
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置0未审核1审核通过（显示）-1审核失败（屏蔽）
	 * 
	 * @param status
	 *            0未审核1审核通过（显示）-1审核失败（屏蔽）
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * 获取审核时间
	 * 
	 * @return check_time - 审核时间
	 */
	public Date getCheckTime() {
		return checkTime;
	}

	/**
	 * 设置审核时间
	 * 
	 * @param checkTime
	 *            审核时间
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取屏蔽时间
	 * 
	 * @return close_time - 屏蔽时间
	 */
	public Date getCloseTime() {
		return closeTime;
	}

	/**
	 * 设置屏蔽时间
	 * 
	 * @param closeTime
	 *            屏蔽时间
	 */
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 * 获取小区名称
	 * 
	 * @return community - 小区名称
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * 设置小区名称
	 * 
	 * @param community
	 *            小区名称
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
    private String type;
    private Integer aidorgcdid;
    @Column(name = "stage_id")
    private Integer stageId;
	public Integer getAidorgcdid() {
		return aidorgcdid;
	}

	public void setAidorgcdid(Integer aidorgcdid) {
		this.aidorgcdid = aidorgcdid;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	/**
	 * 获取工长名字
	 * 
	 * @return forman - 工长名字
	 */
	public String getForman() {
		return forman;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 设置工长名字
	 * 
	 * @param forman
	 *            工长名字
	 */
	public void setForman(String forman) {
		this.forman = forman;
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
			return DateUtil.formatDateTime(createTime);
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

	 //更新时间
	public String getUpdateTimeHandle(){
		if(this.getUpdateTime()!=null){
			return DateUtil.formatDateTime(updateTime);
		}
		return "";
	}
	//发布时间
	public String getPubTimeHandle(){
		if(this.getPubTime()!=null){
			return DateUtil.formatDateTime(pubTime);
		}
		return "";
	}

}