package com.jkkp.modules.supplier.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;

@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	/**
	 * 供货商名称
	 */
	@Column(name = "certificate_type")
	public Integer certificateType;
	public Integer available;
	//20150826新增
	@Column(name = "id_card")
	public String idCard;
	/**
	 * 简称
	 */
	@Column
	public String abbreviation;
	/**
	 * 主营业务
	 */
	@Column(name="primary_business")
	public String primaryBusiness;
	
	//经度
	private String pointx;
	//纬度
	private String pointy;
	
	@Column(name="region_id")
	private Integer regionId;
	
	
	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getPointx() {
		return pointx;
	}

	public void setPointx(String pointx) {
		this.pointx = pointx;
	}

	public String getPointy() {
		return pointy;
	}

	public void setPointy(String pointy) {
		this.pointy = pointy;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getPrimaryBusiness() {
		return primaryBusiness;
	}

	public void setPrimaryBusiness(String primaryBusiness) {
		this.primaryBusiness = primaryBusiness;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getAvailable() {
		return available;
	}

	@Column(name = "sp_code")
	public String spCode;

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	@Column(name = "sp_name")
	public String spName;

	public String getJkbFlag() {
		return jkbFlag;
	}

	public void setJkbFlag(String jkbFlag) {
		this.jkbFlag = jkbFlag;
	}

	@Column(name = "jkb_flag")
	public String jkbFlag;

	/**
	 * 0正常-1关闭-2超期
	 */
	public Integer status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	public Date createTime;

	/**
	 * 1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
	 */
	@Column(name = "bank_id")
	public Integer bankId;

	/**
	 * 开户行
	 */
	@Column(name = "bank_full_name")
	public String bankFullName;

	/**
	 * 开户人姓名
	 */
	@Column(name = "bank_author")
	public String bankAuthor;

	/**
	 * 开户行所在省
	 */
	@Column(name = "bank_province")
	public Integer bankProvince;

	/**
	 * 开户城市
	 */
	@Column(name = "bank_city")
	public Integer bankCity;

	/**
	 * 银行账号
	 */
	@Column(name = "bank_account")
	public String bankAccount;

	/**
	 * 用户名
	 */
	public String username;

	/**
	 * 密码
	 */
	public String pass;

	/**
	 * 联系人
	 */
	@Column(name = "contact_user")
	public String contactUser;

	/**
	 * 联系电话
	 */
	@Column(name = "contact_mobile")
	public String contactMobile;

	/**
	 * 分成比例
	 */
	@Column(name = "gain_rate")
	public Float gainRate;

	/**
	 * 签约开始时间
	 */
	@Column(name = "start_date")
	public Date startDate;

	/**
	 * 签约结束时间
	 */
	@Column(name = "end_date")
	public Date endDate;

	/**
	 * 商户地址
	 */
	public String address;

	/**
	 * 1建材商2装修公司3工长4实体店5监理6设计师
	 */
	public Integer type;

	/**
	 * 400分机
	 */
	@Column(name = "base_service_phone")
	public Integer baseServicePhone;

	/**
	 * 信誉服务
	 */
	@Column(name = "base_service_reputation")
	public Integer baseServiceReputation;

	/**
	 * 店铺服务
	 */
	@Column(name = "base_service_shop")
	public Integer baseServiceShop;

	/**
	 * o2o券
	 */
	@Column(name = "pay_servince_o2o")
	public Integer payServinceO2o;

	/**
	 * 代运营
	 */
	@Column(name = "pay_servince_yunying")
	public Integer payServinceYunying;

	/**
	 * 搜索竞价
	 */
	@Column(name = "pay_servince_search")
	public Integer payServinceSearch;

	/**
	 * 二级域名服务0未开启1开启
	 */
	@Column(name = "pay_servince_domain")
	public Integer payServinceDomain;

	/**
	 * 店铺首页服务
	 */
	@Column(name = "pay_servince_shop_index")
	public Integer payServinceShopIndex;

	/**
	 * 抵扣券
	 */
	@Column(name = "pay_servince_discount")
	public Integer payServinceDiscount;

	/**
	 * 付费服务
	 */
	@Column(name = "pay_service")
	public Integer payService;

	/**
	 * 400分机号
	 */
	@Column(name = "sub_phone")
	public String subPhone;

	/**
	 * 所在城市域名
	 */
	@Column(name = "city_domain")
	public String cityDomain;

	/**
	 * 代管状态：-1拒绝0未申请1待审核2代管中3结束托管
	 */
	@Column(name = "proxy_status")
	public Integer proxyStatus;

	/**
	 * 商家自定义域名
	 */
	public String domain;

	/**
	 * 描述评价1-5星
	 */
	@Column(name = "estimate_desc")
	public BigDecimal estimateDesc;

	/**
	 * 服务评价1-5
	 */
	@Column(name = "estimate_service")
	public BigDecimal estimateService;

	/**
	 * 效率评价1-5星
	 */
	@Column(name = "estimate_efficiency")
	public BigDecimal estimateEfficiency;

	/**
	 * 综合评分
	 */
	@Column(name = "estimate_average")
	public BigDecimal estimateAverage;

	/**
	 * 等级id
	 */
	@Column(name = "level_id")
	public Integer levelId;

	/**
	 * 商家身份：1普通商家；2Vip商家
	 */
	public Integer level;
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 类型，1：对公，2：对私
	 */
	@Column(name = "bank_account_type")
	public Integer bankAccountType;

	/**
	 * 法人
	 */
	@Column(name = "legal_person")
	public String legalPerson;

	/**
	 * 营业执照
	 */
	@Column(name = "business_code")
	public String businessCode;

	/**
	 * 绑定手机
	 */
	@Column(name = "bind_mobile")
	public String bindMobile;

	/**
	 * 审核不通过原因
	 */
	@Column(name = "cause")
	public String cause;

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
	 * 获取供货商名称
	 * 
	 * @return sp_name - 供货商名称
	 */
	public String getSpName() {
		return spName;
	}

	/**
	 * 设置供货商名称
	 * 
	 * @param spName
	 *            供货商名称
	 */
	public void setSpName(String spName) {
		this.spName = spName;
	}

	/**
	 * 获取0正常-1关闭-2超期
	 * 
	 * @return status - 0正常-1关闭-2超期
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置0正常-1关闭-2超期
	 * 
	 * @param status
	 *            0正常-1关闭-2超期
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
	 * 
	 * @return bank_id - 1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
	 */
	public Integer getBankId() {
		return bankId;
	}

	/**
	 * 设置1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
	 * 
	 * @param bankId
	 *            1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
	 */
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	/**
	 * 获取开户行
	 * 
	 * @return bank_full_name - 开户行
	 */
	public String getBankFullName() {
		return bankFullName;
	}

	/**
	 * 设置开户行
	 * 
	 * @param bankFullName
	 *            开户行
	 */
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}

	/**
	 * 获取开户人姓名
	 * 
	 * @return bank_author - 开户人姓名
	 */
	public String getBankAuthor() {
		return bankAuthor;
	}

	/**
	 * 设置开户人姓名
	 * 
	 * @param bankAuthor
	 *            开户人姓名
	 */
	public void setBankAuthor(String bankAuthor) {
		this.bankAuthor = bankAuthor;
	}

	/**
	 * 获取开户行所在省
	 * 
	 * @return bank_province - 开户行所在省
	 */
	public Integer getBankProvince() {
		return bankProvince;
	}

	/**
	 * 设置开户行所在省
	 * 
	 * @param bankProvince
	 *            开户行所在省
	 */
	public void setBankProvince(Integer bankProvince) {
		this.bankProvince = bankProvince;
	}

	/**
	 * 获取开户城市
	 * 
	 * @return bank_city - 开户城市
	 */
	public Integer getBankCity() {
		return bankCity;
	}

	/**
	 * 设置开户城市
	 * 
	 * @param bankCity
	 *            开户城市
	 */
	public void setBankCity(Integer bankCity) {
		this.bankCity = bankCity;
	}

	/**
	 * 获取银行账号
	 * 
	 * @return bank_account - 银行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * 设置银行账号
	 * 
	 * @param bankAccount
	 *            银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * 获取用户名
	 * 
	 * @return username - 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取密码
	 * 
	 * @return pass - 密码
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 设置密码
	 * 
	 * @param pass
	 *            密码
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 获取联系人
	 * 
	 * @return contact_user - 联系人
	 */
	public String getContactUser() {
		return contactUser;
	}

	/**
	 * 设置联系人
	 * 
	 * @param contactUser
	 *            联系人
	 */
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return contact_mobile - 联系电话
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param contactMobile
	 *            联系电话
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * 获取分成比例
	 * 
	 * @return gain_rate - 分成比例
	 */
	public Float getGainRate() {
		if(gainRate == null){
			return Float.valueOf(0);
		}else{
			return this.gainRate;
		}
	}

	public String getGainRateName() {
		return CommonUtil.isNull(gainRate, 0f) * 100 + "%";
	}

	/**
	 * 设置分成比例
	 * 
	 * @param gainRate
	 *            分成比例
	 */
	public void setGainRate(Float gain) {
		if(gain == null){
			this.gainRate = Float.valueOf(0);
		}else{
			this.gainRate = gain;
		}
	}

	/**
	 * 获取签约开始时间
	 * 
	 * @return start_date - 签约开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置签约开始时间
	 * 
	 * @param startDate
	 *            签约开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取签约结束时间
	 * 
	 * @return end_date - 签约结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置签约结束时间
	 * 
	 * @param endDate
	 *            签约结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取商户地址
	 * 
	 * @return address - 商户地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置商户地址
	 * 
	 * @param address
	 *            商户地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取1建材商2装修公司3工长4实体店5监理6设计师
	 * 
	 * @return type - 1建材商2装修公司3工长4实体店5监理6设计师
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置1建材商2装修公司3工长4实体店5监理6设计师
	 * 
	 * @param type
	 *            1建材商2装修公司3工长4实体店5监理6设计师
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取400分机
	 * 
	 * @return base_service_phone - 400分机
	 */
	public Integer getBaseServicePhone() {
		return baseServicePhone;
	}

	/**
	 * 设置400分机
	 * 
	 * @param baseServicePhone
	 *            400分机
	 */
	public void setBaseServicePhone(Integer baseServicePhone) {
		this.baseServicePhone = baseServicePhone;
	}

	/**
	 * 获取信誉服务
	 * 
	 * @return base_service_reputation - 信誉服务
	 */
	public Integer getBaseServiceReputation() {
		return baseServiceReputation;
	}

	/**
	 * 设置信誉服务
	 * 
	 * @param baseServiceReputation
	 *            信誉服务
	 */
	public void setBaseServiceReputation(Integer baseServiceReputation) {
		this.baseServiceReputation = baseServiceReputation;
	}

	/**
	 * 获取店铺服务
	 * 
	 * @return base_service_shop - 店铺服务
	 */
	public Integer getBaseServiceShop() {
		return baseServiceShop;
	}

	/**
	 * 设置店铺服务
	 * 
	 * @param baseServiceShop
	 *            店铺服务
	 */
	public void setBaseServiceShop(Integer baseServiceShop) {
		this.baseServiceShop = baseServiceShop;
	}

	/**
	 * 获取o2o券
	 * 
	 * @return pay_servince_o2o - o2o券
	 */
	public Integer getPayServinceO2o() {
		return payServinceO2o;
	}

	/**
	 * 设置o2o券
	 * 
	 * @param payServinceO2o
	 *            o2o券
	 */
	public void setPayServinceO2o(Integer payServinceO2o) {
		this.payServinceO2o = payServinceO2o;
	}

	/**
	 * 获取代运营
	 * 
	 * @return pay_servince_yunying - 代运营
	 */
	public Integer getPayServinceYunying() {
		return payServinceYunying;
	}

	/**
	 * 设置代运营
	 * 
	 * @param payServinceYunying
	 *            代运营
	 */
	public void setPayServinceYunying(Integer payServinceYunying) {
		this.payServinceYunying = payServinceYunying;
	}

	/**
	 * 获取搜索竞价
	 * 
	 * @return pay_servince_search - 搜索竞价
	 */
	public Integer getPayServinceSearch() {
		return payServinceSearch;
	}

	/**
	 * 设置搜索竞价
	 * 
	 * @param payServinceSearch
	 *            搜索竞价
	 */
	public void setPayServinceSearch(Integer payServinceSearch) {
		this.payServinceSearch = payServinceSearch;
	}

	/**
	 * 获取二级域名服务0未开启1开启
	 * 
	 * @return pay_servince_domain - 二级域名服务0未开启1开启
	 */
	public Integer getPayServinceDomain() {
		return payServinceDomain;
	}

	/**
	 * 设置二级域名服务0未开启1开启
	 * 
	 * @param payServinceDomain
	 *            二级域名服务0未开启1开启
	 */
	public void setPayServinceDomain(Integer payServinceDomain) {
		this.payServinceDomain = payServinceDomain;
	}

	/**
	 * 获取店铺首页服务
	 * 
	 * @return pay_servince_shop_index - 店铺首页服务
	 */
	public Integer getPayServinceShopIndex() {
		return payServinceShopIndex;
	}

	/**
	 * 设置店铺首页服务
	 * 
	 * @param payServinceShopIndex
	 *            店铺首页服务
	 */
	public void setPayServinceShopIndex(Integer payServinceShopIndex) {
		this.payServinceShopIndex = payServinceShopIndex;
	}

	/**
	 * 获取抵扣券
	 * 
	 * @return pay_servince_discount - 抵扣券
	 */
	public Integer getPayServinceDiscount() {
		return payServinceDiscount;
	}

	/**
	 * 设置抵扣券
	 * 
	 * @param payServinceDiscount
	 *            抵扣券
	 */
	public void setPayServinceDiscount(Integer payServinceDiscount) {
		this.payServinceDiscount = payServinceDiscount;
	}

	/**
	 * 获取付费服务
	 * 
	 * @return pay_service - 付费服务
	 */
	public Integer getPayService() {
		return payService;
	}

	/**
	 * 设置付费服务
	 * 
	 * @param payService
	 *            付费服务
	 */
	public void setPayService(Integer payService) {
		this.payService = payService;
	}

	/**
	 * 获取400分机号
	 * 
	 * @return sub_phone - 400分机号
	 */
	public String getSubPhone() {
		return subPhone;
	}

	/**
	 * 设置400分机号
	 * 
	 * @param subPhone
	 *            400分机号
	 */
	public void setSubPhone(String subPhone) {
		this.subPhone = subPhone;
	}

	/**
	 * 获取所在城市域名
	 * 
	 * @return city_domain - 所在城市域名
	 */
	public String getCityDomain() {
		return cityDomain;
	}

	/**
	 * 设置所在城市域名
	 * 
	 * @param cityDomain
	 *            所在城市域名
	 */
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
	 * 获取代管状态：-1拒绝0未申请1待审核2代管中3结束托管
	 * 
	 * @return proxy_status - 代管状态：-1拒绝0未申请1待审核2代管中3结束托管
	 */
	public Integer getProxyStatus() {
		return proxyStatus;
	}

	public String getProxyStatusString() {
		if (proxyStatus == null)
			return "";
		switch (proxyStatus) {
		case -1:
			return "未通过";
		case 0:
			return "待审核";
		case 1:
			return "通过";
		default:
			return "";
		}
	}

	/**
	 * 设置代管状态：-1拒绝0未申请1待审核2代管中3结束托管
	 * 
	 * @param proxyStatus
	 *            代管状态：-1拒绝0未申请1待审核2代管中3结束托管
	 */
	public void setProxyStatus(Integer proxyStatus) {
		this.proxyStatus = proxyStatus;
	}

	/**
	 * 获取商家自定义域名
	 * 
	 * @return domain - 商家自定义域名
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * 设置商家自定义域名
	 * 
	 * @param domain
	 *            商家自定义域名
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * 获取描述评价1-5星
	 * 
	 * @return estimate_desc - 描述评价1-5星
	 */
	public BigDecimal getEstimateDesc() {
		if(estimateDesc == null){
			return BigDecimal.valueOf(0);
		}else{
			return this.estimateDesc;
		}
	}

	/**
	 * 设置描述评价1-5星
	 * 
	 * @param estimateDesc
	 *            描述评价1-5星
	 */
	public void setEstimateDesc(BigDecimal desc) {
		if(desc == null){
			estimateDesc = BigDecimal.valueOf(0);
		}else{
			this.estimateDesc = desc;
		}
	}

	/**
	 * 获取服务评价1-5
	 * 
	 * @return estimate_service - 服务评价1-5
	 */
	public BigDecimal getEstimateService() {
		if(estimateService == null){
			return BigDecimal.valueOf(0);
		}else{
			return this.estimateService;
		}
	}

	/**
	 * 设置服务评价1-5
	 * 
	 * @param estimateService
	 *            服务评价1-5
	 */
	public void setEstimateService(BigDecimal service) {
		if(service == null){
			estimateService = BigDecimal.valueOf(0);
		}else{
			this.estimateService = service;
		}
	}

	/**
	 * 获取效率评价1-5星
	 * 
	 * @return estimate_efficiency - 效率评价1-5星
	 */
	public BigDecimal getEstimateEfficiency() {
		if(estimateEfficiency == null){
			return BigDecimal.valueOf(0);
		}else{
			return this.estimateEfficiency;
		}
	}

	/**
	 * 设置效率评价1-5星
	 * 
	 * @param estimateEfficiency
	 *            效率评价1-5星
	 */
	public void setEstimateEfficiency(BigDecimal efficiency) {
		if(efficiency == null){
			estimateEfficiency = BigDecimal.valueOf(0);
		}else{
			this.estimateEfficiency = efficiency;
		}
	}

	/**
	 * 获取综合评分
	 * 
	 * @return estimate_average - 综合评分
	 */
	public BigDecimal getEstimateAverage() {
		if(estimateAverage == null){
			return BigDecimal.valueOf(0);
		}else{
			return this.estimateAverage;
		}
	}

	/**
	 * 设置综合评分
	 * 
	 * @param estimateAverage
	 *            综合评分
	 */
	public void setEstimateAverage(BigDecimal average) {
		if(average == null){
			estimateAverage = BigDecimal.valueOf(0);
		}else{
			this.estimateAverage = average;
		}
	}

	/**
	 * 获取等级id
	 * 
	 * @return level_id - 等级id
	 */
	public Integer getLevelId() {
		return levelId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * 设置等级id
	 * 
	 * @param levelId
	 *            等级id
	 */
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(Integer bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBindMobile() {
		return bindMobile;
	}

	public void setBindMobile(String bindMobile) {
		this.bindMobile = bindMobile;
	}

	public String getStatusName() {
		return STATUS_MAP.get(status);
	}

	// 0正常-1关闭-2超期
	public static final int STATUS_NORMAL = 0;
	public static final int STATUS_CLOSED = -1;
	public static final int STATUS_SUSPEND = -2;
	public static final Map<Integer, String> STATUS_MAP = new HashMap<Integer, String>() {
		public static final long serialVersionUID = 1L;
		{
			put(STATUS_NORMAL, "正常");
			put(STATUS_CLOSED, "关闭");
			put(STATUS_SUSPEND, "过期");
		}
	};

	

	public String getProxyStatusVal() {
		if (proxyStatus != null) {
			if (proxyStatus == 0) {
				return "待审核";
			}
			if (proxyStatus == -1) {
				return "未通过";
			}
			if (proxyStatus == 1) {
				return "已通过";
			}
		 }
		return "";
	}

	public String getStartDateHandle() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startDate != null) {
			return sdf.format(startDate);
		}
		return "";
	}

	public String getEndDateHandle() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (endDate != null) {
			return sdf.format(endDate);
		}
		return "";
	}

	public String getCreateTimeHandle() {
		return DateUtil.formatDateTime(createTime);
	}
	
	//用于星级图片
	public BigDecimal formate(BigDecimal args){
		String sestimate = args.toString();
		if(sestimate.indexOf('.') > 0){
			int tmpStratStr = Integer.valueOf(sestimate.substring(0,sestimate.indexOf('.')));
			String tmpEndStr = sestimate.substring(sestimate.indexOf('.') + 1);
			int tmpNum = Integer.valueOf(tmpEndStr);
			if(tmpNum > 50){
				return BigDecimal.valueOf(tmpStratStr + 1);
			}else if(tmpNum < 50){
				return BigDecimal.valueOf(tmpStratStr);
			}
		}
		return args;
	}
	
	public BigDecimal getZongHe(){
		return estimateAverage == null ? BigDecimal.valueOf(0) : formate(estimateAverage);
	}
	
	public BigDecimal getXiaoLv(){
		return estimateEfficiency == null ? BigDecimal.valueOf(0) : formate(estimateEfficiency);
	}
	
	public BigDecimal getFuWu(){
		return estimateService == null ? BigDecimal.valueOf(0) : formate(estimateService);
	}
	
	public BigDecimal getMiaoShu(){
		return estimateDesc == null ? BigDecimal.valueOf(0) : formate(estimateDesc);
	}
	
	public String getLevelVal(){
		if(level!=null){
			return level==1?"普通商家":"VIP商家";
		}
		return "待设定";
	}
}