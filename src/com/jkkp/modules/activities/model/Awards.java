package com.jkkp.modules.activities.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "awards")
public class Awards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer rank; //奖品等级  1代表一等奖,2代表二等奖,以此类推
	@Column(name = "prize_name")
	private String prizeName;
	@Column(name = "prize_num")
	private Integer prizeNum;
	private Float probability;
	@Column(name = "create_time")
	protected Date creatTime;
	@Column(name = "active_id")
	private Integer activeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public Integer getPrizeNum() {
		return prizeNum;
	}

	public void setPrizeNum(Integer prizeNum) {
		this.prizeNum = prizeNum;
	}

	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Integer getActiveId() {
		return activeId;
	}

	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}

	public Awards(Integer id, Integer rank, String prizeName, Integer prizeNum, Float probability, Date creatTime,
			Integer activeId) {
		super();
		this.id = id;
		this.rank = rank;
		this.prizeName = prizeName;
		this.prizeNum = prizeNum;
		this.probability = probability;
		this.creatTime = creatTime;
		this.activeId = activeId;
	}

	public Awards() {
		super();
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
