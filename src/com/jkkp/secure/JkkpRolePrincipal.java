package com.jkkp.secure;

import java.io.Serializable;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;

public class JkkpRolePrincipal implements Role, Serializable {
	private static final long serialVersionUID = 1L;
	private String rolename;

	public JkkpRolePrincipal(String name) {
		this.rolename = name;
	}

	public String getName() {
		return getRolename();
	}

	public String getDescription() {
		return " some role";
	}

	public String getRolename() {
		return this.rolename;
	}

	public UserDatabase getUserDatabase() {
		return null;
	}

	public void setDescription(String arg0) {
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}