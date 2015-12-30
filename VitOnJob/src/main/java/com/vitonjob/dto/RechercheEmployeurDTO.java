package com.vitonjob.dto;

import com.vitonjob.entities.Employeur;

public class RechercheEmployeurDTO {
	private Long id;
	private String titre;
	private String nom;
	private String prenom;
	
	public RechercheEmployeurDTO(Employeur object){
		id = object.getId();
		titre = object.getTitre();
		nom = object.getNom();
		prenom = object.getPrenom();
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
}
