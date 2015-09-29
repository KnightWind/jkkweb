package com.jkkp.modules.supplier.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "start_engineerings_picture")
public class StartEngineeringsPicture {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public Integer getStartEngineeringsId() {
		return StartEngineeringsId;
	}
	public void setStartEngineeringsId(Integer startEngineeringsId) {
		StartEngineeringsId = startEngineeringsId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "attachment_id")
	private Integer attachmentId;
	@Column(name = "start_engineerings_id")
	private Integer StartEngineeringsId;
}
