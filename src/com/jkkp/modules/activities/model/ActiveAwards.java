package com.jkkp.modules.activities.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="active_awards")
public class ActiveAwards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="active_id")
	private Integer activeId;
	@Column(name="awards_id")
	private Integer awardsId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActiveId() {
		return activeId;
	}
	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}
	public Integer getAwardsId() {
		return awardsId;
	}
	public void setAwardsId(Integer awardsId) {
		this.awardsId = awardsId;
	}
	public ActiveAwards(Integer id, Integer activeId, Integer awardsId) {
		super();
		this.id = id;
		this.activeId = activeId;
		this.awardsId = awardsId;
	}
	public ActiveAwards() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
