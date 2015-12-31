package com.vitonjob.dto;

import com.vitonjob.entities.Jobyer;

public class RechercheJobyerDTO {
	private Long id;
	private String titre;
	private String nom;
	private String prenom;
	private String cin;

	public RechercheJobyerDTO(Jobyer o) {
		id = o.getId();
		titre = o.getTitre();
		nom = o.getNom();
		prenom = o.getPrenom();
		cin = o.getCin();
	}

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

}
