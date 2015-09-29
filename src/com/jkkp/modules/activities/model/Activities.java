package com.jkkp.modules.activities.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="activities")
public class Activities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String keyword;
	@Column(name="start_time")
	protected Date startTime;
	@Column(name="end_time")
	protected Date endTime;
	@Column(name="display_num")
	protected Integer displayNum;
	private Integer total;
	@Column(name="part_num")
	private Integer partNum;
	@Column(name="max_num")
	private Integer maxNum;
	
	@Column(name="lottery_type")
	private Integer lotteryType; //中奖类型：1 必定中奖,如：每抽奖100次必有20人中奖 ;2 概率中奖，由奖项的概率定，概率抽奖,每个奖项每次中奖概率一致
	@Column(name="times")
	private Integer times; //每抽奖次数
	@Column(name="lucky_times")
	private Integer luckyTimes;

	private String description;
	@Column(name="create_time")
	protected Date createTime;
	
	/**是抽奖活动否在有效内*/
	public boolean isEnable() {
		Date now = new Date();
		if(startTime != null && startTime.after(now)) 
			return false;
		if(endTime != null && endTime.before(now)) 
			return false;
		return true;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDisplayNum() {
		return displayNum;
	}
	public void setDisplayNum(Integer displayNum) {
		this.displayNum = displayNum;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPartNum() {
		return partNum;
	}
	public void setPartNum(Integer partNum) {
		this.partNum = partNum;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getLotteryType() {
		return lotteryType;
	}
	public void setLotteryType(Integer lotteryType) {
		this.lotteryType = lotteryType;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Integer getLuckyTimes() {
		return luckyTimes;
	}
	public void setLuckyTimes(Integer luckyTimes) {
		this.luckyTimes = luckyTimes;
	}
	
	public Activities(Integer id, String name, String keyword, Date startTime,
			Date endTime, Integer displayNum, Integer total, Integer partNum,
			Integer maxNum, String description) {
		super();
		this.id = id;
		this.name = name;
		this.keyword = keyword;
		this.startTime = startTime;
		this.endTime = endTime;
		this.displayNum = displayNum;
		this.total = total;
		this.partNum = partNum;
		this.maxNum = maxNum;
		this.description = description;
	}
	public Activities() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
