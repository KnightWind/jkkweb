package com.jkkp.modules.interfaceauth.model;

import java.util.Date;

import javax.persistence.*;

@Table(name = "user_tocken")
public class UserTocken{

		/**
   		 *主键
		 */
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;

		/**
   		 *用户登录的tockenId
		 */
		@Column(name="tocken_id")
		private String tockenId;

		/**
   		 *用户id，配合用户type
		 */
		@Column(name="user_id")
		private Integer userId;

		/**
   		 *登录用户名字
		 */
		@Column(name="user_name")
		private String userName;

		/**
   		 *登录用户类型，0是用户，1商家（装修商家，监理，因为在同一个表）
		 */
		@Column(name="user_type")
		private Integer userType;

		/**
   		 *创建时间
		 */
		@Column(name="create_Date")
		private Date createDate;
		
		
		/**
   		 *近期访问时间
		 */
		private Date recentVistiTime;
		
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTockenId() {
			return tockenId;
		}

		public void setTockenId(String tockenId) {
			this.tockenId = tockenId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getUserType() {
			return userType;
		}

		public void setUserType(Integer userType) {
			this.userType = userType;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getRecentVistiTime() {
			return recentVistiTime;
		}

		public void setRecentVistiTime(Date recentVistiTime) {
			this.recentVistiTime = recentVistiTime;
		}
}