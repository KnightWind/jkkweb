package com.jkkp.modules.supplier.view;

import java.util.Date;

import com.jkkp.modules.supplier.model.QuanO2o;
import com.jkkp.utils.DateUtil;

public class VQuanO2o extends QuanO2o {
	public String city;
	public Integer no;
	public String type;
	public String name;
	public Date start;
	public Date end;
	public String xiajia;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getXiajia() {
		return xiajia;
	}
	public void setXiajia(String xiajia) {
		this.xiajia = xiajia;
	}
	public Date getCre() {
		return cre;
	}
	
	public void setCre(Date cre) {
		this.cre = cre;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getShangjia() {
		return shangjia;
	}
	public void setShangjia(Integer shangjia) {
		this.shangjia = shangjia;
	}
	public Date cre;
	public Integer qid;
	public Integer shangjia;
	public String getCr(){
		return cre== null ? "" : DateUtil.formatDateTime(cre);
	}
	public String getEn(){
		return end== null ? "" : DateUtil.formatDateTime(end);
	}
	public String getSt(){
		return start== null ? "" : DateUtil.formatDateTime(start);
	}
	public double par;
	public double price;
	public String zt;
	public double getPar() {
		return par;
	}
	public void setPar(double par) {
		this.par = par;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
}
