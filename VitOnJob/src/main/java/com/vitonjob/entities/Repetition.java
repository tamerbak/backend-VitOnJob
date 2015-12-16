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
@Table(name = "REPETITION")
public class Repetition implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REPETITION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EST_LUNDI")
	private Boolean estLundi;

	@Column(name = "EST_MARDI")
	private Boolean estMardi;

	@Column(name = "EST_MERCREDI")
	private Boolean estMercredi;

	@Column(name = "EST_JEUDI")
	private Boolean estJeudi;

	@Column(name = "EST_VENDREDI")
	private Boolean estVendredi;

	@Column(name = "EST_SAMEDI")
	private Boolean estSamedi;

	@Column(name = "EST_DIMANCHE")
	private Boolean estDimanche;

	@ManyToOne
	@JoinColumn(name = "DISPONIBILITE_ID")
	private Disponibilite disponibilite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstLundi() {
		return estLundi;
	}

	public void setEstLundi(Boolean estLundi) {
		this.estLundi = estLundi;
	}

	public Boolean getEstMardi() {
		return estMardi;
	}

	public void setEstMardi(Boolean estMardi) {
		this.estMardi = estMardi;
	}

	public Boolean getEstMercredi() {
		return estMercredi;
	}

	public void setEstMercredi(Boolean estMercredi) {
		this.estMercredi = estMercredi;
	}

	public Boolean getEstJeudi() {
		return estJeudi;
	}

	public void setEstJeudi(Boolean estJeudi) {
		this.estJeudi = estJeudi;
	}

	public Boolean getEstVendredi() {
		return estVendredi;
	}

	public void setEstVendredi(Boolean estVendredi) {
		this.estVendredi = estVendredi;
	}

	public Boolean getEstSamedi() {
		return estSamedi;
	}

	public void setEstSamedi(Boolean estSamedi) {
		this.estSamedi = estSamedi;
	}

	public Boolean getEstDimanche() {
		return estDimanche;
	}

	public void setEstDimanche(Boolean estDimanche) {
		this.estDimanche = estDimanche;
	}

	public Disponibilite getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
	}

}