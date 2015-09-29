package com.jkkp.modules.member.service;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.view.VMemberTopic;

public interface ITopicService extends IService<Topic, VMemberTopic, Integer> {
	public VMemberTopic detail(int id);

	public void noPass(int id);

	public void pass(int id);
}
