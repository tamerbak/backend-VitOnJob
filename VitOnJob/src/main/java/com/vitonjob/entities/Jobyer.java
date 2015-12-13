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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JOBYER")
public class Jobyer implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "JOBYER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TITRE")
	private String titre;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "PRENOM")
	private String prenom;

	@Column(name = "CIN")
	private String cin;

	@Column(name = "DATE_DE_NAISSANCE")
	private Date dateDeNaissance;

	@Column(name = "LIEU_DE_NAISSANCE")
	private String lieuDeNaissance;

	@OneToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	@ManyToMany
	@JoinTable(name = "JOBYER_JOBYER_ADDRESS", joinColumns = {
			@JoinColumn(name = "JOBYER_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "JOBYER_ADDRESS_ID", nullable = false) })
	private Set<JobyerAddress> listJobyerAddress;

	@ManyToMany
	@JoinTable(name = "JOBYER_INDISPENSABLE", joinColumns = {
			@JoinColumn(name = "JOBYER_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "INDISPENSABLE_ID", nullable = false) })
	private Set<Indispensable> listIndispensable;

	@OneToOne
	@JoinColumn(name = "AGENDA_ID")
	private Agenda agenda;

	@ManyToOne
	@JoinColumn(name = "NATIONALITE_ID")
	private Nationalite nationalite;

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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<JobyerAddress> getListJobyerAddress() {
		return listJobyerAddress;
	}

	public void setListJobyerAddress(Set<JobyerAddress> listJobyerAddress) {
		this.listJobyerAddress = listJobyerAddress;
	}

	public Set<Indispensable> getListIndispensable() {
		return listIndispensable;
	}

	public void setListIndispensable(Set<Indispensable> listIndispensable) {
		this.listIndispensable = listIndispensable;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Nationalite getNationalite() {
		return nationalite;
	}

	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}

}