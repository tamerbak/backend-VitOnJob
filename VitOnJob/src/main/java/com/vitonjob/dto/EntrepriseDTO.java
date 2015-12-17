package com.vitonjob.dto;

import java.io.Serializable;
import java.util.List;

public class EntrepriseDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long entrepriseId;

	private String name;

	private List<OfferDTO> offers;

	public EntrepriseDTO() {
	}

	public EntrepriseDTO(Long entrepriseId, String name) {
		this.entrepriseId = entrepriseId;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OfferDTO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferDTO> offers) {
		this.offers = offers;
	}

	public Long getEntrepriseId() {
		return entrepriseId;
	}

	public void setEntrepriseId(Long entrepriseId) {
		this.entrepriseId = entrepriseId;
	}

}
