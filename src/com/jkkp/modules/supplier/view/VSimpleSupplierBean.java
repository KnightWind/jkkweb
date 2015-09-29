package com.jkkp.modules.supplier.view;

public class VSimpleSupplierBean {
	
	private Integer id;
	private String spName;
	private Integer count;
	private String	legalPerson;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLegalPerson() {
		if(legalPerson == null)
			return "";
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public VSimpleSupplierBean(Integer id, String spName, Integer count,
			String legalPerson) {
		super();
		this.id = id;
		this.spName = spName;
		this.count = count;
		this.legalPerson = legalPerson;
	}
	public VSimpleSupplierBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
