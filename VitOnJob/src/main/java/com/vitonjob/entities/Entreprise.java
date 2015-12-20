package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "ENTREPRISE")
public class Entreprise implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENTREPRISE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOM_OU_RAISON_SOCIALE")
	private String nomOuRaisonSociale;

	@Column(name = "SIRET")
	private String siret;

	@Column(name = "CODE_APE_OU_NAF")
	private String codeApeOuNaf;

	@Column(name = "URSSAF")
	private String urssaf;

	@Column(name = "IDENTIFIANT_FISCAL")
	private String identifiantFiscal;

	@Column(name = "CNI_OU_RC")
	private Integer cniOuRc;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	@ManyToMany
	@JoinTable(name = "ENTREPRISE_ENTREPRISE_ADDRESS", joinColumns = {
			@JoinColumn(name = "ENTREPRISE_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ENTREPRISE_ADDRESS_ID", nullable = false) })
	private Set<EntrepriseAddress> listEntrepriseAddress;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEUR_ID")
	private Employeur employeur;

	public Entreprise() {
	}

	public Entreprise(Account account, Employeur employeur) {
		this.account = account;
		this.employeur = employeur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomOuRaisonSociale() {
		return nomOuRaisonSociale;
	}

	public void setNomOuRaisonSociale(String nomOuRaisonSociale) {
		this.nomOuRaisonSociale = nomOuRaisonSociale;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getCodeApeOuNaf() {
		return codeApeOuNaf;
	}

	public void setCodeApeOuNaf(String codeApeOuNaf) {
		this.codeApeOuNaf = codeApeOuNaf;
	}

	public String getUrssaf() {
		return urssaf;
	}

	public void setUrssaf(String urssaf) {
		this.urssaf = urssaf;
	}

	public String getIdentifiantFiscal() {
		return identifiantFiscal;
	}

	public void setIdentifiantFiscal(String identifiantFiscal) {
		this.identifiantFiscal = identifiantFiscal;
	}

	public Integer getCniOuRc() {
		return cniOuRc;
	}

	public void setCniOuRc(Integer cniOuRc) {
		this.cniOuRc = cniOuRc;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<EntrepriseAddress> getListEntrepriseAddress() {
		return listEntrepriseAddress;
	}

	public void setListEntrepriseAddress(Set<EntrepriseAddress> listEntrepriseAddress) {
		this.listEntrepriseAddress = listEntrepriseAddress;
	}

	public Employeur getEmployeur() {
		return employeur;
	}

	public void setEmployeur(Employeur employeur) {
		this.employeur = employeur;
	}

}