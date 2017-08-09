package com.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户信息
 * @author asus
 *
 */

public class Customer {
	private Integer cid;//客户ID
	private String custName;//客户名称
	private String custLevel;//客户级别
	private String custSource;//客户来源
	private String custPhone;//客户电话
	private String custMobile;//客户手机
	
	// 在客户实体类里面表示多个联系人，一个客户有多个联系人
	//Hibernate要求使用集合来表示多个数据，使用set集合
	private Set<LinkMan> setLinkMan = new HashSet<LinkMan>();
		
	public Set<LinkMan> getSetLinkMan() {
		return setLinkMan;
	}
	public void setSetLinkMan(Set<LinkMan> setLinkMan) {
		this.setLinkMan = setLinkMan;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	

}
