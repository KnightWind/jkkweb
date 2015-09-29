package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "supplier_company")
public class SupplierCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司logo
     */
    private String logo;

    /**
     * 公司简介
     */
    private String intro;

    /**
     * 公司规模
     */
    private Integer scale;

    /**
     * 注册资金单位：万元
     */
    private Integer capital;

    /**
     * 成立时间
     */
    private Date establish;

    /**
     * 年检时间
     */
    private Date inspection;

    /**
     * 营业年限开始时间
     */
    @Column(name = "duration_start")
    private Date durationStart;

    /**
     * 营业年限结束时间
     */
    @Column(name = "duration_end")
    private Date durationEnd;

    /**
     * 企业证书
     */
    private String certificate1;

    /**
     * 企业证书2
     */
    private String certificate2;

    /**
     * 企业证书3
     */
    private String certificate3;

    /**
     * 企业证书4
     */
    private String certificate4;

    /**
     * 企业证书5
     */
    private String certificate5;

    /**
     * 企业证书6
     */
    private String certificate6;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 公司模板id
     */
    @Column(name = "template_id")
    private Integer templateId;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 效果图数量
     */
    @Column(name = "design_num")
    private Integer designNum;

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
     * 获取公司logo
     *
     * @return logo - 公司logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置公司logo
     *
     * @param logo 公司logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取公司简介
     *
     * @return intro - 公司简介
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置公司简介
     *
     * @param intro 公司简介
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取公司规模
     *
     * @return scale - 公司规模
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * 设置公司规模
     *
     * @param scale 公司规模
     */
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * 获取注册资金单位：万元
     *
     * @return capital - 注册资金单位：万元
     */
    public Integer getCapital() {
        return capital;
    }

    /**
     * 设置注册资金单位：万元
     *
     * @param capital 注册资金单位：万元
     */
    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    /**
     * 获取成立时间
     *
     * @return establish - 成立时间
     */
    public Date getEstablish() {
        return establish;
    }

    /**
     * 设置成立时间
     *
     * @param establish 成立时间
     */
    public void setEstablish(Date establish) {
        this.establish = establish;
    }

    /**
     * 获取年检时间
     *
     * @return inspection - 年检时间
     */
    public Date getInspection() {
        return inspection;
    }

    /**
     * 设置年检时间
     *
     * @param inspection 年检时间
     */
    public void setInspection(Date inspection) {
        this.inspection = inspection;
    }

    /**
     * 获取营业年限开始时间
     *
     * @return duration_start - 营业年限开始时间
     */
    public Date getDurationStart() {
        return durationStart;
    }

    /**
     * 设置营业年限开始时间
     *
     * @param durationStart 营业年限开始时间
     */
    public void setDurationStart(Date durationStart) {
        this.durationStart = durationStart;
    }

    /**
     * 获取营业年限结束时间
     *
     * @return duration_end - 营业年限结束时间
     */
    public Date getDurationEnd() {
        return durationEnd;
    }

    /**
     * 设置营业年限结束时间
     *
     * @param durationEnd 营业年限结束时间
     */
    public void setDurationEnd(Date durationEnd) {
        this.durationEnd = durationEnd;
    }

    /**
     * 获取企业证书
     *
     * @return certificate1 - 企业证书
     */
    public String getCertificate1() {
        return certificate1;
    }

    /**
     * 设置企业证书
     *
     * @param certificate1 企业证书
     */
    public void setCertificate1(String certificate1) {
        this.certificate1 = certificate1;
    }

    /**
     * 获取企业证书2
     *
     * @return certificate2 - 企业证书2
     */
    public String getCertificate2() {
        return certificate2;
    }

    /**
     * 设置企业证书2
     *
     * @param certificate2 企业证书2
     */
    public void setCertificate2(String certificate2) {
        this.certificate2 = certificate2;
    }

    /**
     * 获取企业证书3
     *
     * @return certificate3 - 企业证书3
     */
    public String getCertificate3() {
        return certificate3;
    }

    /**
     * 设置企业证书3
     *
     * @param certificate3 企业证书3
     */
    public void setCertificate3(String certificate3) {
        this.certificate3 = certificate3;
    }

    /**
     * 获取企业证书4
     *
     * @return certificate4 - 企业证书4
     */
    public String getCertificate4() {
        return certificate4;
    }

    /**
     * 设置企业证书4
     *
     * @param certificate4 企业证书4
     */
    public void setCertificate4(String certificate4) {
        this.certificate4 = certificate4;
    }

    /**
     * 获取企业证书5
     *
     * @return certificate5 - 企业证书5
     */
    public String getCertificate5() {
        return certificate5;
    }

    /**
     * 设置企业证书5
     *
     * @param certificate5 企业证书5
     */
    public void setCertificate5(String certificate5) {
        this.certificate5 = certificate5;
    }

    /**
     * 获取企业证书6
     *
     * @return certificate6 - 企业证书6
     */
    public String getCertificate6() {
        return certificate6;
    }

    /**
     * 设置企业证书6
     *
     * @param certificate6 企业证书6
     */
    public void setCertificate6(String certificate6) {
        this.certificate6 = certificate6;
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
     * 获取公司模板id
     *
     * @return template_id - 公司模板id
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * 设置公司模板id
     *
     * @param templateId 公司模板id
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取公司地址
     *
     * @return address - 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取效果图数量
     *
     * @return design_num - 效果图数量
     */
    public Integer getDesignNum() {
        return designNum;
    }

    /**
     * 设置效果图数量
     *
     * @param designNum 效果图数量
     */
    public void setDesignNum(Integer designNum) {
        this.designNum = designNum;
    }
    public String getEs(){
    	return establish==null?"": DateUtil.format(establish,"yyyy-MM-dd");
    }
    public String getIn(){
    	return inspection==null?"": DateUtil.format(inspection,"yyyy-MM-dd");
    }
    public String getSt(){
    	return durationStart==null?"": DateUtil.format(durationStart,"yyyy-MM-dd");
    }
    public String getEn(){
    	return durationEnd==null?"": DateUtil.format(durationEnd,"yyyy-MM-dd");
    }
}