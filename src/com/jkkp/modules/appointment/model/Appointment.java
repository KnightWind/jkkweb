package com.jkkp.modules.appointment.model;

import java.math.BigDecimal;
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

@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@Column(name = "su_type")
	public Integer suType;
	@Column(name = "house_type")
	public String houseType;
	@Column(name = "whole_house")
	public Integer wholeHouse;
	@Column(name = "gcd_id")
	public Integer gcdId;
	public Double pointx;
	public Double pointy;
	public Integer housestyle;
	public Integer grabCount;
	public Integer version;
	public Integer regionid;

	public Integer getRegionid() {
		return regionid;
	}

	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getGrabCount() {
		return grabCount;
	}

	public void setGrabCount(Integer grabCount) {
		this.grabCount = grabCount;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Integer getHousestyle() {
			return housestyle;
	}

	public void setHousestyle(Integer housestyle) {
		this.housestyle = housestyle;
	}

	public Double getPointx() {
		return pointx;
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx;
	}

	public Double getPointy() {
		return pointy;
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy;
	}

	public Integer getGcdId() {
		return gcdId;
	}

	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}

	public Integer getSuType() {
		return suType;
	}

	public void setSuType(Integer suType) {
		this.suType = suType;
	}

	public Integer getWholeHouse() {
		return wholeHouse;
	}

	public void setWholeHouse(Integer wholeHouse) {
		this.wholeHouse = wholeHouse;
	}

	@Column(name = "dcate_id")
	public Integer dcateId;
	@Column(name = "zx_time")
	public Date zxTime;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public Integer type;
	@Column(name = "visit_time")
	public Date visitTime;
	/**
	 * 预约类型：1新房装修2旧房翻新
	 */
	@Column(name = "sub_type")
	public Integer subType;

	public Integer getDcateId() {
		return dcateId;
	}

	public void setDcateId(Integer dcateId) {
		this.dcateId = dcateId;
	}

	public Date getZxTime() {
		return zxTime;
	}

	public void setZxTime(Date zxTime) {
		this.zxTime = zxTime;
	}

	@Column(name = "allow_num")
	public Integer allowNum;

	public Integer getAllowNum() {
		return allowNum;
	}

	public void setAllowNum(Integer allowNum) {
		this.allowNum = allowNum;
	}

	/**
	 * 预约时间
	 */
	@Column(name = "create_time")
	public Date createTime;

	/**
	 * 预约状态：0未处理1已回访2待报价3待支付4施工中5已完成-1关闭-2暂停
	 */
	public Integer status;

	/**
	 * 预约记录
	 */
	public String content;

	/**
	 * 建筑面积:1.60平以下，2.60-90，3.90-120，4.120-150，5.150-200，6.200以上
	 */
	public Float space;

	/**
	 * 浏览数
	 */
	@Column(name = "review_num")
	public Integer reviewNum;

	/**
	 * 预约用户
	 */
	public String user;

	/**
	 * 联系电话
	 */
	public String mobile;

	/**
	 * 预约地址
	 */
	public String address;

	/**
	 * 是否交房0否1是
	 */
	@Column(name = "get_room")
	public Integer getRoom;

	/**
	 * 装修预算：10万以下，2.10-20，3.20-30，4.30-50，5.50-100，6.100以上
	 */
	public Float budget;

	/**
	 * 交房日期
	 */
	@Column(name = "get_room_date")
	public Date getRoomDate;

	/**
	 * 装修方式1.半包2.全包
	 */
	public Integer method;

	/**
	 * 预约来源1.商城网站2.400电话
	 */
	public Integer source;

	/**
	 * 跟单员id（系统管理员）
	 */
	@Column(name = "admin_id")
	public Integer adminId;

	/**
	 * 城市所在域名
	 */
	@Column(name = "city_domain")
	public String cityDomain;

	/**
	 * 沟通时间
	 */
	@Column(name = "link_time")
	public Date linkTime;

	/**
	 * 签约公司
	 */
	public String company;

	/**
	 * 关闭原因
	 */
	public String reason;

	/**
	 * 浏览预约时间
	 */
	@Column(name = "review_time")
	public Date reviewTime;

	/**
	 * 所在小区
	 */
	public String community;

	/**
	 * 客厅地面:0不改造1铺地板2铺瓷砖
	 */
	@Column(name = "parlour_ground")
	public Integer parlourGround;

	/**
	 * 客厅拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 */
	@Column(name = "parlour_ground_dismantle")
	public Integer parlourGroundDismantle;

	/**
	 * 客厅墙面：1刷墙纸2贴壁纸0不改造
	 */
	@Column(name = "parlour_wall")
	public Integer parlourWall;

	/**
	 * 0无拆除项1铲墙皮
	 */
	@Column(name = "parlour_wall_dismantle")
	public Integer parlourWallDismantle;

	/**
	 * 卧室地面：0不改造1铺地板2铺瓷砖
	 */
	@Column(name = "bedroom_ground")
	public Integer bedroomGround;

	/**
	 * 卧室地面拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 */
	@Column(name = "bedroom_ground_dismantle")
	public Integer bedroomGroundDismantle;

	/**
	 * 卧室墙面：0不改造1刷墙漆2贴壁纸
	 */
	@Column(name = "bedroom_wall")
	public Integer bedroomWall;

	/**
	 * 卧室墙面拆除项：0无拆除1铲墙皮
	 */
	@Column(name = "bedroom_wall_dismantle")
	public Integer bedroomWallDismantle;

	/**
	 * 厨房:0不改造，1铺瓷砖
	 */
	public Integer kitchen;

	/**
	 * 拆除旧瓷砖
	 */
	@Column(name = "kitchen_dismantle")
	public Integer kitchenDismantle;

	/**
	 * 卫生间：0不改造
	 */
	public Integer toilet;

	/**
	 * 卫生间拆除项：0无拆除1拆除旧瓷砖
	 */
	@Column(name = "toilet_dismantle")
	public Integer toiletDismantle;

	/**
	 * 水路改造：0不改造1局部改造2全面改造
	 */
	public Integer water;

	/**
	 * 电路改造：0不改造1全面改造
	 */
	public Integer electric;

	/**
	 * 预计开工
	 */
	@Column(name = "start_work")
	public Date startWork;

	/**
	 * x居室
	 */
	@Column(name = "ht_bedroom")
	public Integer htBedroom;

	/**
	 * x厅
	 */
	@Column(name = "ht_living_room")
	public Integer htLivingRoom;

	/**
	 * x厨房
	 */
	@Column(name = "ht_kitchen")
	public Integer htKitchen;

	/**
	 * x卫生间
	 */
	@Column(name = "ht_toilet")
	public Integer htToilet;

	/**
	 * select_push_id
	 */
	@Column(name = "select_push_id")
	public Integer selectPushId;

	/**
	 * 报价
	 */
	public BigDecimal payment;

	/**
	 * 付款时间
	 */
	@Column(name = "pay_time")
	public Date payTime;

	/**
	 * 淘宝交易编号
	 */
	@Column(name = "trade_no")
	public String tradeNo;

	/**
	 * 支付方式1易宝支付2支付宝
	 */
	@Column(name = "pay_type")
	public Integer payType;

	/**
	 * 网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
	 */
	@Column(name = "bank_gateway")
	public String bankGateway;

	/**
	 * 订单完成时间
	 */
	@Column(name = "finish_time")
	public Date finishTime;

	/**
	 * 订单开启时间
	 */
	@Column(name = "enable_time")
	public Date enableTime;

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
	 * 获取预约类型：1新房装修2旧房翻新
	 * 
	 * @return sub_type - 预约类型：1新房装修2旧房翻新
	 */
	public Integer getSubType() {
		return subType;
	}

	/**
	 * 设置预约类型：1新房装修2旧房翻新
	 * 
	 * @param subType
	 *            预约类型：1新房装修2旧房翻新
	 */
	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	/**
	 * 获取预约时间
	 * 
	 * @return create_time - 预约时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getCre() {
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}

	/**
	 * 设置预约时间
	 * 
	 * @param createTime
	 *            预约时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取预约状态：0未处理1已回访2待报价3待支付4施工中5已完成-1关闭-2暂停
	 * 
	 * @return status - 预约状态：0未处理1已回访2待报价3待支付4施工中5已完成-1关闭-2暂停
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置预约状态：0未处理1已回访2待报价3待支付4施工中5已完成-1关闭-2暂停
	 * 
	 * @param status
	 *            预约状态：0未处理1已回访2待报价3待支付4施工中5已完成-1关闭-2暂停
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取预约记录
	 * 
	 * @return content - 预约记录
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置预约记录
	 * 
	 * @param content
	 *            预约记录
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取建筑面积:1.60平以下，2.60-90，3.90-120，4.120-150，5.150-200，6.200以上
	 * 
	 * @return space - 建筑面积:1.60平以下，2.60-90，3.90-120，4.120-150，5.150-200，6.200以上
	 */

	/**
	 * 设置建筑面积:1.60平以下，2.60-90，3.90-120，4.120-150，5.150-200，6.200以上
	 * 
	 * @param space
	 *            建筑面积:1.60平以下，2.60-90，3.90-120，4.120-150，5.150-200，6.200以上
	 */

	/**
	 * 获取浏览数
	 * 
	 * @return review_num - 浏览数
	 */
	public Integer getReviewNum() {
		return reviewNum;
	}

	/**
	 * 设置浏览数
	 * 
	 * @param reviewNum
	 *            浏览数
	 */
	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
	}

	/**
	 * 获取预约用户
	 * 
	 * @return user - 预约用户
	 */
	public String getUser() {
		if (user == null) {
			return "(无)";
		}
		return user;
	}

	/**
	 * 设置预约用户
	 * 
	 * @param user
	 *            预约用户
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return mobile - 联系电话
	 */
	public String getMobile() {
		if (mobile == null || mobile.equals("undefined")) {
			return "(无)";
		}
		return mobile;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param mobile
	 *            联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取预约地址
	 * 
	 * @return address - 预约地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置预约地址
	 * 
	 * @param address
	 *            预约地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取是否交房0否1是
	 * 
	 * @return get_room - 是否交房0否1是
	 */
	public Integer getGetRoom() {
		return getRoom;
	}

	/**
	 * 设置是否交房0否1是
	 * 
	 * @param getRoom
	 *            是否交房0否1是
	 */
	public void setGetRoom(Integer getRoom) {
		this.getRoom = getRoom;
	}

	/**
	 * 获取装修预算：10万以下，2.10-20，3.20-30，4.30-50，5.50-100，6.100以上
	 * 
	 * @return - 装修预算：10万以下，2.10-20，3.20-30，4.30-50，5.50-100，6.100以上
	 */

	// public String getBudgetString(){
	// switch(budget){
	// case 1:
	// return "10万以下";
	// case 2:
	// return "10-20万";
	// case 3:
	// return "20-30万";
	// case 4:
	// return "30-50万";
	// case 5:
	// return "50-100万";
	// case 6:
	// return "100万以上";
	// default:
	// return "10万以下";
	// }
	// }
	/**
	 * 设置装修预算：10万以下，2.10-20，3.20-30，4.30-50，5.50-100，6.100以上
	 * 
	 * @param budget
	 *            装修预算：10万以下，2.10-20，3.20-30，4.30-50，5.50-100，6.100以上
	 */

	/**
	 * 获取交房日期
	 * 
	 * @return get_room_date - 交房日期
	 */
	public Date getGetRoomDate() {
		return getRoomDate;
	}

	public Float getSpace() {
		if (space == null) {
			return Float.valueOf(0);
		} else {
			return this.space;
		}
	}

	public void setSpace(Float spa) {
		if (spa == null)
			space = Float.valueOf(0);
		else
			this.space = spa;
	}

	public Float getBudget() {
		if (budget == null) {
			return Float.valueOf(0);
		} else {
			return this.budget;
		}
	}

	public void setBudget(Float bud) {
		if (bud == null)
			budget = Float.valueOf(0);
		else
			this.budget = bud;
	}

	/**
	 * 设置交房日期
	 * 
	 * @param getRoomDate
	 *            交房日期
	 */
	public void setGetRoomDate(Date getRoomDate) {
		this.getRoomDate = getRoomDate;
	}

	/**
	 * 获取装修方式1.半包2.全包
	 * 
	 * @return method - 装修方式1.半包2.全包
	 */
	public Integer getMethod() {
		return method;
	}

	/**
	 * 设置装修方式1.半包2.全包
	 * 
	 * @param method
	 *            装修方式1.半包2.全包
	 */
	public void setMethod(Integer method) {
		this.method = method;
	}

	/**
	 * 获取预约来源1.商城网站2.400电话
	 * 
	 * @return source - 预约来源1.商城网站2.400电话
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * 设置预约来源1.商城网站2.400电话
	 * 
	 * @param source
	 *            预约来源1.商城网站2.400电话
	 */
	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * 获取跟单员id（系统管理员）
	 * 
	 * @return admin_id - 跟单员id（系统管理员）
	 */
	public Integer getAdminId() {
		return adminId;
	}

	/**
	 * 设置跟单员id（系统管理员）
	 * 
	 * @param adminId
	 *            跟单员id（系统管理员）
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	/**
	 * 获取城市所在域名
	 * 
	 * @return city_domain - 城市所在域名
	 */
	public String getCityDomain() {
		return cityDomain;
	}

	/**
	 * 设置城市所在域名
	 * 
	 * @param cityDomain
	 *            城市所在域名
	 */
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
	 * 获取沟通时间
	 * 
	 * @return link_time - 沟通时间
	 */
	public Date getLinkTime() {
		return linkTime;
	}

	/**
	 * 设置沟通时间
	 * 
	 * @param linkTime
	 *            沟通时间
	 */
	public void setLinkTime(Date linkTime) {
		this.linkTime = linkTime;
	}

	public String getLin() {
		return linkTime == null ? "" : DateUtil.formatDateTime(linkTime);
	}

	/**
	 * 获取签约公司
	 * 
	 * @return company - 签约公司
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 设置签约公司
	 * 
	 * @param company
	 *            签约公司
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 获取关闭原因
	 * 
	 * @return reason - 关闭原因
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 设置关闭原因
	 * 
	 * @param reason
	 *            关闭原因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 获取浏览预约时间
	 * 
	 * @return review_time - 浏览预约时间
	 */
	public Date getReviewTime() {
		return reviewTime;
	}

	public String getSto() {
		return reviewTime == null ? "" : DateUtil.formatDateTime(reviewTime);
	}

	/**
	 * 设置浏览预约时间
	 * 
	 * @param reviewTime
	 *            浏览预约时间
	 */
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	/**
	 * 获取所在小区
	 * 
	 * @return community - 所在小区
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * 设置所在小区
	 * 
	 * @param community
	 *            所在小区
	 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/**
	 * 获取客厅地面:0不改造1铺地板2铺瓷砖
	 * 
	 * @return parlour_ground - 客厅地面:0不改造1铺地板2铺瓷砖
	 */
	public Integer getParlourGround() {
		return parlourGround;
	}

	/**
	 * 设置客厅地面:0不改造1铺地板2铺瓷砖
	 * 
	 * @param parlourGround
	 *            客厅地面:0不改造1铺地板2铺瓷砖
	 */
	public void setParlourGround(Integer parlourGround) {
		this.parlourGround = parlourGround;
	}

	public String getDie() {
		if (this.parlourGround != null) {
			if (this.parlourGround == 0) {
				return "不改造";
			} else if (this.parlourGround == 1) {
				return "铺地板";
			} else {
				return "铺瓷砖";
			}
		}
		return "";
	}

	public String getDieXia() {
		if (this.parlourGroundDismantle != null) {
			if (this.parlourGroundDismantle == 0) {
				return "无拆除项";
			} else if (this.parlourGround == 1) {
				return "拆除旧地板";
			} else {
				return "拆除旧瓷砖";
			}
		}
		return "";
	}

	/**
	 * 获取客厅拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 * 
	 * @return parlour_ground_dismantle - 客厅拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 */
	public Integer getParlourGroundDismantle() {
		return parlourGroundDismantle;
	}

	/**
	 * 设置客厅拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 * 
	 * @param parlourGroundDismantle
	 *            客厅拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 */
	public void setParlourGroundDismantle(Integer parlourGroundDismantle) {
		this.parlourGroundDismantle = parlourGroundDismantle;
	}

	/**
	 * 获取客厅墙面：1刷墙纸2贴壁纸0不改造
	 * 
	 * @return parlour_wall - 客厅墙面：1刷墙纸2贴壁纸0不改造
	 */
	public Integer getParlourWall() {
		return parlourWall;
	}

	public String getQian() {
		if (this.parlourWall != null) {
			if (this.parlourWall == 0) {
				return "无拆除项";
			} else if (this.parlourWall == 1) {
				return "不改造";
			} else {
				return "贴壁纸";
			}
		}
		return "";
	}

	/**
	 * 设置客厅墙面：1刷墙纸2贴壁纸0不改造
	 * 
	 * @param parlourWall
	 *            客厅墙面：1刷墙纸2贴壁纸0不改造
	 */
	public void setParlourWall(Integer parlourWall) {
		this.parlourWall = parlourWall;
	}

	/**
	 * 获取0无拆除项1铲墙皮
	 * 
	 * @return parlour_wall_dismantle - 0无拆除项1铲墙皮
	 */
	public Integer getParlourWallDismantle() {
		return parlourWallDismantle;
	}

	public String getQianPi() {
		if (this.parlourWallDismantle != null) {
			if (this.parlourWallDismantle == 0) {
				return "无拆除项";
			} else {
				return "铲墙皮";
			}
		}
		return "";
	}

	/**
	 * 设置0无拆除项1铲墙皮
	 * 
	 * @param parlourWallDismantle
	 *            0无拆除项1铲墙皮
	 */
	public void setParlourWallDismantle(Integer parlourWallDismantle) {
		this.parlourWallDismantle = parlourWallDismantle;
	}

	/**
	 * 获取卧室地面：0不改造1铺地板2铺瓷砖
	 * 
	 * @return bedroom_ground - 卧室地面：0不改造1铺地板2铺瓷砖
	 */
	public Integer getBedroomGround() {
		return bedroomGround;
	}

	/**
	 * 设置卧室地面：0不改造1铺地板2铺瓷砖
	 * 
	 * @param bedroomGround
	 *            卧室地面：0不改造1铺地板2铺瓷砖
	 */
	public void setBedroomGround(Integer bedroomGround) {
		this.bedroomGround = bedroomGround;
	}

	/**
	 * 获取卧室地面拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 * 
	 * @return bedroom_ground_dismantle - 卧室地面拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 */
	public Integer getBedroomGroundDismantle() {
		return bedroomGroundDismantle;
	}

	/**
	 * 设置卧室地面拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 * 
	 * @param bedroomGroundDismantle
	 *            卧室地面拆除项：0无拆除项1拆除旧地板2拆除旧瓷砖
	 */
	public void setBedroomGroundDismantle(Integer bedroomGroundDismantle) {
		this.bedroomGroundDismantle = bedroomGroundDismantle;
	}

	/**
	 * 获取卧室墙面：0不改造1刷墙漆2贴壁纸
	 * 
	 * @return bedroom_wall - 卧室墙面：0不改造1刷墙漆2贴壁纸
	 */
	public Integer getBedroomWall() {
		return bedroomWall;
	}

	/**
	 * 设置卧室墙面：0不改造1刷墙漆2贴壁纸
	 * 
	 * @param bedroomWall
	 *            卧室墙面：0不改造1刷墙漆2贴壁纸
	 */
	public void setBedroomWall(Integer bedroomWall) {
		this.bedroomWall = bedroomWall;
	}

	/**
	 * 获取卧室墙面拆除项：0无拆除1铲墙皮
	 * 
	 * @return bedroom_wall_dismantle - 卧室墙面拆除项：0无拆除1铲墙皮
	 */
	public Integer getBedroomWallDismantle() {
		return bedroomWallDismantle;
	}

	/**
	 * 设置卧室墙面拆除项：0无拆除1铲墙皮
	 * 
	 * @param bedroomWallDismantle
	 *            卧室墙面拆除项：0无拆除1铲墙皮
	 */
	public void setBedroomWallDismantle(Integer bedroomWallDismantle) {
		this.bedroomWallDismantle = bedroomWallDismantle;
	}

	/**
	 * 获取厨房:0不改造，1铺瓷砖
	 * 
	 * @return kitchen - 厨房:0不改造，1铺瓷砖
	 */
	public Integer getKitchen() {
		return kitchen;
	}

	public String getChuFa() {
		if (this.kitchen != null) {
			if (this.kitchen == 0) {
				return "不改造";
			} else {
				return "铺瓷砖";
			}
		}
		return "";
	}

	/**
	 * 设置厨房:0不改造，1铺瓷砖
	 * 
	 * @param kitchen
	 *            厨房:0不改造，1铺瓷砖
	 */
	public void setKitchen(Integer kitchen) {
		this.kitchen = kitchen;
	}

	/**
	 * 获取拆除旧瓷砖
	 * 
	 * @return kitchen_dismantle - 拆除旧瓷砖
	 */
	public Integer getKitchenDismantle() {
		return kitchenDismantle;
	}

	/**
	 * 设置拆除旧瓷砖
	 * 
	 * @param kitchenDismantle
	 *            拆除旧瓷砖
	 */
	public void setKitchenDismantle(Integer kitchenDismantle) {
		this.kitchenDismantle = kitchenDismantle;
	}

	/**
	 * 获取卫生间：0不改造
	 * 
	 * @return toilet - 卫生间：0不改造
	 */
	public Integer getToilet() {
		return toilet;
	}

	/**
	 * 设置卫生间：0不改造
	 * 
	 * @param toilet
	 *            卫生间：0不改造
	 */
	public void setToilet(Integer toilet) {
		this.toilet = toilet;
	}

	public String getWei() {
		if (this.toilet != null) {
			if (this.toilet == 0) {
				return "不改造";
			}
		}
		return "";
	}

	public String getWeiXi() {
		if (this.toiletDismantle != null) {
			if (this.toiletDismantle == 0) {
				return "无拆除";
			} else {
				return "拆除旧瓷砖";
			}
		}
		return "";
	}

	/**
	 * 获取卫生间拆除项：0无拆除1拆除旧瓷砖
	 * 
	 * @return toilet_dismantle - 卫生间拆除项：0无拆除1拆除旧瓷砖
	 */
	public Integer getToiletDismantle() {
		return toiletDismantle;
	}

	/**
	 * 设置卫生间拆除项：0无拆除1拆除旧瓷砖
	 * 
	 * @param toiletDismantle
	 *            卫生间拆除项：0无拆除1拆除旧瓷砖
	 */
	public void setToiletDismantle(Integer toiletDismantle) {
		this.toiletDismantle = toiletDismantle;
	}

	/**
	 * 获取水路改造：0不改造1局部改造2全面改造
	 * 
	 * @return water - 水路改造：0不改造1局部改造2全面改造
	 */
	public Integer getWater() {
		return water;
	}

	public String getShuiLu() {
		if (this.water != null) {
			if (this.water == 0) {
				return "不改造";
			} else if (this.water == 1) {
				return "局部改造";
			} else {
				return "全面改造";
			}
		}
		return "不改造";
	}

	/**
	 * 设置水路改造：0不改造1局部改造2全面改造
	 * 
	 * @param water
	 *            水路改造：0不改造1局部改造2全面改造
	 */
	public void setWater(Integer water) {
		this.water = water;
	}

	/**
	 * 获取电路改造：0不改造1全面改造
	 * 
	 * @return electric - 电路改造：0不改造1全面改造
	 */
	public Integer getElectric() {
		return electric;
	}

	public String getDianLu() {
		if (this.electric != null) {
			if (this.electric == 1) {
				return "全面改造";
			} else {
				return "不改造";
			}
		}
		return "";
	}

	/**
	 * 设置电路改造：0不改造1全面改造
	 * 
	 * @param electric
	 *            电路改造：0不改造1全面改造
	 */
	public void setElectric(Integer electric) {
		this.electric = electric;
	}

	/**
	 * 获取预计开工
	 * 
	 * @return start_work - 预计开工
	 */
	public Date getStartWork() {
		return startWork;
	}

	/**
	 * 设置预计开工
	 * 
	 * @param startWork
	 *            预计开工
	 */
	public void setStartWork(Date startWork) {
		this.startWork = startWork;
	}

	/**
	 * 获取x居室
	 * 
	 * @return ht_bedroom - x居室
	 */
	public Integer getHtBedroom() {
		if (this.htBedroom != null)
			return htBedroom;
		else {
			return 0;
		}
	}

	/**
	 * 设置x居室
	 * 
	 * @param htBedroom
	 *            x居室
	 */
	public void setHtBedroom(Integer htBedroom) {
		this.htBedroom = htBedroom;
	}

	/**
	 * 获取x厅
	 * 
	 * @return ht_living_room - x厅
	 */
	public Integer getHtLivingRoom() {
		return htLivingRoom;
	}

	/**
	 * 设置x厅
	 * 
	 * @param htLivingRoom
	 *            x厅
	 */
	public void setHtLivingRoom(Integer htLivingRoom) {
		this.htLivingRoom = htLivingRoom;
	}

	/**
	 * 获取x厨房
	 * 
	 * @return ht_kitchen - x厨房
	 */
	public Integer getHtKitchen() {
		return htKitchen;
	}

	/**
	 * 设置x厨房
	 * 
	 * @param htKitchen
	 *            x厨房
	 */
	public void setHtKitchen(Integer htKitchen) {
		this.htKitchen = htKitchen;
	}

	/**
	 * 获取x卫生间
	 * 
	 * @return ht_toilet - x卫生间
	 */
	public Integer getHtToilet() {
		return htToilet;
	}

	/**
	 * 设置x卫生间
	 * 
	 * @param htToilet
	 *            x卫生间
	 */
	public void setHtToilet(Integer htToilet) {
		this.htToilet = htToilet;
	}

	/**
	 * 获取select_push_id
	 * 
	 * @return select_push_id - select_push_id
	 */
	public Integer getSelectPushId() {
		return selectPushId;
	}

	/**
	 * 设置select_push_id
	 * 
	 * @param selectPushId
	 *            select_push_id
	 */
	public void setSelectPushId(Integer selectPushId) {
		this.selectPushId = selectPushId;
	}

	/**
	 * 获取报价
	 * 
	 * @return payment - 报价
	 */
	public BigDecimal getPayment() {
		if (payment == null) {
			return BigDecimal.valueOf(0);
		} else {
			return this.payment;
		}
	}

	/**
	 * 设置报价
	 * 
	 * @param payment
	 *            报价
	 */
	public void setPayment(BigDecimal pay) {
		if (pay == null)
			payment = BigDecimal.valueOf(0);
		else
			this.payment = pay;
	}

	/**
	 * 获取付款时间
	 * 
	 * @return pay_time - 付款时间
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * 设置付款时间
	 * 
	 * @param payTime
	 *            付款时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * 获取淘宝交易编号
	 * 
	 * @return trade_no - 淘宝交易编号
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * 设置淘宝交易编号
	 * 
	 * @param tradeNo
	 *            淘宝交易编号
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * 获取支付方式1易宝支付2支付宝
	 * 
	 * @return pay_type - 支付方式1易宝支付2支付宝
	 */
	public Integer getPayType() {
		return payType;
	}

	/**
	 * 设置支付方式1易宝支付2支付宝
	 * 
	 * @param payType
	 *            支付方式1易宝支付2支付宝
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 * 获取网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
	 * 
	 * @return bank_gateway - 网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
	 */
	public String getBankGateway() {
		return bankGateway;
	}

	/**
	 * 设置网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
	 * 
	 * @param bankGateway
	 *            网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
	 */
	public void setBankGateway(String bankGateway) {
		this.bankGateway = bankGateway;
	}

	/**
	 * 获取订单完成时间
	 * 
	 * @return finish_time - 订单完成时间
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * 设置订单完成时间
	 * 
	 * @param finishTime
	 *            订单完成时间
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * 获取订单开启时间
	 * 
	 * @return enable_time - 订单开启时间
	 */
	public Date getEnableTime() {
		return enableTime;
	}

	/**
	 * 设置订单开启时间
	 * 
	 * @param enableTime
	 *            订单开启时间
	 */
	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	public String getSubTypeName() {
		return SUBTYPE_MAP.get(subType);
	}

	public String getStatusName() {
		return STATUS_MAP.get(status);
	}

	public String getZhuangXiu() {
		return SUBTYPE_MAP.get(method);
	}

	public String getSourceName() {
		return SOURCE_MAP.get(source);
	}

	// 预约状态：0流单10待抢单20待量房30待报价40待签约50待开工，此时表示已签约，开始建立工程单，工程单与监理进行关联（选监理）
	// 60施工中70已完工80暂停90关闭
	public static final int STATUS_LOSE = 0;
	public static final int STATUS_DRAFT = 1;
	public static final int STATUS_WREST = 10;
	public static final int STATUS_MEASURE = 20;
	public static final int STATUS_OFFER = 30;
	public static final int STATUS_CONTRACT = 40;
	public static final int STATUS_START = 50;
	public static final int STATUS_BUILD = 60;
	public static final int STATUS_OVER = 70;
	public static final int STATUS_SUSPEND = 80;
	public static final int STATUS_CLOSE = 90;
	public static final int STATUS_HANDSEL = 100;
	public static final int STATUS_REFUND = 45;
	public static final Map<Integer, String> STATUS_MAP = new HashMap<Integer, String>() {
		public static final long serialVersionUID = 1L;
		{
			put(STATUS_LOSE, "流单");
			put(STATUS_DRAFT, "草稿");
			put(STATUS_WREST, "待抢单");
			put(STATUS_MEASURE, "待量房");
			put(STATUS_OFFER, "待报价");
			put(STATUS_CONTRACT, "待签约");
			put(STATUS_START, "待开工");
			put(STATUS_BUILD, "施工中");
			put(STATUS_OVER, "已完工");
			put(STATUS_SUSPEND, "暂停");
			put(STATUS_CLOSE, "已关闭");
			put(STATUS_HANDSEL, "结束抢单");
			put(STATUS_REFUND, "退款中");
		}
	};

	// 1.商城网站2.400电话
	public static final int SOURCE_SHOP = 1;
	public static final int SOURCE_400 = 2;
	public static final Map<Integer, String> SOURCE_MAP = new HashMap<Integer, String>() {
		public static final long serialVersionUID = 1L;
		{
			put(SOURCE_SHOP, "商城网站");
			put(SOURCE_400, "400电话");
		}
	};

	public String getIson() {
		if (this.source != null) {
			if (this.source == 1) {
				return "商城网站";
			} else {
				return "400电话";
			}
		}
		return "";
	}

	public String getIstrue() {
		if (this.getRoom != null) {
			if (this.getRoom == 1) {
				return "是";
			} else {
				return "否";
			}
		}
		return "";
	}

	// 1新房装修2旧房翻新
	public static final int SUBTYPE_NEW = 1;
	public static final int SUBTYPE_OLD = 2;
	public static final Map<Integer, String> SUBTYPE_MAP = new HashMap<Integer, String>() {
		public static final long serialVersionUID = 1L;
		{
			put(SUBTYPE_NEW, "新房装修");
			put(SUBTYPE_OLD, "旧房翻新");
		}
	};

	public String getCreateTimeHandle() {
		if (createTime != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}


	public String getWholeHouseValll() {
		if (this.getWholeHouse() != null) {
			if (wholeHouse == 1) {
				return "整装";
			}
			if (wholeHouse == 2) {
				return "局部";
			}
			return "";
		}
		return "";
	}

	public String getTypeVal(){
		if(this.getType()!=null){
			if(type==3){
				return "微信活动预约单";
			}
			if(type==6){
				return "web预约单";
			}
			if(type==5){
				return "微信引流活动预约单";
			}
			return "APP预约单";
		}
		return "";
	}
	
	//用户提交的量房参考时间
	public String getReviewTimeHandle(){
		if(this.getReviewTime()!=null){
			return DateUtil.formatDateTime(reviewTime);
		}
		return "";
	}
	public String getReviewTimeHandle2(){
		if(this.getReviewTime()!=null){
			return DateUtil.formatDate(reviewTime);
		}
		return "";
	}
	
	
	//房屋用途
	public String getHouseTypeVal(){
		if(this.getHouseType()!=null){
			int type=CommonUtil.stringToInteger(this.getHouseType());
			switch (type) {
			case 1:
				return "出租";
			case 2:
				return "自用";
			case 3:
				return "婚房";
			case 4:
				return "儿童房";
			case 5:
				return "会所";
			case 6:
				return "工装";
			default:
				return "";
			}
		}
		return "";
	}
	
	//新旧房
	public String getSuTypeVal(){
		if(this.getSubType()!=null){
			if(suType==1){
				return "新房";
			}
			if(suType==2){
				return "旧房";
			}
			return "";
		}
		return "";
	}
	
	//装修时间
	public String getZxTimeHandle(){
		if(this.getZxTime()!=null){
			return DateUtil.formatDateTime(zxTime);
		}
		return "";
	}
	
	//装修方式
	public String getMethodValll(){
		if(this.getMethod()!=null){
			if(method==1){
				return "半包";
			}
			if(method==2){
				return "全包";
			}
			return "";
		}
		return "";
	}
}