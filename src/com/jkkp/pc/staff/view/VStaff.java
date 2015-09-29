package com.jkkp.pc.staff.view;

public class VStaff {

	private Integer id;
	private String stylistName;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 头像路径
	 */
	private String path;
	/**
	 * 评分
	 */
	private Float grade;
	
	
	
	public Float getGrade() {
		return grade;
	}
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStylistName() {
		return stylistName;
	}
	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
}
