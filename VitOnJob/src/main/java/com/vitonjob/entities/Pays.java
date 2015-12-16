package com.vitonjob.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYS")
public class Pays implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAYS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "CODE")
	private String code;

	@Column(name = "INDICATIF_TELEPHONIQUE")
	private String indicatifTelephonique;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIndicatifTelephonique() {
		return indicatifTelephonique;
	}

	public void setIndicatifTelephonique(String indicatifTelephonique) {
		this.indicatifTelephonique = indicatifTelephonique;
	}

}