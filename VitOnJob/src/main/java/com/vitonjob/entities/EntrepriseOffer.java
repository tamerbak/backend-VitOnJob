package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ENTREPRISE_OFFER")
public class EntrepriseOffer implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENTREPRISE_OFFER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TITRE")
	private String titre;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TARIF_A_L_HEURE")
	private Double tarifALheure;

	@Column(name = "DATE_EXPIRATION")
	private Date dateExpiration;

	@ManyToOne
	@JoinColumn(name = "ENTREPRISE_ID")
	private Entreprise entreprise;

	@ManyToOne
	@JoinColumn(name = "ENTREPRISE_ADDRESS_ID")
	private EntrepriseAddress entrepriseAddress;

	@OneToMany(mappedBy = "entrepriseOffer")
	private Set<PracticeLanguage> listPracticeLanguage;

	@OneToMany(mappedBy = "entrepriseOffer")
	private Set<PracticeJob> listPracticeJob;

	@ManyToOne
	@JoinColumn(name = "AGENDA_ID")
	private Agenda agenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTarifALheure() {
		return tarifALheure;
	}

	public void setTarifALheure(Double tarifALheure) {
		this.tarifALheure = tarifALheure;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public EntrepriseAddress getEntrepriseAddress() {
		return entrepriseAddress;
	}

	public void setEntrepriseAddress(EntrepriseAddress entrepriseAddress) {
		this.entrepriseAddress = entrepriseAddress;
	}

	public Set<PracticeLanguage> getListPracticeLanguage() {
		return listPracticeLanguage;
	}

	public void setListPracticeLanguage(Set<PracticeLanguage> listPracticeLanguage) {
		this.listPracticeLanguage = listPracticeLanguage;
	}

	public Set<PracticeJob> getListPracticeJob() {
		return listPracticeJob;
	}

	public void setListPracticeJob(Set<PracticeJob> listPracticeJob) {
		this.listPracticeJob = listPracticeJob;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

}