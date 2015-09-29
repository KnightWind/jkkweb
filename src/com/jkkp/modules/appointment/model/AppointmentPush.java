package com.jkkp.modules.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "appointment_push")
public class AppointmentPush {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private Float money;
    private Integer changNoC;
    private Integer changNoB;
    
    //add 20150814
  //定金支付状态 0未支付，1已支付，2已退款
	@Column(name="deposit_pay_status")
	private Integer depositPayStatus;

	//定金支付时间（如果允许多次支付，为最新的支付时间）
	@Column(name="deposit_pay_time")
	private Date depositPayTime;

	//支付日志记录id
	@Column(name="deposit_pay_rec_id")
	private Integer depositPayRecId;

	//
	@Column(name="deposit_refund_time")
	private Date depositRefundTime;

	//退款日志记录id
	@Column(name="deposit_refund_rec_id")
	private Integer depositRefundRecId;

	//工程款支付状态 0未支付 1已支付 2已退款
	@Column(name="project_pay_status")
	private Integer projectPayStatus;

	//工程款支付时间
	@Column(name="project_pay_time")
	private Date projectPayTime;

	//支付日志记录id
	@Column(name="project_pay_rec_id")
	private Integer projectPayRecId;

	//
	@Column(name="project_refund_time")
	private Date projectRefundTime;

	//退款日志记录id
	@Column(name="project_refund_rec_id")
	private Integer projectRefundRecId;
  		//---
    
	
	
	
	
	
	
	public Integer getChangNoC() {
		return changNoC;
	}

	public Integer getDepositPayStatus() {
		return depositPayStatus;
	}

	public void setDepositPayStatus(Integer depositPayStatus) {
		this.depositPayStatus = depositPayStatus;
	}

	public Date getDepositPayTime() {
		return depositPayTime;
	}

	public void setDepositPayTime(Date depositPayTime) {
		this.depositPayTime = depositPayTime;
	}

	public Integer getDepositPayRecId() {
		return depositPayRecId;
	}

	public void setDepositPayRecId(Integer depositPayRecId) {
		this.depositPayRecId = depositPayRecId;
	}

	public Date getDepositRefundTime() {
		return depositRefundTime;
	}

	public void setDepositRefundTime(Date depositRefundTime) {
		this.depositRefundTime = depositRefundTime;
	}


	public Integer getDepositRefundRecId() {
		return depositRefundRecId;
	}

	public void setDepositRefundRecId(Integer depositRefundRecId) {
		this.depositRefundRecId = depositRefundRecId;
	}

	public Integer getProjectPayStatus() {
		return projectPayStatus;
	}

	public void setProjectPayStatus(Integer projectPayStatus) {
		this.projectPayStatus = projectPayStatus;
	}

	public Date getProjectPayTime() {
		return projectPayTime;
	}

	public void setProjectPayTime(Date projectPayTime) {
		this.projectPayTime = projectPayTime;
	}

	public Integer getProjectPayRecId() {
		return projectPayRecId;
	}

	public void setProjectPayRecId(Integer projectPayRecId) {
		this.projectPayRecId = projectPayRecId;
	}

	public Date getProjectRefundTime() {
		return projectRefundTime;
	}

	public void setProjectRefundTime(Date projectRefundTime) {
		this.projectRefundTime = projectRefundTime;
	}

	public Integer getProjectRefundRecId() {
		return projectRefundRecId;
	}

	public void setProjectRefundRecId(Integer projectRefundRecId) {
		this.projectRefundRecId = projectRefundRecId;
	}

	public void setChangNoC(Integer changNoC) {
		this.changNoC = changNoC;
	}

	public Integer getChangNoB() {
		return changNoB;
	}

