package com.hibernate.manytomany;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * @author asus
 *
 */
public class Role {
	private Integer Role_id;//角色id
	private String Role_name;//角色姓名
	private String Role_password;//角色密码
	
	//一个角色有多个用户
	private Set<User> setUser = new HashSet<User>();
		
	public Set<User> getSetUser() {
		return setUser;
	}
	public void setSetUser(Set<User> setUser) {
		this.setUser = setUser;
	}
	public Integer getRole_id() {
		return Role_id;
	}
	public void setRole_id(Integer role_id) {
		Role_id = role_id;
	}
	public String getRole_name() {
		return Role_name;
	}
	public void setRole_name(String role_name) {
		Role_name = role_name;
	}
	public String getRole_password() {
		return Role_password;
	}
	public void setRole_password(String role_password) {
		Role_password = role_password;
	}
	

}
