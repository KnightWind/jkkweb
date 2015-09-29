package com.jkkp.modules.design.view;

public class VDesignPhoto {
	private Integer id;
	private Integer typeId;
	private String path;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public VDesignPhoto(Integer id, Integer typeId, String path) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.path = path;
	}
	public VDesignPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
