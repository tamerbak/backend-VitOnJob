package com.vitonjob.dto;

import com.vitonjob.entities.Employeur;

public class RechercheEmployeurDTO {
	private Long id;
	private String titre;
	private String nom;
	private String prenom;

	public RechercheEmployeurDTO(Employeur object) {
		id = object.getId();
		titre = object.getTitre();
		nom = object.getNom();
		prenom = object.getPrenom();
	}

	public RechercheEmployeurDTO(Long id, String titre, String nom, String prenom) {
		this.id = id;
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RechercheEmployeurDTO other = (RechercheEmployeurDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
