package com.vitonjob.dto;

import java.io.Serializable;

public class PaysDTO {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private String nom;
	private String indicatif_telephonique;

	public PaysDTO(String nom) {
		this.nom = nom;
	}

	public PaysDTO(String nom, String indicatif) {
		this.nom = nom;
		this.setIndicatif(indicatif);
	}
	
	public PaysDTO() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getIndicatif() {
		return indicatif_telephonique;
	}

	public void setIndicatif(String indicatif) {
		this.indicatif_telephonique = indicatif;
	}
}
