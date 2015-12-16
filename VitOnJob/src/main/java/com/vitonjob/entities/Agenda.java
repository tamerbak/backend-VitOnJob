package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AGENDA")
public class Agenda implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AGENDA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LIBELLE")
	private String libelle;

	@OneToMany(mappedBy = "agenda", fetch = FetchType.LAZY)
	private Set<Disponibilite> listDisponibilites;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Disponibilite> getListDisponibilites() {
		return listDisponibilites;
	}

	public void setListDisponibilites(Set<Disponibilite> listDisponibilites) {
		this.listDisponibilites = listDisponibilites;
	}

}