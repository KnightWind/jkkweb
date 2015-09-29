package com.jkkp.appapi.modules.mapper;


import java.util.Date;


import com.jkkp.modules.appointment.model.Appointment;

public class VIAppointment{
	private Integer appointmentid;
	private Integer subType;
	private Date createTime;
	private Integer status;
	private String content;
	private float space;
	private Integer reviewNum;
	private String user;
	private String mobile;
	private String address;
	private Integer getRoom;
	private float budget;
	private Date getRoomDate;
	private Integer method;
	private Integer source;
	private Integer adminId;
	private String cityDomain;
	private Date linkTime;
	private String company;
	private String reason;
	private Date reviewTime;
	private String community;
	private Integer parlourGround;
	private Integer parlourGroundDismantle;
	private Integer parlourWall;
	private Integer parlourWallDismantle;
	private Integer bedroomGround;
	private Integer bedroomGroundDismantle;
	private Integer bedroomWall;
	private Integer bedroomWallDismantle;
	private Integer kitchen;
	private Integer kitchenDismantle;
	private Integer toilet;
	private Integer toiletDismantle;
	private Integer water;
	private Integer electric;
	private Date startWork;
	private Integer htBedroom;
	private Integer htLivingRoom;
	private Integer htKitchen;
	private Integer htToilet;
	private Integer selectPushId;
	private double payment;
	private Date payTime;
	private String tradeNo;
	private Integer payType;
	private String bankGateway;
	private Date finishTime;
	private Date enableTime;

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	public String getCityDomain() {
		return cityDomain;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCommunity() {
		return community;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setBankGateway(String bankGateway) {
		this.bankGateway = bankGateway;
	}

	public String getBankGateway() {
		return bankGateway;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setGetRoomDate(Date getRoomDate) {
		this.getRoomDate = getRoomDate;
	}

	public Date getGetRoomDate() {
		return getRoomDate;
	}

	public void setLinkTime(Date linkTime) {
		this.linkTime = linkTime;
	}

	public Date getLinkTime() {
		return linkTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setStartWork(Date startWork) {
		this.startWork = startWork;
	}

	public Date getStartWork() {
		return startWork;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	public Date getEnableTime() {
		return enableTime;
	}
	
	public void setPayment(double payment){
		this.payment=payment;
	}

	public double getPayment(){
		return payment;
	}
	
	public void setToiletDismantle(Integer toiletDismantle){
		this.toiletDismantle=toiletDismantle;
	}

	public Integer getToiletDismantle(){
		return toiletDismantle;
	}

	public void setToilet(Integer toilet){
		this.toilet=toilet;
	}

	public Integer getToilet(){
		return toilet;
	}

	public void setKitchenDismantle(Integer kitchenDismantle){
		this.kitchenDismantle=kitchenDismantle;
	}

	public Integer getKitchenDismantle(){
		return kitchenDismantle;
	}
	public void setHtBedroom(Integer htBedroom){
		this.htBedroom=htBedroom;
	}

	public Integer getHtBedroom(){
		return htBedroom;
	}

	public void setElectric(Integer electric){
		this.electric=electric;
	}

	public Integer getElectric(){
		return electric;
	}

	public void setWater(Integer water){
		this.water=water;
	}

	public Integer getWater(){
		return water;
	}
	public void setAppointmentid(Integer appointmentid){
		this.appointmentid=appointmentid;
	}

	public Integer getAppointmentid(){
		return appointmentid;
	}

	public Integer getSubType(){
		return subType;
	}
	public void setSubType(Integer subType){
		this.subType=subType;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}

	public void setSpace(float space){
		this.space=space;
	}

	public float getSpace(){
		return space;
	}
	public void setReviewNum(Integer reviewNum){
		this.reviewNum=reviewNum;
	}

	public Integer getReviewNum(){
		return reviewNum;
	}

	 public void setGetRoom(Integer getRoom){
		this.getRoom=getRoom;
	}

	public Integer getGetRoom(){
		return getRoom;
	}         
	                
	 public void setBudget(float budget){
		this.budget=budget;
	}

	public float getBudget(){
		return budget;
	}                    
	 public void setMethod(Integer method){
		this.method=method;
	}

	public Integer getMethod(){
		return method;
	}                     
	 public void setSource(Integer source){
		this.source=source;
	}

	public Integer getSource(){
		return source;
	}                
	                
	 public void set(Integer adminId){
		this.adminId=adminId;
	}

	public Integer getAdminId(){
		return adminId;
	}                 
	 public void setParlourGround(Integer parlourGround){
		this.parlourGround=parlourGround;
	}

	public Integer getParlourGround( ){
		return parlourGround;
	}                 
	 public void setParlourGroundDismantle(Integer parlourGroundDismantle){
		this.parlourGroundDismantle=parlourGroundDismantle;
	}

	public Integer getParlourGroundDismantle(){
		return parlourGroundDismantle;
	} 
	                 


	public Integer getParlourWall(){
		return parlourWall;
	} 
	   public void setParlourWall(Integer parlourWall){
		this.parlourWall=parlourWall;
	}

	public Integer getParlourWallDismantle(){
		return parlourWallDismantle;
	} 
	   public void setParlourWallDismantle(Integer parlourWallDismantle){
		this.parlourWallDismantle=parlourWallDismantle;
	}


	 public void setBedroomGround(Integer bedroomGround){
		this.bedroomGround=bedroomGround;
	}

	public Integer getBedroomGround(){
		return bedroomGround;
	} 
	                  
	 
	  public void setBedroomGroundDismantle(Integer bedroomGroundDismantle){
		this.bedroomGroundDismantle=bedroomGroundDismantle;
	}

	public Integer getBedroomGroundDismantle(){
		return bedroomGroundDismantle;
	} 

	 public void setBedroomWall(Integer bedroomWall){
		this.bedroomWall=bedroomWall;
	}

	public Integer getBedroomWall(){
		return bedroomWall;
	}          
	 public void setBedroomWallDismantle(Integer bedroomWallDismantle){
		this.bedroomWallDismantle=bedroomWallDismantle;
	}

	public Integer getBedroomWallDismantle(){
		return bedroomWallDismantle;
	}          

	public void setPayType(Integer payType){
		this.payType=payType;
	}

	public Integer getPayType(){
		return payType;
	}

	public void setSelectPushId(Integer selectPushId){
		this.selectPushId=selectPushId;
	}

	public Integer getSelectPushId(){
		return selectPushId;
	}

	public void setHtToilet(Integer htToilet){
		this.htToilet=htToilet;
	}

	public Integer getHtToilet(){
		return htToilet;
	}

	public void setHtKitchen(Integer htKitchen){
		this.htKitchen=htKitchen;
	}

	public Integer getHtKitchen(){
		return htKitchen;
	}

	public void setKitchen(Integer kitchen){
		this.kitchen=kitchen;
	}

	public Integer getKitchen(){
		return kitchen;
	}
	public void setHtLivingRoom(Integer htLivingRoom){
		this.htLivingRoom=htLivingRoom;
	}

	public Integer getHtLivingRoom(){
		return htLivingRoom;
	}
	       
	    

}
