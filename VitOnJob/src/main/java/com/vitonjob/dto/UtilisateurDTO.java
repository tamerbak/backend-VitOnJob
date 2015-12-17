package com.vitonjob.dto;

import java.io.Serializable;

public class UtilisateurDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	protected String email;

	public UtilisateurDTO(String email) {
		this.email = email;
	}

	public UtilisateurDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
