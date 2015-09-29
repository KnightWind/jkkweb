package com.jkkp.pc.decorate.view;

import java.util.ArrayList;
import java.util.List;

import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.design.model.DesignCate;

public class VDecorate extends DesignCate {

	private Integer id;
	private Float space;
	private Float money;
	private String houseType;
	private String style;
	private String community;
	private String cover;
	private Integer pid;
	
	List<VAttachment> attList = new ArrayList<VAttachment>();;
	
	/**
	 * 案例图片
	 */
	private String path;
	
	
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public List<VAttachment> getAttList() {
		return attList;
	}
	public void setAttList(List<VAttachment> attList) {
		this.attList = attList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getSpace() {
		return space;
	}
	public void setSpace(Float space) {
		this.space = space;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
