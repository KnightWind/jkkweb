package com.jkkp.appapi.modules.mapper;

public class VIEngineerings {
	private String address="";
	private String create_time="";
	private String singn_time="";
	private String start_time="";
	private String end_time="";
	private String engineerId="";
	
	public String getAddress(){
		return address;
	}
	
	public String getCreate_time(){
		return create_time;
	}
	public String getSingn_time(){
		return singn_time;
	}
	public String getStart_time(){
		return start_time;
	}
	public String getEnd_time(){
		return end_time;
	}
	public String getEngineerId(){
		return engineerId;
	}
	
	public void  setAddress(String address){
		this.address=address;
	}
	public void  setCreate_time(String create_time){
		this.create_time=create_time;
	}
	public void  setSingn_time(String singn_time){
		this.singn_time=singn_time;
	}
	public void  setStart_time(String start_time){
		this.start_time=start_time;
	}
	public void  setEnd_time(String end_time){
		this.end_time=end_time;
	}
	public void  setEngineerId(String engineerId){
		this.engineerId=engineerId;
	}

}
