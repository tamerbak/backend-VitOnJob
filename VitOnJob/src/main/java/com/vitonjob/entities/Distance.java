package com.vitonjob.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DISTANCE")
public class Distance implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DISTANCE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "VALEUR")
	private Double valeur;

	@ManyToOne
	@JoinColumn(name = "JOBYER_ADDRESS_ID")
	private JobyerAddress jobyerAddress;

	@ManyToOne
	@JoinColumn(name = "ENTREPRISE_ADDRESS_ID")
	private EntrepriseAddress entrepriseAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public JobyerAddress getJobyerAddress() {
		return jobyerAddress;
	}

	public void setJobyerAddress(JobyerAddress jobyerAddress) {
		this.jobyerAddress = jobyerAddress;
	}

	public EntrepriseAddress getEntrepriseAddress() {
		return entrepriseAddress;
	}

	public void setEntrepriseAddress(EntrepriseAddress entrepriseAddress) {
		this.entrepriseAddress = entrepriseAddress;
	}

}