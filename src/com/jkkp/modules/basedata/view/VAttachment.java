package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.Attachment;

public class VAttachment extends Attachment {

	public String photoUrl;
	public String downloadPath;

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
}
