package com.jkkp.modules.basedata.view;

public class VExpenditure {

	private Integer id;
	private String rphone;
	private String rname;
	private String spName;
	private String departStore;
	private String departPerson;
	private Integer orderCount;
	private Double orderMoney;
	private Float pushMoney;
	private Double pushMoneyCount;
	private Double unCalePushMoney;
	public Integer role;
	
	public String getRoleString(){
		if(role != null){
			if(role == 1){
				return "员工";
			}else if(role == 0){
				return "业主";
			}
		}
		return "";
	}
	
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getDepartStore() {
		return departStore;
	}
	public void setDepartStore(String departStore) {
		this.departStore = departStore;
	}
	public String getDepartPerson() {
		return departPerson;
	}
	public void setDepartPerson(String departPerson) {
		this.departPerson = departPerson;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Float getPushMoney() {
		return pushMoney;
	}
	public void setPushMoney(Float pushMoney) {
		this.pushMoney = pushMoney;
	}
	public Double getPushMoneyCount() {
		return pushMoneyCount;
	}
	public void setPushMoneyCount(Double pushMoneyCount) {
		this.pushMoneyCount = pushMoneyCount;
	}
	public Double getUnCalePushMoney() {
		return unCalePushMoney;
	}
	public void setUnCalePushMoney(Double unCalePushMoney) {
		this.unCalePushMoney = unCalePushMoney;
	}
	
	
	
	
}
