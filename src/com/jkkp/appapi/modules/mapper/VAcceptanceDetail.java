package com.jkkp.appapi.modules.mapper;

import java.util.List;

public class VAcceptanceDetail {

	private Integer id;
	private String title;
	private String content;
	private List<VAcceptanceDetail> children;
	private List<VAcceptanceItem> items;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<VAcceptanceDetail> getChildren() {
		return children;
	}

	public void setChildren(List<VAcceptanceDetail> children) {
		this.children = children;
	}

	public List<VAcceptanceItem> getItems() {
		return items;
	}

	public void setItems(List<VAcceptanceItem> items) {
		this.items = items;
	}

}
