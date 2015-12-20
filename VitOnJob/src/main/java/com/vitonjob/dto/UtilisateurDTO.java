package com.vitonjob.dto;

import java.io.Serializable;

public class UtilisateurDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	protected String email;

	protected String telephone;

	protected boolean isNew;

	public UtilisateurDTO(String email) {
		this.email = email;
	}

	public UtilisateurDTO(String email, String telephone, boolean isNew) {
		this.email = email;
		this.telephone = telephone;
		this.isNew = isNew;
	}

	public UtilisateurDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
