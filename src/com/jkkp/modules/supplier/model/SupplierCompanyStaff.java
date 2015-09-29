package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "supplier_company_staff")
public class SupplierCompanyStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer sid;
    
    /**
     * 商户id
     */
    @Column(name = "sp_id")
    public Integer spId;
     
    @Column(name = "sjs_suggest")
    public String sjsSuggest;
    @Column(name = "gz_suggest")
    public String gzSuggest;
    
    @Column(name = "estimate_average")
    public Float estimateAverage;
    
    
    public Float getEstimateAverage() {
		return estimateAverage;
	}

	public void setEstimateAverage(Float estimateAverage) {
		this.estimateAverage = estimateAverage;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSjsSuggest() {
		return sjsSuggest;
	}

	public void setSjsSuggest(String sjsSuggest) {
		this.sjsSuggest = sjsSuggest;
	}

	public String getGzSuggest() {
		return gzSuggest;
	}

	public void setGzSuggest(String gzSuggest) {
		this.gzSuggest = gzSuggest;
	}

	/**
     * 员工头像
     */
    public String avatar;

    /**
     * 员工姓名
     */
    public String name;

    /**
     * 员工职位
     */
    public String job;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
	protected Date createTime;

    public String mobile;
    
    @Column(name="branch_id")
    public Integer branchId;
    
    @Column(name="leader_id")
    public Integer  leaderId;
    
    @Column(name="bank_account")
    public String bankAccount;
    
    @Column(name="account_name")
    public String accountName;
    
    @Column(name="bank_id")
    public Integer bankId;
    
    @Column(name="gain_rate")
    public Float gainRate;
    
    @Column(name="total_sttle_money")
    public Float totalSttleMoney;
    
    public Integer status;
    
    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Float getGainRate() {
		return gainRate;
	}

	public void setGainRate(Float gainRate) {
		this.gainRate = gainRate;
	}

	public Float getTotalSttleMoney() {
		return totalSttleMoney;
	}

	public void setTotalSttleMoney(Float totalSttleMoney) {
		this.totalSttleMoney = totalSttleMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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
     * 获取商户id
     *
     * @return sp_id - 商户id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商户id
     *
     * @param spId 商户id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取员工头像
     *
     * @return avatar - 员工头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置员工头像
     *
     * @param avatar 员工头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取员工姓名
     *
     * @return name - 员工姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置员工姓名
     *
     * @param name 员工姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取员工职位
     *
     * @return job - 员工职位
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置员工职位
     *
     * @param job 员工职位
     */
    public void setJob(String job) {
        this.job = job;
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
    
    
    public SupplierCompanyStaff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierCompanyStaff(Integer id, String name, String mobile,
			Integer branchId, Integer leaderId, String bankAccount,
			String accountName, Integer bankId,Integer spId) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.branchId = branchId;
		this.leaderId = leaderId;
		this.bankAccount = bankAccount;
		this.accountName = accountName;
		this.bankId = bankId;
		this.spId=spId;
	}

}