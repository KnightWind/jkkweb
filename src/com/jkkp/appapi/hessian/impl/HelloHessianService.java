package com.jkkp.appapi.hessian.impl;

import com.bean.Person;
import com.service.HelloHessian;

public class HelloHessianService implements HelloHessian {
	private static final long serialVersionUID = 7541500707729202618L;

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	@Override
	public Person getPerson(String name, Integer age, Integer sex) {
		Person bean = new Person();
		bean.setName(sayHello(name));
		bean.setAge(age);
		bean.setSex(sex);
		return bean;
	}

	@Override
	public Person getPersonByName(String name) {
		Person p = new Person();
		p.setName(name);
		return p;
	}

}
