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
@Table(name = "PRACTICE_LANGUAGE")
public class PracticeLanguage implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRACTICE_LANGUAGE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "JOBYER_ID")
	private Jobyer jobyer;

	@ManyToOne
	@JoinColumn(name = "LEVEL_ID")
	private Level level;

	@ManyToOne
	@JoinColumn(name = "LANGUAGE_ID")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "ENTREPRISE_OFFER_ID")
	private EntrepriseOffer entrepriseOffer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Jobyer getJobyer() {
		return jobyer;
	}

	public void setJobyer(Jobyer jobyer) {
		this.jobyer = jobyer;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public EntrepriseOffer getEntrepriseOffer() {
		return entrepriseOffer;
	}

	public void setEntrepriseOffer(EntrepriseOffer entrepriseOffer) {
		this.entrepriseOffer = entrepriseOffer;
	}

}