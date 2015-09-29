package com.jkkp.modules.design.model;

import javax.persistence.*;

@Table(name = "design_image")
public class DesignImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 效果图id
     */
    @Column(name = "design_id")
    private Integer designId;
    @Column(name = "is_desk_top")
    private Integer isDeskTop;
    public Integer getIsDeskTop() {
		return isDeskTop;
	}

	public void setIsDeskTop(Integer isDeskTop) {
		this.isDeskTop = isDeskTop;
	}

	private Integer type;
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
     * 图片id
     */
    private String pid;

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
     * 获取效果图id
     *
     * @return design_id - 效果图id
     */
    public Integer getDesignId() {
        return designId;
    }

    /**
     * 设置效果图id
     *
     * @param designId 效果图id
     */
    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    /**
     * 获取图片id
     *
     * @return pid - 图片id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置图片id
     *
     * @param pid 图片id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}