/**
 * 
 */
package com.jkkp.appapi.putapp;

/**
 * @author Administrator
 *
 */
public class GeTuiAppParam {

	private String appId;
	private String appkey;
	private String master;
	
	/**
	 * @param appId
	 * @param appkey
	 * @param master
	 */
	public GeTuiAppParam(String appId, String appkey, String master) {
		super();
		this.appId = appId;
		this.appkey = appkey;
		this.master = master;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	
}
