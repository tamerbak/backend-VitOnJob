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

	public RechercheJobyerDTO(Long id, String titre, String nom, String prenom, String cin) {
		this.id = id;
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
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
		RechercheJobyerDTO other = (RechercheJobyerDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
