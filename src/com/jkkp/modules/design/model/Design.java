package com.jkkp.modules.design.model;

import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "design")
public class Design {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public Integer staffid;//设计师id查询company_staff表
	public String remark;
	public String community;
	//装修时间;1工作日,2自然日
	@Column(name="work_time")
	public Integer workTime;
	//开工工期
	@Column(name="start_work")
	public Integer startWork;
	//水电工期
	@Column(name="white_fuel")
	public Integer whiteFuel;
	//瓦木工期
	@Column(name="tile_wood")
	public Integer tileWood;
	//竣工工期
	@Column(name="completion")
	public Integer completion;
	//合同id
	@Column(name="agreement_id")
	public Integer agreementId;

	
	
	
	
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 设计名称
	 */
	public Integer aid;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "design_name")
	public String designName;

	/**
	 * 商家id
	 */
	@Column(name = "sp_id")
	public Integer spId;

	/**
	 * 工长姓名
	 */
	public String forman;

	/**
	 * 设计师名
	 */
	public String designer;

	/**
	 * 设计公司名称
	 */
	public String company;

	/**
	 * 图片数量
	 */
	@Column(name = "image_num")
	public Integer imageNum;

	/**
	 * 户型分类id
	 */
	public Integer huxing;

	/**
	 * 空间分类id
	 */
	public Integer kongjian;

	/**
	 * 风格分类id
	 */
	public Integer fengge;

	/**
	 * 审核状态：0待审核1通过-1不通过
	 */
	public Byte status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	public Date createTime;

	/**
	 * 冻结状态，0否1冻结
	 */
	@Column(name = "is_lock")
	public Byte isLock;

	/**
	 * 封面id
	 */
	public Integer pid;

	/**
	 * 所在城市域名
	 */
	@Column(name = "city_domain")
	public String cityDomain;

	/**
	 * 显示名称：company|designer|forman，多个逗号分开
	 */
	@Column(name = "display_name")
	public String displayName;

	/**
	 * 如果uid不为0，则是前台用户上传
	 */
	public Integer uid;

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
	 * 获取设计名称
	 * 
	 * @return design_name - 设计名称
	 */
	public String getDesignName() {
		return designName == null ? "" : designName;
	}

	/**
	 * 设置设计名称
	 * 
	 * @param designName
	 *            设计名称
	 */
	public void setDesignName(String designName) {
		this.designName = designName;
	}

	public String getCre() {
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
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
	 * 获取工长姓名
	 * 
	 * @return forman - 工长姓名
	 */
	public String getForman() {
		return forman;
	}

	/**
	 * 设置工长姓名
	 * 
	 * @param forman
	 *            工长姓名
	 */
	public void setForman(String forman) {
		this.forman = forman;
	}

	/**
	 * 获取设计师名
	 * 
	 * @return designer - 设计师名
	 */
	public String getDesigner() {
		return designer;
	}

	/**
	 * 设置设计师名
	 * 
	 * @param designer
	 *            设计师名
	 */
	public void setDesigner(String designer) {
		this.designer = designer;
	}

	/**
	 * 获取设计公司名称
	 * 
	 * @return company - 设计公司名称
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 设置设计公司名称
	 * 
	 * @param company
	 *            设计公司名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 获取图片数量
	 * 
	 * @return image_num - 图片数量
	 */
	public Integer getImageNum() {
		return imageNum;
	}

	/**
	 * 设置图片数量
	 * 
	 * @param imageNum
	 *            图片数量
	 */
	public void setImageNum(Integer imageNum) {
		this.imageNum = imageNum;
	}

	/**
	 * 获取户型分类id
	 * 
	 * @return huxing - 户型分类id
	 */
	public Integer getHuxing() {
		return huxing;
	}

	/**
	 * 设置户型分类id
	 * 
	 * @param huxing
	 *            户型分类id
	 */
	public void setHuxing(Integer huxing) {
		this.huxing = huxing;
	}

	/**
	 * 获取空间分类id
	 * 
	 * @return kongjian - 空间分类id
	 */
	public Integer getKongjian() {
		return kongjian;
	}

	/**
	 * 设置空间分类id
	 * 
	 * @param kongjian
	 *            空间分类id
	 */
	public void setKongjian(Integer kongjian) {
		this.kongjian = kongjian;
	}
    public Integer bidding;
    public Float budget;
    public Float space;
    public Integer method;
    @Column(name = "su_type")
	public String suType;
    public Float duration;
    
	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

	public Integer getStartWork() {
		return startWork;
	}

	public void setStartWork(Integer startWork) {
		this.startWork = startWork;
	}

	public Integer getWhiteFuel() {
		return whiteFuel;
	}

	public void setWhiteFuel(Integer whiteFuel) {
		this.whiteFuel = whiteFuel;
	}

	public Integer getTileWood() {
		return tileWood;
	}

	public void setTileWood(Integer tileWood) {
		this.tileWood = tileWood;
	}

	public Integer getCompletion() {
		return completion;
	}

	public void setCompletion(Integer completion) {
		this.completion = completion;
	}

	public Integer getBidding() {
		return bidding;
	}

	public void setBidding(Integer bidding) {
		this.bidding = bidding;
	}

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Float getSpace() {
		return space;
	}

	public void setSpace(Float space) {
		this.space = space;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public String getSuType() {
		return suType;
	}

	public void setSuType(String suType) {
		this.suType = suType;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	/**
	 * 获取风格分类id
	 * 
	 * @return fengge - 风格分类id
	 */
	public Integer getFengge() {
		return fengge;
	}

	/**
	 * 设置风格分类id
	 * 
	 * @param fengge
	 *            风格分类id
	 */
	public void setFengge(Integer fengge) {
		this.fengge = fengge;
	}

	/**
	 * 获取审核状态：0待审核1通过-1不通过
	 * 
	 * @return status - 审核状态：0待审核1通过-1不通过
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置审核状态：0待审核1通过-1不通过
	 * 
	 * @param status
	 *            审核状态：0待审核1通过-1不通过
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getIsOperate() {
		if (this.status != null) {
			if (this.status == 0) {
				return "待审核";
			} else if (this.status == -1) {
				return "不通过";
			} else {
				return "通过";
			}
		}
		return "";
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
	 * 获取冻结状态，0否1冻结
	 * 
	 * @return is_lock - 冻结状态，0否1冻结
	 */
	public Byte getIsLock() {
		return isLock;
	}

	/**
	 * 设置冻结状态，0否1冻结
	 * 
	 * @param isLock
	 *            冻结状态，0否1冻结
	 */
	public void setIsLock(Byte isLock) {
		this.isLock = isLock;
	}

	/**
	 * 获取封面id
	 * 
	 * @return pid - 封面id
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * 设置封面id
	 * 
	 * @param pid
	 *            封面id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
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
	 * 获取显示名称：company|designer|forman，多个逗号分开
	 * 
	 * @return display_name - 显示名称：company|designer|forman，多个逗号分开
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 设置显示名称：company|designer|forman，多个逗号分开
	 * 
	 * @param displayName
	 *            显示名称：company|designer|forman，多个逗号分开
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 获取如果uid不为0，则是前台用户上传
	 * 
	 * @return uid - 如果uid不为0，则是前台用户上传
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * 设置如果uid不为0，则是前台用户上传
	 * 
	 * @param uid
	 *            如果uid不为0，则是前台用户上传
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getStatusVal() {
		if (this.getStatus() != null) {
			if (status == 1) {
				return "通过";
			}
			if (status == -1) {
				return "不通过";
			}
			if (status == 0) {
				return "待审核";
			}
			return "";
		}
		return "";
	}

	// 创建时间
	public String getCreateTimeHandle() {
		if (this.getCreateTime() != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}

	public String getZd() {
		if (this.isLock != null) {
			if (this.isLock == 0) {
				return "正常";
			} else {
				return "冻结";
			}
		}
		return "";
	}
}