	public void setChangNoB(Integer changNoB) {
		this.changNoB = changNoB;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	/**
	 * 预约id
	 */
	private Integer aid;
	/**
	 * 商家取消量房的次数
	 */
	@Column(name = "supplier_count")
	private Integer supplierCount;

	public Integer getSupplierCount() {
		return supplierCount;
	}

	public void setSupplierCount(Integer supplierCount) {
		this.supplierCount = supplierCount;
	}

	/**
	 * 取消预约原因
	 */
	private String reason;

	public String getReason() {
		if(reason==null){
			return "";
		}
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getAmountTime() {
		return amountTime;
	}

	public void setAmountTime(Date amountTime) {
		this.amountTime = amountTime;
	}
    private Integer random;
    
	public Integer getRandom() {
		return random;
	}

	public void setRandom(Integer random) {
		this.random = random;
	}

	/**
	 * 商家id
	 */
	@Column(name = "sp_id")
	private Integer spId;
	@Column(name = "send_collect_state")
	private Integer sendCollectState;
	
	public Integer getSendCollectState() {
		return sendCollectState;
	}

	public void setSendCollectState(Integer sendCollectState) {
		this.sendCollectState = sendCollectState;
	}

	/**
	 * 提醒状态
	 */
	private Integer reminder;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 量房时间
	 */
	@Column(name = "amount_time")
	private Date amountTime;
	/**
	 * 应单时间
	 */
	@Column(name = "single_time")
	private Date singleTime;

	public Integer getReminder() {
		return reminder;
	}

	public void setReminder(Integer reminder) {
		this.reminder = reminder;
	}

	public Date getSingleTime() {
		return singleTime;
	}

	public void setSingleTime(Date singleTime) {
		this.singleTime = singleTime;
	}

	/**
	 * 状态：0未查看1已查看
	 */
	private Byte status;

	/**
	 * 下发客服
	 */
	@Column(name = "admin_id")
	private Integer adminId;

	/**
	 * 报价
	 */
	private Double quote;

	/**
	 * 客厅地面报价
	 */
	@Column(name = "parlour_ground_price")
	private Integer parlourGroundPrice;

	/**
	 * 客厅墙面报价
	 */
	@Column(name = "parlour_wall_price")
	private Integer parlourWallPrice;

	/**
	 * 卧室地面报价
	 */
	@Column(name = "bedroom_ground_price")
	private Integer bedroomGroundPrice;

	/**
	 * 卧室墙面报价
	 */
	@Column(name = "bedroom_wall_price")
	private Integer bedroomWallPrice;

	/**
	 * 厨房报价
	 */
	@Column(name = "kitchen_price")
	private Integer kitchenPrice;

	/**
	 * 卫生间报价
	 */
	@Column(name = "toilet_price")
	private Integer toiletPrice;

	/**
	 * 水改报价
	 */
	@Column(name = "water_price")
	private Integer waterPrice;

	/**
	 * 电改报价
	 */
	@Column(name = "electric_price")
	private Integer electricPrice;

	/**
	 * 其他项目
	 */
	@Column(name = "other_option")
	private String otherOption;

	/**
	 * 其他价格
	 */
	@Column(name = "other_price")
	private Integer otherPrice;

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

	public String getTime() {
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}

	/**
	 * 获取预约id
	 * 
	 * @return aid - 预约id
	 */
	public Integer getAid() {
		return aid;
	}

	/**
	 * 设置预约id
	 * 
	 * @param aid
	 *            预约id
	 */
	public void setAid(Integer aid) {
		this.aid = aid;
	}

	/**
	 * 获取商家id
	 * 
	 * @return sp_id - 商家id
	 */
	public Integer getSpId() {
		return spId;
	}

	/**
	 * 设置商家id
	 * 
	 * @param spId
	 *            商家id
	 */
	public void setSpId(Integer spId) {
		this.spId = spId;
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
	 * 获取状态：0未查看1已查看
	 * 
	 * @return status - 状态：0未查看1已查看
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置状态：0未查看1已查看
	 * 
	 * @param status
	 *            状态：0未查看1已查看
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getZt() {
		if (this.status != null) {
			if (this.status == 0) {
				return "未查看";
			} else {
				return "已查看";
			}
		}
		return "";
	}

	/**
	 * 获取下发客服
	 * 
	 * @return admin_id - 下发客服
	 */
	public Integer getAdminId() {
		return adminId;
	}

	/**
	 * 设置下发客服
	 * 
	 * @param adminId
	 *            下发客服
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	/**
	 * 获取报价
	 * 
	 * @return quote - 报价
	 */
	public Double getQuote() {
		if(this.quote==null)
			this.quote=(double) 0;
		return quote;
	}

	/**
	 * 设置报价
	 * 
	 * @param quote
	 *            报价
	 */
	public void setQuote(Double quote) {
		this.quote = quote;
	}

	/**
	 * 获取客厅地面报价
	 * 
	 * @return parlour_ground_price - 客厅地面报价
	 */
	public Integer getParlourGroundPrice() {
		return parlourGroundPrice;
	}

	/**
	 * 设置客厅地面报价
	 * 
	 * @param parlourGroundPrice
	 *            客厅地面报价
	 */
	public void setParlourGroundPrice(Integer parlourGroundPrice) {
		this.parlourGroundPrice = parlourGroundPrice;
	}

	/**
	 * 获取客厅墙面报价
	 * 
	 * @return parlour_wall_price - 客厅墙面报价
	 */
	public Integer getParlourWallPrice() {
		return parlourWallPrice;
	}

	/**
	 * 设置客厅墙面报价
	 * 
	 * @param parlourWallPrice
	 *            客厅墙面报价
	 */
	public void setParlourWallPrice(Integer parlourWallPrice) {
		this.parlourWallPrice = parlourWallPrice;
	}

	/**
	 * 获取卧室地面报价
	 * 
	 * @return bedroom_ground_price - 卧室地面报价
	 */
	public Integer getBedroomGroundPrice() {
		return bedroomGroundPrice;
	}

	/**
	 * 设置卧室地面报价
	 * 
	 * @param bedroomGroundPrice
	 *            卧室地面报价
	 */
	public void setBedroomGroundPrice(Integer bedroomGroundPrice) {
		this.bedroomGroundPrice = bedroomGroundPrice;
	}

	/**
	 * 获取卧室墙面报价
	 * 
	 * @return bedroom_wall_price - 卧室墙面报价
	 */
	public Integer getBedroomWallPrice() {
		return bedroomWallPrice;
	}

	/**
	 * 设置卧室墙面报价
	 * 
	 * @param bedroomWallPrice
	 *            卧室墙面报价
	 */
	public void setBedroomWallPrice(Integer bedroomWallPrice) {
		this.bedroomWallPrice = bedroomWallPrice;
	}

	/**
	 * 获取厨房报价
	 * 
	 * @return kitchen_price - 厨房报价
	 */
	public Integer getKitchenPrice() {
		return kitchenPrice;
	}

	/**
	 * 设置厨房报价
	 * 
	 * @param kitchenPrice
	 *            厨房报价
	 */
	public void setKitchenPrice(Integer kitchenPrice) {
		this.kitchenPrice = kitchenPrice;
	}

	/**
	 * 获取卫生间报价
	 * 
	 * @return toilet_price - 卫生间报价
	 */
	public Integer getToiletPrice() {
		return toiletPrice;
	}

	/**
	 * 设置卫生间报价
	 * 
	 * @param toiletPrice
	 *            卫生间报价
	 */
	public void setToiletPrice(Integer toiletPrice) {
		this.toiletPrice = toiletPrice;
	}

	/**
	 * 获取水改报价
	 * 
	 * @return water_price - 水改报价
	 */
	public Integer getWaterPrice() {
		return waterPrice;
	}

	/**
	 * 设置水改报价
	 * 
	 * @param waterPrice
	 *            水改报价
	 */
	public void setWaterPrice(Integer waterPrice) {
		this.waterPrice = waterPrice;
	}

	/**
	 * 获取电改报价
	 * 
	 * @return electric_price - 电改报价
	 */
	public Integer getElectricPrice() {
		return electricPrice;
	}

	/**
	 * 设置电改报价
	 * 
	 * @param electricPrice
	 *            电改报价
	 */
	public void setElectricPrice(Integer electricPrice) {
		this.electricPrice = electricPrice;
	}

	/**
	 * 获取其他项目
	 * 
	 * @return other_option - 其他项目
	 */
	public String getOtherOption() {
		return otherOption;
	}

	/**
	 * 设置其他项目
	 * 
	 * @param otherOption
	 *            其他项目
	 */
	public void setOtherOption(String otherOption) {
		this.otherOption = otherOption;
	}

	/**
	 * 获取其他价格
	 * 
	 * @return other_price - 其他价格
	 */
	public Integer getOtherPrice() {
		return otherPrice;
	}

	/**
	 * 设置其他价格
	 * 
	 * @param otherPrice
	 *            其他价格
	 */
	public void setOtherPrice(Integer otherPrice) {
		this.otherPrice = otherPrice;
	}

	public String getStatusName() {
		if (status != null) {
			if (status == 0) {
				return "待抢单";
			}
			if (status == 10) {
				return "已抢";
			}
			if (status == 11) {
				return "取消";
			}
			if (status == 15) {
				return "待应单";
			}
			if (status == 20) {
				return "预约量房";
			}
			if (status == 30) {
				return "已量房";
			}
			if (status == 40) {
				return "已签约,待付款";
			}
			if (status == 50) {
				return "已付款";
			}
			if(status==60){
				return "已付定金";
			}
			if(status==70){
				return "已结束";
			}
			return "未知状态";
		}
		return "未知状态";
	}

	// 创建时间
	public String getCreateTimeHandle() {
		if (createTime != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}

	// 应单时间
	public String getSingleTimeHandle() {
		if (singleTime != null) {
			return DateUtil.formatDateTime(singleTime);
		}
		return "";
	}

	// 量房时间
	public String getAmountTimeHandle() {
		if (amountTime != null) {
			return DateUtil.formatDateTime(amountTime);
		}
		return "";
	}

}