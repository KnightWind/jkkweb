package com.jkkp.modules.supplier.view;

public class VSimpleConditionCity {
	private Integer id;
	private Integer scId;
	private Integer regionId;
	private Integer parentId;
	private String regionName;
	
	
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScId() {
		return scId;
	}
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public VSimpleConditionCity(Integer id, Integer scId, Integer regionId,
			String regionName) {
		super();
		this.id = id;
		this.scId = scId;
		this.regionId = regionId;
		this.regionName = regionName;
	}
	public VSimpleConditionCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
