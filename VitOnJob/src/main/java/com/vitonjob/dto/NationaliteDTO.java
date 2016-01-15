package com.vitonjob.dto;

public class NationaliteDTO {

	private Long nationaliteId;
	
	public NationaliteDTO() {
	}

	public NationaliteDTO(Long nationaliteId) {
		super();
		this.nationaliteId = nationaliteId;
	}

	public Long getNationaliteId() {
		return nationaliteId;
	}

	public void setNationaliteId(Long nationaliteId) {
		this.nationaliteId = nationaliteId;
	}
}
