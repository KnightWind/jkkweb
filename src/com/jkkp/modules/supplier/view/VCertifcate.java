package com.jkkp.modules.supplier.view;

public class VCertifcate {
	
	//证书类型与商家关联表
	private Integer id;
	//图片路径
	private String path;
	//证书类型
	private String type;
	//证书类型id
	private Integer typeId;
	
	
	
	public VCertifcate(Integer id, String path, String type, Integer typeId) {
		super();
		this.id = id;
		this.path = path;
		this.type = type;
		this.typeId = typeId;
	}
	public VCertifcate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
