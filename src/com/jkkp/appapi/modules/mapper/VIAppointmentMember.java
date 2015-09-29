package com.jkkp.appapi.modules.mapper;

public class VIAppointmentMember {
	
	private String appointmentId;

	private String appointmentCreateTime;
	
	private String appointmentStatus;
	
	private String appointmentCommunity;
	
	private String suppName;
	
	
	private String jlname;
	private Integer engid;
	private Integer jlappid;
	private Integer jlid;
	private Integer jlappstatus;
	private Integer engstage;
	private Integer distype;
	
	public Integer getDistype() {
		return distype;
	}

	public void setDistype(Integer distype) {
		this.distype = distype;
	}

	public Integer getEngstage() {
		return engstage;
	}

	public void setEngstage(Integer engstage) {
		this.engstage = engstage;
	}

	public String getJlname() {
		return jlname;
	}

	public void setJlname(String jlname) {
		this.jlname = jlname;
	}

	public Integer getEngid() {
		return engid;
	}

	public void setEngid(Integer engid) {
		this.engid = engid;
	}

	public Integer getJlappid() {
		return jlappid;
	}

	public void setJlappid(Integer jlappid) {
		this.jlappid = jlappid;
	}

	public Integer getJlid() {
		return jlid;
	}

	public void setJlid(Integer jlid) {
		this.jlid = jlid;
	}

	public Integer getJlappstatus() {
		return jlappstatus;
	}

	public void setJlappstatus(Integer jlappstatus) {
		this.jlappstatus = jlappstatus;
	}

	public String getAppointmentCommunity(){
		return appointmentCommunity;
	}
	
	public void setAppointmentCommunity(String appointmentCommunity){
		this.appointmentCommunity=appointmentCommunity;
	}
	
	public String getSuppName(){
		return suppName;
	}
	
	public void setSuppName(String suppName){
		this.suppName=suppName;
	}
	
	
	
	
	public String getAppointmentId(){
		return appointmentId;
	}
	public String getAppointmentCreateTime(){
		return appointmentCreateTime;
	}
	public String getAppointmentStatus(){
		return appointmentStatus;
	}
	
	public void setAppointmentId(String appointmentId){
		this.appointmentId=appointmentId;
	}
	
	
	public void setAppointmentStatus(String appointmentStatus){
		this.appointmentStatus=appointmentStatus;
	}
	
	public void setAppointmentCreateTime(String appointmentCreateTime){
		this.appointmentCreateTime=appointmentCreateTime;
	}
	
	
	
}
