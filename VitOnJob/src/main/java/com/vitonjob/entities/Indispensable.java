package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INDISPENSABLE")
public class Indispensable implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INDISPENSABLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LIBELLE")
	private String libelle;

	@ManyToMany(mappedBy = "listIndispensable")
	private Set<Jobyer> myJobyer;

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

	public Set<Jobyer> getMyJobyer() {
		return myJobyer;
	}

	public void setMyJobyer(Set<Jobyer> myJobyer) {
		this.myJobyer = myJobyer;
	}

}