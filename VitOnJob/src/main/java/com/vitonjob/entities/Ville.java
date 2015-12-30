package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VILLE")
public class Ville implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VILLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOM")
	private String Nom;

	@ManyToOne
	@JoinColumn(name = "PAYS_ID")
	private Pays pays;

	@ManyToMany
	@JoinTable(name = "VILLE_CODE_POSTAL", joinColumns = {
			@JoinColumn(name = "VILLE_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CODE_POSTAL_ID", nullable = false) })
	private Set<CodePostal> listCodePostal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Set<CodePostal> getListCodePostal() {
		return listCodePostal;
	}

	public void setListCodePostal(Set<CodePostal> listCodePostal) {
		this.listCodePostal = listCodePostal;
	}

}