package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAT")
public class Contrat implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRAT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DATE_DE_DEBUT")
	private Date dateDeDebut;

	@Column(name = "DATE_DE_FIN")
	private Date dateDeFin;

	@Column(name = "NOMBRE_D_HEURES")
	private Integer nombreDHeures;

	@Column(name = "TARIF_A_L_HEURE")
	private Double tarifALheure;

	@Column(name = "PERIODE_ESSAI")
	private Integer periodeEssai;

	@Column(name = "DATE_DEBUT_TERME")
	private Date dateDebutTerme;

	@Column(name = "DATE_FIN_TERME")
	private Date dateFinTerme;

	@Column(name = "MOTIF_DE_RECOURS")
	private String motifDeRecours;

	@Column(name = "HEURE_DE_DEBUT")
	private Integer heureDeDebut;

	@Column(name = "HEURE_DE_FIN")
	private Integer heureDeFin;

	@Column(name = "INFO_COMPLEMENTAIRE")
	private String infoComplementaire;

	@Column(name = "DATE_SIGNATURE")
	private Date dateSignature;

	@Column(name = "NUMERO")
	private String numero;

	@ManyToOne
	@JoinColumn(name = "JOBYER_ID")
	private Jobyer jobyer;

	@ManyToOne
	@JoinColumn(name = "ENTREPRISE_ID")
	private Entreprise entreprise;

	@ManyToOne
	@JoinColumn(name = "MEDECINE_DE_TRAVAIL_ID")
	private MedecineDeTravail medecineDeTravail;

	@ManyToOne
	@JoinColumn(name = "LIEU_SIGNATURE_ID")
	private Ville lieuSignature;

	@ManyToOne
	@JoinColumn(name = "INTERIME_ID")
	private Interime interime;

	@ManyToOne
	@JoinColumn(name = "ENTREPRISE_OFFER_ID")
	private EntrepriseOffer entrepriseOffer;

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

	public Integer getNombreDHeures() {
		return nombreDHeures;
	}

	public void setNombreDHeures(Integer nombreDHeures) {
		this.nombreDHeures = nombreDHeures;
	}

	public Double getTarifALheure() {
		return tarifALheure;
	}

	public void setTarifALheure(Double tarifALheure) {
		this.tarifALheure = tarifALheure;
	}

	public Integer getPeriodeEssai() {
		return periodeEssai;
	}

	public void setPeriodeEssai(Integer periodeEssai) {
		this.periodeEssai = periodeEssai;
	}

	public Date getDateDebutTerme() {
		return dateDebutTerme;
	}

	public void setDateDebutTerme(Date dateDebutTerme) {
		this.dateDebutTerme = dateDebutTerme;
	}

	public Date getDateFinTerme() {
		return dateFinTerme;
	}

	public void setDateFinTerme(Date dateFinTerme) {
		this.dateFinTerme = dateFinTerme;
	}

	public String getMotifDeRecours() {
		return motifDeRecours;
	}

	public void setMotifDeRecours(String motifDeRecours) {
		this.motifDeRecours = motifDeRecours;
	}

	public Integer getHeureDeDebut() {
		return heureDeDebut;
	}

	public void setHeureDeDebut(Integer heureDeDebut) {
		this.heureDeDebut = heureDeDebut;
	}

	public Integer getHeureDeFin() {
		return heureDeFin;
	}

	public void setHeureDeFin(Integer heureDeFin) {
		this.heureDeFin = heureDeFin;
	}

	public String getInfoComplementaire() {
		return infoComplementaire;
	}

	public void setInfoComplementaire(String infoComplementaire) {
		this.infoComplementaire = infoComplementaire;
	}

	public Date getDateSignature() {
		return dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Jobyer getJobyer() {
		return jobyer;
	}

	public void setJobyer(Jobyer jobyer) {
		this.jobyer = jobyer;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public MedecineDeTravail getMedecineDeTravail() {
		return medecineDeTravail;
	}

	public void setMedecineDeTravail(MedecineDeTravail medecineDeTravail) {
		this.medecineDeTravail = medecineDeTravail;
	}

	public Ville getLieuSignature() {
		return lieuSignature;
	}

	public void setLieuSignature(Ville lieuSignature) {
		this.lieuSignature = lieuSignature;
	}

	public Interime getInterime() {
		return interime;
	}

	public void setInterime(Interime interime) {
		this.interime = interime;
	}

	public EntrepriseOffer getEntrepriseOffer() {
		return entrepriseOffer;
	}

	public void setEntrepriseOffer(EntrepriseOffer entrepriseOffer) {
		this.entrepriseOffer = entrepriseOffer;
	}

}