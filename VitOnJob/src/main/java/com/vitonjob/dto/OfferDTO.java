package com.vitonjob.dto;

import java.util.List;

public class OfferDTO {

	private Long offerId;

	private String title;

	private List<PracticeJobDTO> pricticesJob;

	private List<PracticeLanguageDTO> pricticesLanguage;

	public OfferDTO() {
	}

	public OfferDTO(Long offerId, String title) {
		super();
		this.offerId = offerId;
		this.title = title;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PracticeJobDTO> getPricticesJob() {
		return pricticesJob;
	}

	public void setPricticesJob(List<PracticeJobDTO> pricticesJob) {
		this.pricticesJob = pricticesJob;
	}

	public List<PracticeLanguageDTO> getPricticesLanguage() {
		return pricticesLanguage;
	}

	public void setPricticesLanguage(List<PracticeLanguageDTO> pricticesLanguage) {
		this.pricticesLanguage = pricticesLanguage;
	}

}
