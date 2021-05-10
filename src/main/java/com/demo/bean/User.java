package com.demo.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public class User implements Serializable {
	private Integer id;
	private String telephone;
	private String username;
	private String password;
	private Date registrationTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisteredDate() {
		return registrationTime;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registrationTime = registeredDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", telephone='" + telephone + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", registeredTime=" + registrationTime +
				'}';
	}
}
