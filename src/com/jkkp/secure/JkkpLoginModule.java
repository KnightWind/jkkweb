package com.jkkp.secure;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JkkpLoginModule implements LoginModule {
	Logger logger = Logger.getLogger(JkkpLoginModule.class);
	public static String USER_QUERY = "select pass from admin where username = ? and status = 0";
	public static String SUPPLIER_QUERY = "SELECT s.userpwd as pass from supplier_user s,supplier p where s.sp_id=p.id and s.username=? and  p.status = 0 and p.type = ";
	public static String ROLE_QUERY = "select role from resource_role where username = ?";
	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;
	private boolean debug = false;

	private boolean succeeded = false; 
	private boolean commitSucceeded = false;

	private boolean isSupplier; // 是否商家
	private boolean isSupervisor; // 是否监理
	private boolean isCrowd; // 是否建材
	private boolean isForeman; // 是否建材
	private String username = null;
	private char[] password = null;
	private JkkpUserPrincipal jkkpUserPrincipal;
	private JkkpRolePrincipal jkkpRolePrincipal;

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
	}

//	public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		BASE64Encoder base64en = new BASE64Encoder();
//
//		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
//		return newstr;
//	}
	
	
	public final static String md5(String string) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = string.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	

	private void identifyUser() {
		this.isSupplier = this.username.startsWith(JkkpUserPrincipal.SUPPLIER_PREFIX);
		if (this.isSupplier) {
			this.username = this.username.replace(JkkpUserPrincipal.SUPPLIER_PREFIX, "");
		} else {
			this.isSupervisor = this.username.startsWith(JkkpUserPrincipal.SUPERVISOR_PREFIX);
			if (this.isSupervisor) {
				this.username = this.username.replace(JkkpUserPrincipal.SUPERVISOR_PREFIX, "");
			}
			this.isCrowd = this.username.startsWith(JkkpUserPrincipal.CROWD_PREFIX);
			if(this.isCrowd){
				this.username = this.username.replace(JkkpUserPrincipal.CROWD_PREFIX, "");
			}
			this.isForeman = this.username.startsWith(JkkpUserPrincipal.FOREMAN_PREFIX);
			if(this.isForeman){
				this.username = this.username.replace(JkkpUserPrincipal.FOREMAN_PREFIX, "");
			}
		}
	}

	public boolean login() throws LoginException {
		if (this.callbackHandler == null) {
			throw new LoginException("call back handler is null");
		}
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("username");
		callbacks[1] = new PasswordCallback("password: ", false);
		try {
			this.callbackHandler.handle(callbacks);
			this.username = ((NameCallback) callbacks[0]).getName();
			this.password = ((PasswordCallback) callbacks[1]).getPassword();

			this.identifyUser(); // 识别用户

			if ((this.username == null) || (this.password == null)) {
				throw new LoginException("Callback handler does not return login data properly");
			}

			this.logger.info("username: " + this.username);
			this.logger.info("password: " + new String(this.password));

			if (this.checkLoginUser()) {
				this.succeeded = true;
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedCallbackException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean commit() throws LoginException {
		this.logger.info("committing...");
		if (!this.succeeded) {
			return false;
		}
		this.jkkpUserPrincipal = new JkkpUserPrincipal(this.username);
		//this.jkkpUserPrincipal.setType(this.isSupplier ? JkkpUserPrincipal.SUPPLIER_TYPE : (this.isSupervisor ? JkkpUserPrincipal.SUPERVISOR_TYPE : JkkpUserPrincipal.ADMIN_TYPE));
	
		if(this.isSupplier){
			this.jkkpUserPrincipal.setType(JkkpUserPrincipal.SUPPLIER_TYPE);
		}else if(this.isSupervisor){
			this.jkkpUserPrincipal.setType(JkkpUserPrincipal.SUPERVISOR_TYPE);
		}else if(this.isCrowd){
			this.jkkpUserPrincipal.setType(JkkpUserPrincipal.CROWD_TYPE);
		}else if(this.isForeman){
			this.jkkpUserPrincipal.setType(JkkpUserPrincipal.FOREMAN_TYPE);
		}else{
			this.jkkpUserPrincipal.setType(JkkpUserPrincipal.ADMIN_TYPE);
		}
		
		if (!this.subject.getPrincipals().contains(this.jkkpUserPrincipal)) {
			this.subject.getPrincipals().add(this.jkkpUserPrincipal);
		}

		List<String> roles = getRoles(this.jkkpUserPrincipal);
		for (String role : roles) {
			this.logger.debug("role...");
			this.jkkpRolePrincipal = new JkkpRolePrincipal(role);
			if (!this.subject.getPrincipals().contains(this.jkkpRolePrincipal)) {
				this.subject.getPrincipals().add(this.jkkpRolePrincipal);
			}
		}

		this.commitSucceeded = true;

		for (Principal p : this.subject.getPrincipals()) {
			if ((p instanceof JkkpRolePrincipal)) {
				this.logger.debug(" ROLE: " + p.getName());
			}
		}
		return true;
	}

	public boolean abort() throws LoginException {
		if (!this.succeeded)
			return false;
		if ((this.succeeded) && (!this.commitSucceeded)) {
			this.succeeded = false;
			this.username = null;
			if (this.password != null) {
				this.password = null;
			}
			this.jkkpUserPrincipal = null;
		} else {
			logout();
		}
		return true;
	}

	public boolean logout() throws LoginException {
		this.subject.getPrincipals().remove(this.jkkpUserPrincipal);
		this.succeeded = false;
		this.succeeded = this.commitSucceeded;
		this.username = null;
		if (this.password != null) {
			for (int i = 0; i < this.password.length; i++) {
				this.password[i] = ' ';
			}
			this.password = null;
		}
		this.jkkpUserPrincipal = null;
		return true;
	}

	private boolean checkLoginUser() throws LoginException {
		if (this.isSupplier) {
			return this.isValidUser(SUPPLIER_QUERY + 1);
		} else if (this.isSupervisor) {
			return this.isValidUser(SUPPLIER_QUERY + 5);
		} else if (this.isCrowd){
			return this.isValidUser(SUPPLIER_QUERY + 2);
		}else if (this.isForeman){
			return this.isValidUser(SUPPLIER_QUERY + 3);
		}else {
			return this.isValidUser(USER_QUERY);
		}
	}
 
	private boolean isValidUser(String querySql) throws LoginException {
		logger.info(querySql);
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(querySql);
			stmt.setString(1, this.username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				logger.info("yuan:" + new String(this.password));
				logger.info("mi:" + md5(new String(this.password)));
				if (md5(new String(this.password)).equals(rs.getString("pass")))
					return true;
			}
		} catch (Exception e) {
			this.logger.error("Error when loading user from the database " + e);
			e.printStackTrace();
		} finally {
			try {
				rs.close(); 
			} catch (SQLException e) {
				this.logger.error("Error when closing result set." + e);
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				this.logger.error("Error when closing statement." + e);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				this.logger.error("Error when closing connection." + e);
			}
		}
		return false;
	}

	private List<String> getRoles(JkkpUserPrincipal user) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<String> roleList = new ArrayList<String>();
		try {
			String checkRoleName = this.username;
			if (this.isSupplier || this.isSupervisor || this.isCrowd || this.isForeman) {
				checkRoleName = JkkpUserPrincipal.AUTHORIZATION_NAME;
			}
 
			connection = getConnection();
			stmt = connection.prepareStatement(ROLE_QUERY);
			stmt.setString(1, checkRoleName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				roleList.add(rs.getString("role"));
				user.addRole(new JkkpRolePrincipal(rs.getString("role")));
			}
		} catch (Exception e) {
			this.logger.error("Error when loading user from the database " + e);
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				this.logger.error("Error when closing result set." + e);
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				this.logger.error("Error when closing statement." + e);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				this.logger.error("Error when closing connection." + e);
			}
		}
		return roleList;
	}

	private Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkkp");
			return ds.getConnection();
		} catch (Exception e) {
			this.logger.error("获取数据库连接失败", e);
		}
		return null;
	}
	public static void main(String[] args) {
		char[] c = {'a','b','c'};
		System.out.println(new String(c));
	}
}