package com.lxp.bean;

/** 
 * @Description : 对应实体类
 * @author : lxp
 * @date : 2019年5月27日 下午7:38:11 
*/

public class User {
	private String id;
	private String username;
	private String pwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwd=" + pwd + "]";
	}
	
	
}
