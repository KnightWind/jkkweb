package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 红包表
 * @author bruce
 * 
 */
@Table(name = "red_package")
public class RedPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 红包名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 金额 单位：元
	 */
	@Column(name = "price")
	private Float price;

	/**
	 * 适用平台：0家可可全平台；1家装；2建材
	 */
	@Column(name = "platform")
	protected Integer platform;

	/**
	 * 满多少可使用
	 */
	@Column(name = "start_money")
	private Float startMoney;

	/**
	 * 每人限领个数
	 */
	@Column(name = "limit_num")
	private Integer limitNum;

	/**
	 * 红包总个数
	 */
	@Column(name = "total_num")
	private Integer totalNum;

	/**
	 * 已经领取的个数
	 */
	@Column(name = "get_num")
	private Integer getNum;

	/**
	 * 红包是否支持拆分使用
	 */
	@Column(name = "is_split")
	protected Integer isSplit;

	/**
	 * 使用条件：0全款，1定金
	 */
	@Column(name = "use_condition")
	protected Integer useCondition;

	/**
	 * 红包可开始使用时间
	 */
	@Column(name = "begin_time")
	protected Date beginTime;

	/**
	 * 红包结束时间
	 */
	@Column(name = "end_time")
	protected Date endTime;

	/**
	 * 更新时间
	 */
	@Column(name = "udpate_time")
	private Date udpateTime;

	/**
	 * 管理员用户名
	 */
	@Column(name = "admin_name")
	private String adminName;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Float getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(Float startMoney) {
		this.startMoney = startMoney;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getGetNum() {
		return getNum;
	}

	public void setGetNum(Integer getNum) {
		this.getNum = getNum;
	}

	public Integer getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}

	public Integer getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(Integer useCondition) {
		this.useCondition = useCondition;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getUdpateTime() {
		return udpateTime;
	}

	public void setUdpateTime(Date udpateTime) {
		this.udpateTime = udpateTime;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}