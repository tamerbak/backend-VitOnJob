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
@Table(name = "JOBYER_OFFER_CONTACT")
public class JobyerOfferContact implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "JOBYER_OFFER_CONTACT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "JOBYER_OFFER_ID")
	private JobyerOffer jobyerOffer;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEUR_ID")
	private Employeur employeur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobyerOffer getJobyerOffer() {
		return jobyerOffer;
	}

	public void setJobyerOffer(JobyerOffer jobyerOffer) {
		this.jobyerOffer = jobyerOffer;
	}

	public Employeur getEmployeur() {
		return employeur;
	}

	public void setEmployeur(Employeur employeur) {
		this.employeur = employeur;
	}

}