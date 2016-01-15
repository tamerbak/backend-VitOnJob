package com.vitonjob.dto;

import java.util.List;

import com.vitonjob.entities.Nationalite;

public class JobyerDTO extends UtilisateurDTO {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long jobyerId;
	
	private List<JobyerOfferDTO> offers;
	
	private Nationalite nationalite;

	public JobyerDTO(Long jobyerId, String jobyerEmail) {
		super(jobyerEmail);
		this.jobyerId = jobyerId;
	}

	public JobyerDTO(Long jobyerId, String email, String telephone, boolean isNew) {
		super(email, telephone, isNew);
		this.jobyerId = jobyerId;
	}

	public JobyerDTO() {
	}

	public Long getJobyerId() {
		return jobyerId;
	}

	public void setjobyerId(Long jobyerId) {
		this.jobyerId = jobyerId;
	}
	
	public List<JobyerOfferDTO> getOffers() {
		return offers;
	}

	public void setOffers(List<JobyerOfferDTO> offers) {
		this.offers = offers;
	}
	
	public Nationalite getNationalite(){
		return nationalite;
	}
	
	public void setNationalite(Nationalite nationalite){
		this.nationalite = nationalite;
	}
}
