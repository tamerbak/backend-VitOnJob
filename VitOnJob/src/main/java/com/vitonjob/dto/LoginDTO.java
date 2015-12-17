package com.vitonjob.dto;

import java.io.Serializable;

import org.json.simple.JSONObject;

import com.vitonjob.utils.StringUtils;

public class LoginDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	protected String email;

	protected String telephone;

	protected String password;

	private String role;

	public LoginDTO(JSONObject jsonLogin) {
		this.email = StringUtils.decode64((String) jsonLogin.get("email"));
		this.telephone = StringUtils.decode64((String) jsonLogin.get("telephone"));
		this.password = StringUtils.decode64((String) jsonLogin.get("password"));
		this.role = StringUtils.decode64((String) jsonLogin.get("role"));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
