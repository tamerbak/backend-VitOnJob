package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENTREPRISE_ADDRESS")
public class EntrepriseAddress implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENTREPRISE_ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "IS_WORK_ADDRESS")
	private Boolean isWorkAddress;

	@Column(name = "IS_HEADQUARTERS")
	private Boolean isHeadquarters;

	@ManyToMany(mappedBy = "listEntrepriseAddress", fetch = FetchType.LAZY)
	private Set<Entreprise> listEntreprise;

	@ManyToOne
	@JoinColumn(name = "ADRESSE_ID")
	private Adresse adresse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsWorkAddress() {
		return isWorkAddress;
	}

	public void setIsWorkAddress(Boolean isWorkAddress) {
		this.isWorkAddress = isWorkAddress;
	}

	public Boolean getIsHeadquarters() {
		return isHeadquarters;
	}

	public void setIsHeadquarters(Boolean isHeadquarters) {
		this.isHeadquarters = isHeadquarters;
	}

	public Set<Entreprise> getListEntreprise() {
		return listEntreprise;
	}

	public void setListEntreprise(Set<Entreprise> listEntreprise) {
		this.listEntreprise = listEntreprise;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}