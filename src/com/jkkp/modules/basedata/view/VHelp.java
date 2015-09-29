package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.Help;

public class VHelp extends Help {
	private int vid;
    private String fistCata;
    private String secCata;
    private String vcontent;
    private int vstatus;
    private String pname;
    private String name;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getFistCata() {
		return fistCata;
	}
	public void setFistCata(String fistCata) {
		this.fistCata = fistCata;
	}
	public String getSecCata() {
		return secCata;
	}
	public void setSecCata(String secCata) {
		this.secCata = secCata;
	}
	public String getVcontent() {
		return vcontent;
	}
	public void setVcontent(String vcontent) {
		this.vcontent = vcontent;
	}
	public int getVstatus() {
		return vstatus;
	}
	public void setVstatus(int vstatus) {
		this.vstatus = vstatus;
	}
	
	public String getStatusHandle(){
		if(vstatus==0){
			return "隐藏";
		}else if(vstatus==-1){
			return "显示";
		}
		return "";
	}
	
	
	public String getStatusVal(){
		if(vstatus==-1){
			return "隐藏";
		}else if(vstatus==0){
			return "显示";
		}
		return "";
	}
	
	public String getContentVal(){
		if(vcontent.length()>0){
			return "有";
		}
		return "无";
	}
}
