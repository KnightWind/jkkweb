package com.jkkp.modules.basedata.model;
import javax.persistence.*;

@Table(name = "application_version")
public class ApplicationVersion{
		/**
   		 *版本号，1，2，3...
		 */
		@Column(name="version")
		private Integer version;
		/**
   		 *下载链接,www....
		 */
		@Column(name="url")
		private String url;
		/**
   		 *应用包大小,3.111
		 */
		@Column(name="size")
		private Float size;
		/**
   		 *描述,更新内容
		 */
		@Column(name="descr")
		private String descr;
		/**
   		 *是否强制更新，0不强制，1强制
		 */
		@Column(name="focus_update")
		private String focusUpdate;
		/**
   		 *应用系统类型，0 iphone 1 android
		 */
		@Column(name="os_type")
		private String osType;
		/**
   		 *应用功能类型，1用户，2商家，3监理
		 */
		@Column(name="app_type")
		private String appType;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		/**
   		 *应用名字
		 */
		@Column(name="name")
		private String name;
		/**
   		 *显示名字
		 */
		@Column(name="display_version")
		private String displayVersion;

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Float getSize() {
			return size;
		}

		public void setSize(Float size) {
			this.size = size;
		}

		public String getDescr() {
			return descr;
		}

		public void setDescr(String descr) {
			this.descr = descr;
		}

		public String getFocusUpdate() {
			return focusUpdate;
		}

		public void setFocusUpdate(String focusUpdate) {
			this.focusUpdate = focusUpdate;
		}

		public String getOsType() {
			return osType;
		}

		public void setOsType(String osType) {
			this.osType = osType;
		}

		public String getAppType() {
			return appType;
		}

		public void setAppType(String appType) {
			this.appType = appType;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDisplayVersion() {
			return displayVersion;
		}

		public void setDisplayVersion(String displayVersion) {
			this.displayVersion = displayVersion;
		}
		
		

}