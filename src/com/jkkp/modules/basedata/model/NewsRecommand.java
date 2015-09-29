package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news_recommand")
public class NewsRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 所在城市域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 推荐位置，index:首页|news:新闻首页
     */
    private String label;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取文章id
     *
     * @return article_id - 文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id
     *
     * @param articleId 文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
     * @param cityDomain 所在城市域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取推荐位置，index:首页|news:新闻首页
     *
     * @return label - 推荐位置，index:首页|news:新闻首页
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置推荐位置，index:首页|news:新闻首页
     *
     * @param label 推荐位置，index:首页|news:新闻首页
     */
    public void setLabel(String label) {
        this.label = label;
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
}