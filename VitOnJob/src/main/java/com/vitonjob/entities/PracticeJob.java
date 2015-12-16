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
@Table(name = "PRACTICE_JOB")
public class PracticeJob implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRACTICE_JOB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "JOBYER_ID")
	private Jobyer jobyer;

	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	private Job job;

	@ManyToOne
	@JoinColumn(name = "LEVEL_ID")
	private Level level;

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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public EntrepriseOffer getEntrepriseOffer() {
		return entrepriseOffer;
	}

	public void setEntrepriseOffer(EntrepriseOffer entrepriseOffer) {
		this.entrepriseOffer = entrepriseOffer;
	}

}