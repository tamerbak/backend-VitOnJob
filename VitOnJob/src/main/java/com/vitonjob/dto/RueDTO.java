package com.vitonjob.dto;

import java.io.Serializable;

public class RueDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private String nom;

	public RueDTO(String nom) {
		this.nom = nom;
	}

	public RueDTO() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
