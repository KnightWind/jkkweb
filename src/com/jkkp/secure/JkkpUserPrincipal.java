package com.jkkp.secure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

public class JkkpUserPrincipal implements User {
	private String type;
	private String username;
	private Set<Role> roles = new HashSet<Role>();
	
	public static final String SUPPLIER_TYPE = "supplier";
	public static final String SUPERVISOR_TYPE = "supervisor";
	public static final String CROWD_TYPE = "crowd";
	public static final String FOREMAN_TYPE = "foreman";
	public static final String ADMIN_TYPE = "admin";

	public static String SUPPLIER_PREFIX = "SUPPLIER_PREFIX_"; // 商家标识前缀
	public static String SUPERVISOR_PREFIX = "SUPERVISOR_PREFIX_"; // 监理标识前缀
	public static String CROWD_PREFIX = "CROWD_PREFIX_"; // 建材商标识前缀
	public static String FOREMAN_PREFIX = "FOREMAN_PREFIX_"; // 工长识前缀
	public static String AUTHORIZATION_NAME = "test"; // 已认证用户
	
	public JkkpUserPrincipal(String username) {
		this.username = username;
	} 
	
	public boolean isSupplier() {
		return SUPPLIER_TYPE.equals(type);
	}
	
	public boolean isCrowd() { 
		return CROWD_TYPE.equals(type);
	}
	
	public boolean isForeman() { 
		return FOREMAN_TYPE.equals(type);
	}
	
	public boolean isSupervisor() {
		return SUPERVISOR_TYPE.equals(type);
	}
	
	public boolean isAdmin() {
		return ADMIN_TYPE.equals(type);
	}

	public String getName() {
		return this.username;
	}

	public void addGroup(Group arg0) {
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public String getFullName() {
		return this.username;
	}

	public Iterator<Group> getGroups() {
		return null;
	}

	public String getPassword() {
		return null;
	}

	public Iterator<Role> getRoles() {
		return this.roles.iterator();
	}

	public UserDatabase getUserDatabase() {
		return null;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isInGroup(Group arg0) {
		return false;
	}

	public boolean isInRole(Role role) {
		if (!this.roles.isEmpty()) {
			Iterator<Role> it = this.roles.iterator();
			while (it.hasNext()) {
				Role rol = (Role) it.next();
				if ((rol.getName() != null) && (rol.getName().equals(role.getName()))) {
					return true;
				}
			}
		}
		return false;
	}

	public void removeGroup(Group arg0) {
	}

	public void removeGroups() {
	}

	public void removeRole(Role arg0) {
	}

	public void removeRoles() {
		this.roles.clear();
	}

	public void setFullName(String arg0) {
		setUsername(this.username);
	}

	public void setPassword(String arg0) {
	}

	public void setUsername(String arg0) {
		this.username = arg0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}