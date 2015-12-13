package com.vitonjob.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERIME")
public class Interime implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INTERIME_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NOM_OU_RAISON_SOCIALE")
	private String nomOuRaisonSociale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomOuRaisonSociale() {
		return nomOuRaisonSociale;
	}

	public void setNomOuRaisonSociale(String nomOuRaisonSociale) {
		this.nomOuRaisonSociale = nomOuRaisonSociale;
	}

}