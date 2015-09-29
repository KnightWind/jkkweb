package com.jkkp.modules.interfaceauth.model;

import java.util.Date;

import javax.persistence.*;

@Table(name = "interface_record")
public class InterfaceRecord{


		/**
   		 *主键
		 */
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;

		/**
   		 *登录标识
		 */
		@Column(name="tocken_id")
		private String tockenId;

		/**
   		 *登录用户
		 */
		@Column(name="user_id")
		private Integer userId;

		/**
   		 *用户类型
		 */
		@Column(name="user_type")
		private Integer userType;

		/**
   		 *接口编码
		 */
		@Column(name="interface_code")
		private String interfaceCode;

		/**
   		 *接口名称
		 */
		@Column(name="interface_name")
		private String interfaceName;

		/**
   		 *入参
		 */
		@Column(name="input_param")
		private String inputParam;

		/**
   		 *出参
		 */
		@Column(name="out_param")
		private String outParam;

		/**
   		 *操作结果
		 */
		@Column(name="oper_result")
		private String operResult;

		/**
   		 *操作类型
		 */
		@Column(name="oper_type")
		private String operType;

		/**
   		 *操作描述
		 */
		@Column(name="oper_desc")
		private String operDesc;

		/**
   		 *操作时间
		 */
		@Column(name="oper_time")
		private Date operTime;

		/**
   		 *调用源ip
		 */
		@Column(name="src_ip")
		private String srcIp;
		
		/**
   		 *操作结果描述
		 */
		@Column(name="oper_Result_Desc")
		private String operResultDesc;

		public String getOperResultDesc() {
			return operResultDesc;
		}

		public void setOperResultDesc(String operResultDesc) {
			this.operResultDesc = operResultDesc;
		}

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

		public Integer getUserType() {
			return userType;
		}

		public void setUserType(Integer userType) {
			this.userType = userType;
		}

		public String getInterfaceCode() {
			return interfaceCode;
		}

		public void setInterfaceCode(String interfaceCode) {
			this.interfaceCode = interfaceCode;
		}

		public String getInterfaceName() {
			return interfaceName;
		}

		public void setInterfaceName(String interfaceName) {
			this.interfaceName = interfaceName;
		}

		public String getInputParam() {
			return inputParam;
		}

		public void setInputParam(String inputParam) {
			this.inputParam = inputParam;
		}

		public String getOutParam() {
			return outParam;
		}

		public void setOutParam(String outParam) {
			this.outParam = outParam;
		}

		public String getOperResult() {
			return operResult;
		}

		public void setOperResult(String operResult) {
			this.operResult = operResult;
		}

		public String getOperType() {
			return operType;
		}

		public void setOperType(String operType) {
			this.operType = operType;
		}

		public String getOperDesc() {
			return operDesc;
		}

		public void setOperDesc(String operDesc) {
			this.operDesc = operDesc;
		}

		public Date getOperTime() {
			return operTime;
		}

		public void setOperTime(Date operTime) {
			this.operTime = operTime;
		}

		public String getSrcIp() {
			return srcIp;
		}

		public void setSrcIp(String srcIp) {
			this.srcIp = srcIp;
		}
		

}