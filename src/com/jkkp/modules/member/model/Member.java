package com.jkkp.modules.member.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    @Column(name = "reall_name")
    private String reallName;
    @Column(name = "city_domain")
    private String cityDomain;

    public String getCityDomain() {
		return cityDomain;
	}

	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
     * 性别1男2女
     */
    private Byte gender;

    /**
     * 是否结婚：0未填1是2否
     */
    @Column(name = "is_marry")
    private Byte isMarry;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 会员生日
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String certificate;

    /**
     * 消费金额
     */
    @Column(name = "cost_money")
    private Integer costMoney;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 装修风格：1中式2美式3欧式4日式5田园6现代
     */
    private Integer style;

    /**
     * 会员等级
     */
    private Integer level;

    /**
     * 会员头像
     */
    private String avatar;

    /**
     * 升级时间
     */
    @Column(name = "level_up_time")
    private Date levelUpTime;

    /**
     * 支付类型1在线网银支付（目前只有一种）2货到付款3信用卡分期
     */
    @Column(name = "pay_type")
    private Byte payType;

    /**
     * 发票抬头类型1个人2单位
     */
    @Column(name = "receipt_title_type")
    private Byte receiptTitleType;

    /**
     * 发票抬头
     */
    @Column(name = "receipt_title")
    private String receiptTitle;

    /**
     * 发票明细类型1日常用品2办公用品3明细
     */
    @Column(name = "receipt_type")
    private Byte receiptType;

    /**
     * 最后一次推送券的时间
     */
    @Column(name = "push_time")
    private Date pushTime;

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
     * 获取会员昵称
     *
     * @return nickname - 会员昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置会员昵称
     *
     * @param nickname 会员昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取真实姓名
     *
     * @return reall_name - 真实姓名
     */
    public String getReallName() {
        return reallName;
    }

    /**
     * 设置真实姓名
     *
     * @param reallName 真实姓名
     */
    public void setReallName(String reallName) {
        this.reallName = reallName;
    }

    /**
     * 获取性别1男2女
     *
     * @return gender - 性别1男2女
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置性别1男2女
     *
     * @param gender 性别1男2女
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取是否结婚：0未填1是2否
     *
     * @return is_marry - 是否结婚：0未填1是2否
     */
    public Byte getIsMarry() {
        return isMarry;
    }

    /**
     * 设置是否结婚：0未填1是2否
     *
     * @param isMarry 是否结婚：0未填1是2否
     */
    public void setIsMarry(Byte isMarry) {
        this.isMarry = isMarry;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取会员生日
     *
     * @return birthday - 会员生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置会员生日
     *
     * @param birthday 会员生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取身份证号
     *
     * @return certificate - 身份证号
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * 设置身份证号
     *
     * @param certificate 身份证号
     */
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    /**
     * 获取消费金额
     *
     * @return cost_money - 消费金额
     */
    public Integer getCostMoney() {
        return costMoney;
    }

    /**
     * 设置消费金额
     *
     * @param costMoney 消费金额
     */
    public void setCostMoney(Integer costMoney) {
        this.costMoney = costMoney;
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
     * 获取最后登录时间
     *
     * @return login_time - 最后登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param loginTime 最后登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取装修风格：1中式2美式3欧式4日式5田园6现代
     *
     * @return style - 装修风格：1中式2美式3欧式4日式5田园6现代
     */
    public Integer getStyle() {
        return style;
    }

    /**
     * 设置装修风格：1中式2美式3欧式4日式5田园6现代
     *
     * @param style 装修风格：1中式2美式3欧式4日式5田园6现代
     */
    public void setStyle(Integer style) {
        this.style = style;
    }

    /**
     * 获取会员等级
     *
     * @return level - 会员等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置会员等级
     *
     * @param level 会员等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取会员头像
     *
     * @return avatar - 会员头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置会员头像
     *
     * @param avatar 会员头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取升级时间
     *
     * @return level_up_time - 升级时间
     */
    public Date getLevelUpTime() {
        return levelUpTime;
    }

    /**
     * 设置升级时间
     *
     * @param levelUpTime 升级时间
     */
    public void setLevelUpTime(Date levelUpTime) {
        this.levelUpTime = levelUpTime;
    }

    /**
     * 获取支付类型1在线网银支付（目前只有一种）2货到付款3信用卡分期
     *
     * @return pay_type - 支付类型1在线网银支付（目前只有一种）2货到付款3信用卡分期
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * 设置支付类型1在线网银支付（目前只有一种）2货到付款3信用卡分期
     *
     * @param payType 支付类型1在线网银支付（目前只有一种）2货到付款3信用卡分期
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * 获取发票抬头类型1个人2单位
     *
     * @return receipt_title_type - 发票抬头类型1个人2单位
     */
    public Byte getReceiptTitleType() {
        return receiptTitleType;
    }

    /**
     * 设置发票抬头类型1个人2单位
     *
     * @param receiptTitleType 发票抬头类型1个人2单位
     */
    public void setReceiptTitleType(Byte receiptTitleType) {
        this.receiptTitleType = receiptTitleType;
    }

    /**
     * 获取发票抬头
     *
     * @return receipt_title - 发票抬头
     */
    public String getReceiptTitle() {
        return receiptTitle;
    }

    /**
     * 设置发票抬头
     *
     * @param receiptTitle 发票抬头
     */
    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    /**
     * 获取发票明细类型1日常用品2办公用品3明细
     *
     * @return receipt_type - 发票明细类型1日常用品2办公用品3明细
     */
    public Byte getReceiptType() {
        return receiptType;
    }

    /**
     * 设置发票明细类型1日常用品2办公用品3明细
     *
     * @param receiptType 发票明细类型1日常用品2办公用品3明细
     */
    public void setReceiptType(Byte receiptType) {
        this.receiptType = receiptType;
    }

    /**
     * 获取最后一次推送券的时间
     *
     * @return push_time - 最后一次推送券的时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置最后一次推送券的时间
     *
     * @param pushTime 最后一次推送券的时间
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }
    
    //页面拓展类
    @Transient
    String createTimeHandle;
    @Transient
    String loginTimeHandle;
    @Transient
    String genderVal;
    @Transient
    String marryVal;
    @Transient
    String styleVal;
    @Transient
    String birthdayVal;
    
    
   

	public void setStyleVal(String styleVal) {
		this.styleVal = styleVal;
	}

	public void setBirthdayVal(String birthdayVal) {
		this.birthdayVal = birthdayVal;
	}

	public void setCreateTimeHandle(String createTimeHandle) {
		this.createTimeHandle = createTimeHandle;
	}

	public void setLoginTimeHandle(String loginTimeHandle) {
		this.loginTimeHandle = loginTimeHandle;
	}

	public void setGenderVal(String genderVal) {
		this.genderVal = genderVal;
	}

	public void setMarryVal(String marryVal) {
		this.marryVal = marryVal;
	}

	public String getCreateTimeHandle(){
    	if(createTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(createTime);
    	}
    	return "";
    }
    
    public String getLoginTimeHandle(){
    	if(loginTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(loginTime);
    	}
    	return "";
    }
    
    public String getGenderVal(){
    	if(gender!=null){
    		if(gender==1){
    			return "男";
    		}
    		if(gender==2){
    			return "女";
    		}
    	}
    	return "";
    }
    
    public String getMarryVal(){
    	if(isMarry!=null){
    		if(isMarry==1){
    			return "已婚";
    		}
    		if(isMarry==2){
    			return "未婚";
    		}
    		if(isMarry==0){
    			return "未填";
    		}
    	}
    	return "";
    }
    
    public String getStyleVal(){
    	if(style!=null){
    		if(style==1){
    			return "中式";
    		}
    		if(style==2){
    			return "美式";
    		}
    		if(style==3){
    			return "欧式";
    		}
    		if(style==4){
    			return "日式";
    		}
    		if(style==5){
    			return "田园";
    		}
    		if(style==6){
    			return "现代";
    		}
    	}
    	return "";
    }
    
    public String getBirthdayVal(){
    	if(birthday!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(birthday);
    	}
    	return "";
    }
}