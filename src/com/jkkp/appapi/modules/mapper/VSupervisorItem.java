package com.jkkp.appapi.modules.mapper;

import java.util.List;

public class VSupervisorItem {

	private Integer id;
	private Integer name;
	private String status;
	private List<VSupervisorItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<VSupervisorItem> getItems() {
		return items;
	}

	public void setItems(List<VSupervisorItem> items) {
		this.items = items;
	}

}
