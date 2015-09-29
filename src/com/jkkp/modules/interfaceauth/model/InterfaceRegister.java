package com.jkkp.modules.interfaceauth.model;

import javax.persistence.*;

@Table(name = "interface_register")
public class InterfaceRegister{


		/**
   		 *主键
		 */
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;

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
   		 *模块编码
		 */
		@Column(name="module_code")
		private String moduleCode;

		/**
   		 *模块名称(比如登录模块，方案设计模块)
		 */
		@Column(name="module_name")
		private String moduleName;

		/**
   		 *渠道分类( (android，ios，微信....等)根据实际需要考虑是否需要对接口分类)
		 */
		@Column(name="channel_type")
		private Integer channelType;

		/**
   		 *操作类型( （C、R、U、D）对应增、删、改、查)
		 */
		@Column(name="oper_type")
		private Integer operType;

		/**
   		 *操作描述（该接口的作用或用途描述）
		 */
		@Column(name="oper_desc")
		private String operDesc;

		/**
   		 *状态（是否需要记录日志 1记录,2不记录）
		 */
		@Column(name="status")
		private Integer status;

		/**
   		 *日志记录类型(1:只记录入参，2：只记录出参，3：全部记录)
		 */
		@Column(name="log_type")
		private Integer logType;

		/**
   		 *是否需要登录(0不需要，1需要)
		 */
		@Column(name="need_login_auth")
		private Integer needLoginAuth;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public String getModuleCode() {
			return moduleCode;
		}

		public void setModuleCode(String moduleCode) {
			this.moduleCode = moduleCode;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public Integer getChannelType() {
			return channelType;
		}

		public void setChannelType(Integer channelType) {
			this.channelType = channelType;
		}

		public Integer getOperType() {
			return operType;
		}

		public void setOperType(Integer operType) {
			this.operType = operType;
		}

		public String getOperDesc() {
			return operDesc;
		}

		public void setOperDesc(String operDesc) {
			this.operDesc = operDesc;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getLogType() {
			return logType;
		}

		public void setLogType(Integer logType) {
			this.logType = logType;
		}

		public Integer getNeedLoginAuth() {
			return needLoginAuth;
		}

		public void setNeedLoginAuth(Integer needLoginAuth) {
			this.needLoginAuth = needLoginAuth;
		}

		
}