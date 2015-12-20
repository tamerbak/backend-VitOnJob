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

	private Double longitude;

	private Double latitude;

	private Long entrepriseAddressId;

	public EntrepriseDTO() {
	}

	public EntrepriseDTO(Long entrepriseId, String name) {
		this.entrepriseId = entrepriseId;
		this.name = name;
	}

	public EntrepriseDTO(Long entrepriseAddressId, Double longitude, Double latitude) {
		this.entrepriseAddressId = entrepriseAddressId;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public Long getEntrepriseAddressId() {
		return entrepriseAddressId;
	}

	public void setEntrepriseAddressId(Long entrepriseAddressId) {
		this.entrepriseAddressId = entrepriseAddressId;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
