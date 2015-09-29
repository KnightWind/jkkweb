package com.jkkp.modules.member.model;

import javax.persistence.*;

@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 区
     */
    private Integer distruct;

    /**
     * 城市
     */
    private Integer city;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 是否为默认地址0否1是
     */
    @Column(name = "is_default")
    private Byte isDefault;

    /**
     * 会员id
     */
    private Integer uid;

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
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * 获取区
     *
     * @return distruct - 区
     */
    public Integer getDistruct() {
        return distruct;
    }

    /**
     * 设置区
     *
     * @param distruct 区
     */
    public void setDistruct(Integer distruct) {
        this.distruct = distruct;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public Integer getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * 获取配送地址
     *
     * @return address - 配送地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置配送地址
     *
     * @param address 配送地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * 获取邮编
     *
     * @return zipcode - 邮编
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * 设置邮编
     *
     * @param zipcode 邮编
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * 获取是否为默认地址0否1是
     *
     * @return is_default - 是否为默认地址0否1是
     */
    public Byte getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否为默认地址0否1是
     *
     * @param isDefault 是否为默认地址0否1是
     */
    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取会员id
     *
     * @return uid - 会员id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置会员id
     *
     * @param uid 会员id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}