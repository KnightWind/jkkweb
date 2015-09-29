package com.jkkp.modules.basedata.view;

import java.util.ArrayList;
import java.util.List;

import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.modules.supplier.view.VJlComplain;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.modules.system.view.VComplain;

public class VEngineerings extends Engineerings {
	public String spName;						//公司名称
	public String jlName;						//监理姓名
	public String nickName;						//业主姓名
	public String designerName;					//设计师姓名
	public String cateName;						//装修风格
	
	public VAppointment appointment = null;		//工地信息
	public VComplain complain = null;			//业主投诉详情
	public VSupplier jlSupplier = null;			//监理详情
	public VSupplier spSupplier = null;			//装修公司
	public List<VJlComplain> jlcomplainList=new ArrayList<VJlComplain>();//监理投诉列表
	public VDesign design=null;					//装修方案

	public String huxing;						//户型
	public String fengge;						//风格
	public String baojia;						//报价
	public String mianji;						//面积
	public String address;						//地址详情  
	public String method2,method2Val;			//半包全包1半包或2全包
	
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMethod2() {
		return method2;
	}

	public void setMethod2(String method2) {
		this.method2 = method2;
	}

	public String getMethod2Val() {
		if("1".equals(method2)) method2Val="半包";
		else if("2".equals(method2)) method2Val="全包";
		else method2Val=method2==null?"":method2.trim();
		return method2Val;
	}

	public void setMethod2Val(String method2Val) {
		this.method2Val = method2Val;
	}

	public String getMianji() {
		return mianji;
	}

	public void setMianji(String mianji) {
		this.mianji = mianji;
	}

	public String getHuxing() {
		return huxing;
	}

	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}

	public String getFengge() {
		return fengge;
	}

	public void setFengge(String fengge) {
		this.fengge = fengge;
	}

	public String getBaojia() {
		return baojia;
	}

	public void setBaojia(String baojia) {
		this.baojia = baojia;
	}

	public VDesign getDesign() {
		return design;
	}

	public void setDesign(VDesign design) {
		this.design = design;
	}

	public VSupplier getJlSupplier() {
		return jlSupplier;
	}

	public void setJlSupplier(VSupplier jlSupplier) {
		this.jlSupplier = jlSupplier;
	}

	public VSupplier getSpSupplier() {
		return spSupplier;
	}

	public void setSpSupplier(VSupplier spSupplier) {
		this.spSupplier = spSupplier;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<VJlComplain> getJlcomplainList() {
		return jlcomplainList;
	}

	public void setJlcomplainList(List<VJlComplain> jlcomplainList) {
		this.jlcomplainList = jlcomplainList;
	}

	public VComplain getComplain() {
		return complain;
	}

	public void setComplain(VComplain complain) {
		this.complain = complain;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getJlName() {
		return jlName;
	}

	public void setJlName(String jlName) {
		this.jlName = jlName;
	}


	public VAppointment getAppointment() {
		return appointment;
	}

	public void setAppointment(VAppointment appointment) {
		this.appointment = appointment;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}
}
