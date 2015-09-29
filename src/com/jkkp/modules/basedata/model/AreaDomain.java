package com.jkkp.modules.basedata.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;

@Table(name = "area_domain")
public class AreaDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 区域域名
	 */
	@Column(name = "area_domain")
	private String areaDomain;

	/**
	 * 区域名称
	 */
	private String area;

	/**
	 * 省域名
	 */
	@Column(name = "province_domain")
	private String provinceDomain;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 城市名称
	 */
	private String city;

	/**
	 * 域名简称
	 */
	@Column(name = "city_domain")
	private String cityDomain;

	/**
	 * 开通状态：0未开通1开通-1下线
	 */
	@Column(name = "is_open")
	private Integer isOpen;

	/**
	 * 开通时间
	 */
	@Column(name = "open_time")
	private Date openTime;

	/**
	 * 下线时间
	 */
	@Column(name = "offline_time")
	private Date offlineTime;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取区域域名
	 *
	 * @return area_domain - 区域域名
	 */
	public String getAreaDomain() {
		return areaDomain;
	}

	/**
	 * 设置区域域名
	 *
	 * @param areaDomain
	 *            区域域名
	 */
	public void setAreaDomain(String areaDomain) {
		this.areaDomain = areaDomain;
	}

	/**
	 * 获取区域名称
	 *
	 * @return area - 区域名称
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 设置区域名称
	 *
	 * @param area
	 *            区域名称
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 获取省域名
	 *
	 * @return province_domain - 省域名
	 */
	public String getProvinceDomain() {
		return provinceDomain;
	}

	/**
	 * 设置省域名
	 *
	 * @param provinceDomain
	 *            省域名
	 */
	public void setProvinceDomain(String provinceDomain) {
		this.provinceDomain = provinceDomain;
	}

	/**
	 * 获取省
	 *
	 * @return province - 省
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置省
	 *
	 * @param province
	 *            省
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取城市名称
	 *
	 * @return city - 城市名称
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置城市名称
	 *
	 * @param city
	 *            城市名称
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取域名简称
	 *
	 * @return city_domain - 域名简称
	 */
	public String getCityDomain() {
		return cityDomain;
	}

	/**
	 * 设置域名简称
	 *
	 * @param cityDomain
	 *            域名简称
	 */
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
	 * 获取开通状态：0未开通1开通-1下线
	 *
	 * @return is_open - 开通状态：0未开通1开通-1下线
	 */
	public Integer getIsOpen() {
		return isOpen;
	}

	/**
	 * 设置开通状态：0未开通1开通-1下线
	 *
	 * @param isOpen
	 *            开通状态：0未开通1开通-1下线
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * 获取开通时间
	 *
	 * @return open_time - 开通时间
	 */
	public Date getOpenTime() {
		return openTime;
	}

	/**
	 * 设置开通时间
	 *
	 * @param openTime
	 *            开通时间
	 */
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	/**
	 * 获取下线时间
	 *
	 * @return offline_time - 下线时间
	 */
	public Date getOfflineTime() {
		return offlineTime;
	}

	/**
	 * 设置下线时间
	 *
	 * @param offlineTime
	 *            下线时间
	 */
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getOpenTimeName() {
		return openTime == null ? "" : DateUtil.formatDateTime(openTime);
	}
	
	public String getOfflineTimeName() {
		return offlineTime == null ? "" : DateUtil.formatDateTime(offlineTime);
	}

	public String getIsOpenName() {
		return IS_OPEN_MAP.get(isOpen);
	}

	// 0未开通1开通-1下线
	public static final int IS_OPEN_NO = 0;
	public static final int IS_OPEN_YES = 1;
	public static final int IS_OFFLINE = -1;
	public static final Map<Integer, String> IS_OPEN_MAP = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(IS_OPEN_NO, "未开通");
			put(IS_OPEN_YES, "开通");
			put(IS_OFFLINE, "下线");
		}
	};

	public String getOperateName() {
		int isOpen = CommonUtil.isNull(this.isOpen, IS_OPEN_NO);
		if (isOpen == IS_OPEN_YES) {
			return "下线";
		} else {
			return "开通";
		}
	}

	public int getOpen() {
		return CommonUtil.isNull(this.isOpen, IS_OPEN_NO) == IS_OPEN_YES ? 1 : 0;
	}
}