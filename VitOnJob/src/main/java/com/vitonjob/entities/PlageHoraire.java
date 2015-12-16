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
@Table(name = "PLAGE_HORAIRE")
public class PlageHoraire implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PLAGE_HORAIRE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "HEURE_DE_DEBUT")
	private Integer HeureDeDebut;

	@Column(name = "HEURE_DE_FIN")
	private Integer HeureDeFin;

	@ManyToOne
	@JoinColumn(name = "DISPONIBILITE_ID")
	private Disponibilite disponibilite;

	@ManyToOne
	@JoinColumn(name = "REPETITION_ID")
	private Repetition repetition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHeureDeDebut() {
		return HeureDeDebut;
	}

	public void setHeureDeDebut(Integer heureDeDebut) {
		HeureDeDebut = heureDeDebut;
	}

	public Integer getHeureDeFin() {
		return HeureDeFin;
	}

	public void setHeureDeFin(Integer heureDeFin) {
		HeureDeFin = heureDeFin;
	}

	public Disponibilite getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Repetition getRepetition() {
		return repetition;
	}

	public void setRepetition(Repetition repetition) {
		this.repetition = repetition;
	}

}