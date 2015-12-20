package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DISPONIBILITE")
public class Disponibilite implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DISPONIBILITE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DATE_DE_DEBUT")
	private Date dateDeDebut;

	@Column(name = "DATE_DE_FIN")
	private Date dateDeFin;

	@OneToMany(mappedBy = "disponibilite", fetch = FetchType.EAGER)
	private Set<PlageHoraire> listPlageHoraire;

	@ManyToOne
	@JoinColumn(name = "AGENDA_ID")
	private Agenda agenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDeDebut() {
		return dateDeDebut;
	}

	public void setDateDeDebut(Date dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}

	public Date getDateDeFin() {
		return dateDeFin;
	}

	public void setDateDeFin(Date dateDeFin) {
		this.dateDeFin = dateDeFin;
	}

	public Set<PlageHoraire> getListPlageHoraire() {
		return listPlageHoraire;
	}

	public void setListPlageHoraire(Set<PlageHoraire> listPlageHoraire) {
		this.listPlageHoraire = listPlageHoraire;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

}