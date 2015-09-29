package com.jkkp.pc.apply.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ecshop_homepage_apply")
public class EcshopHomepageApply{


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		/**
   		 *
		 */
		@Column(name="member_name")
		private String memberName;

		/**
   		 *
		 */
		@Column(name="member_mobile")
		private String memberMobile;

		/**
   		 *(10.监理预约，20.估算报价，30.装修)
		 */
		@Column(name="apply_type")
		private Integer applyType;

		/**
   		 *(10.质量套餐，20.管家套餐，30.尊享套餐)
		 */
		@Column(name="supervisor_package_type")
		private Integer supervisorPackageType;

		/**
   		 *房屋面积，单位（平米）
		 */
		@Column(name="house_acreage")
		private Float houseAcreage;

		/**
   		 *(10.准备装修，20.正在装修，30.已经装修 )
		 */
		@Column(name="house_decorate_status")
		private Integer houseDecorateStatus;
		
		/**
  		 *房屋完整地址
		 */
		@Column(name="house_addr_full")
		private String houseAddrFull;


		/**
   		 *
		 */
		@Column(name="house_addr_province")
		private String houseAddrProvince;

		/**
   		 *
		 */
		@Column(name="house_addr_city")
		private String houseAddrCity;

		/**
   		 *
		 */
		@Column(name="house_addr_area")
		private String houseAddrArea;

		/**
   		 *(10.普通住宅，20.商住两用...)
		 */
		@Column(name="house_model")
		private Integer houseModel;

		/**
   		 *
		 */
		@Column(name="house_model_room")
		private Integer houseModelRoom;

		/**
   		 *
		 */
		@Column(name="house_model_hall")
		private Integer houseModelHall;

		/**
   		 *
		 */
		@Column(name="house_model_kitchen")
		private Integer houseModelKitchen;

		/**
   		 *
		 */
		@Column(name="house_model_toilet")
		private Integer houseModelToilet;

		/**
   		 *
		 */
		@Column(name="house_model_balcony")
		private Integer houseModelBalcony;

		/**
   		 *(10.简装，20.精装，30.豪华)
		 */
		@Column(name="house_decorate_grade")
		private Integer houseDecorateGrade;

		/**
   		 *预约发起时间
		 */
		@Column(name="create_time")
		private Date createTime;

		/**
   		 *客服响应时间
		 */
		@Column(name="ser_reply_time")
		private Date serReplyTime;

		/**
   		 *客服答复内容
		 */
		@Column(name="ser_reply_content")
		private String serReplyContent;
		/**
		 *客服是否答复
		 */
		@Column(name="ser_has_reply")
		private Integer serHasReply;

		
		/**
   		 *
		 */
		@Column(name="remark")
		private String remark;
		
		
		/**
  		 *短信校验
		 */
		@Column(name="isChecked")
		private Integer isChecked;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getMemberMobile() {
			return memberMobile;
		}

		public void setMemberMobile(String memberMobile) {
			this.memberMobile = memberMobile;
		}

		public Integer getApplyType() {
			return applyType;
		}

		public void setApplyType(Integer applyType) {
			this.applyType = applyType;
		}

		public Integer getSupervisorPackageType() {
			return supervisorPackageType;
		}

		public void setSupervisorPackageType(Integer supervisorPackageType) {
			this.supervisorPackageType = supervisorPackageType;
		}

		public Float getHouseAcreage() {
			return houseAcreage;
		}

		public void setHouseAcreage(Float houseAcreage) {
			this.houseAcreage = houseAcreage;
		}

		public Integer getHouseDecorateStatus() {
			return houseDecorateStatus;
		}

		public void setHouseDecorateStatus(Integer houseDecorateStatus) {
			this.houseDecorateStatus = houseDecorateStatus;
		}

		
		public String getHouseAddrFull() {
			return houseAddrFull;
		}

		public void setHouseAddrFull(String houseAddrFull) {
			this.houseAddrFull = houseAddrFull;
		}

		public String getHouseAddrProvince() {
			return houseAddrProvince;
		}

		public void setHouseAddrProvince(String houseAddrProvince) {
			this.houseAddrProvince = houseAddrProvince;
		}

		public String getHouseAddrCity() {
			return houseAddrCity;
		}

		public void setHouseAddrCity(String houseAddrCity) {
			this.houseAddrCity = houseAddrCity;
		}

		public String getHouseAddrArea() {
			return houseAddrArea;
		}

		public void setHouseAddrArea(String houseAddrArea) {
			this.houseAddrArea = houseAddrArea;
		}

		public Integer getHouseModel() {
			return houseModel;
		}

		public void setHouseModel(Integer houseModel) {
			this.houseModel = houseModel;
		}

		public Integer getHouseModelRoom() {
			return houseModelRoom;
		}

		public void setHouseModelRoom(Integer houseModelRoom) {
			this.houseModelRoom = houseModelRoom;
		}

		public Integer getHouseModelHall() {
			return houseModelHall;
		}

		public void setHouseModelHall(Integer houseModelHall) {
			this.houseModelHall = houseModelHall;
		}

		public Integer getHouseModelKitchen() {
			return houseModelKitchen;
		}

		public void setHouseModelKitchen(Integer houseModelKitchen) {
			this.houseModelKitchen = houseModelKitchen;
		}

		public Integer getHouseModelToilet() {
			return houseModelToilet;
		}

		public void setHouseModelToilet(Integer houseModelToilet) {
			this.houseModelToilet = houseModelToilet;
		}

		public Integer getHouseModelBalcony() {
			return houseModelBalcony;
		}

		public void setHouseModelBalcony(Integer houseModelBalcony) {
			this.houseModelBalcony = houseModelBalcony;
		}

		public Integer getHouseDecorateGrade() {
			return houseDecorateGrade;
		}

		public void setHouseDecorateGrade(Integer houseDecorateGrade) {
			this.houseDecorateGrade = houseDecorateGrade;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getSerReplyTime() {
			return serReplyTime;
		}

		public void setSerReplyTime(Date serReplyTime) {
			this.serReplyTime = serReplyTime;
		}

		public String getSerReplyContent() {
			return serReplyContent;
		}

		public void setSerReplyContent(String serReplyContent) {
			this.serReplyContent = serReplyContent;
		}

		
		public Integer getSerHasReply() {
			return serHasReply;
		}

		public void setSerHasReply(Integer serHasReply) {
			this.serHasReply = serHasReply;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Integer getIsChecked() {
			return isChecked;
		}

		public void setIsChecked(Integer isChecked) {
			this.isChecked = isChecked;
		}
		
		

}