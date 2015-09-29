package com.jkkp.modules.appointment.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "jlappointment_push")
public class JlappointmentPush {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    /**
     * 预约id
     */
    public Integer aid;

    /**
     *取消预约原因
     */
    public String reason;
    public String getReason() {
		return reason == null ? "" : reason;
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
     
	public Integer random;
	/**
     * 商家id
     */
    @Column(name = "sp_id")
    public Integer spId;
    public Integer getRandom() {
		return random;
	}

	public void setRandom(Integer random) {
		this.random = random;
	}

	/**
     * 提醒状态
     */
    public Integer reminder;
    
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    public Date createTime;
    /**
     * 量房时间
     */
    @Column(name = "amount_time")
    public Date amountTime;
    /**
     * 应单时间
     */
    @Column(name = "single_time")
    public Date singleTime;
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
    public Byte status;

    /**
     * 下发客服
     */
    @Column(name = "admin_id")
    public Integer adminId;

    /**
     * 报价
     */
    public BigDecimal quote;

    /**
     * 客厅地面报价
     */
    @Column(name = "parlour_ground_price")
    public Integer parlourGroundPrice;

    /**
     * 客厅墙面报价
     */
    @Column(name = "parlour_wall_price")
    public Integer parlourWallPrice;

    /**
     * 卧室地面报价
     */
    @Column(name = "bedroom_ground_price")
    public Integer bedroomGroundPrice;

    /**
     * 卧室墙面报价
     */
    @Column(name = "bedroom_wall_price")
    public Integer bedroomWallPrice;

    /**
     * 厨房报价
     */
    @Column(name = "kitchen_price")
    public Integer kitchenPrice;

    /**
     * 卫生间报价
     */
    @Column(name = "toilet_price")
    public Integer toiletPrice;

    /**
     * 水改报价
     */
    @Column(name = "water_price")
    public Integer waterPrice;

    /**
     * 电改报价
     */
    @Column(name = "electric_price")
    public Integer electricPrice;

    /**
     * 其他项目
     */
    @Column(name = "other_option")
    public String otherOption;

    /**
     * 其他价格
     */
    @Column(name = "other_price")
    public Integer otherPrice;

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
		return createTime== null ? "" : DateUtil.formatDateTime(createTime);
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
     * @param aid 预约id
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
     * @param spId 商家id
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
     * @param createTime 创建时间
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
     * @param status 状态：0未查看1已查看
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
    public String getZt(){
    	if(this.status!=null){
    		if(this.status==0){
    			return "未查看";
    		}else{
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
     * @param adminId 下发客服
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取报价
     *
     * @return quote - 报价
     */
    public BigDecimal getQuote() {
        return quote;
    }

    /**
     * 设置报价
     *
     * @param quote 报价
     */
    public void setQuote(BigDecimal quote) {
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
     * @param parlourGroundPrice 客厅地面报价
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
     * @param parlourWallPrice 客厅墙面报价
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
     * @param bedroomGroundPrice 卧室地面报价
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
     * @param bedroomWallPrice 卧室墙面报价
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
     * @param kitchenPrice 厨房报价
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
     * @param toiletPrice 卫生间报价
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
     * @param waterPrice 水改报价
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
     * @param electricPrice 电改报价
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
     * @param otherOption 其他项目
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
     * @param otherPrice 其他价格
     */
    public void setOtherPrice(Integer otherPrice) {
        this.otherPrice = otherPrice;
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