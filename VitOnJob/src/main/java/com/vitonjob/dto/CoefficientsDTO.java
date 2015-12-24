package com.vitonjob.dto;

import java.io.IOException;
import java.io.Serializable;

import javax.ws.rs.WebApplicationException;

import org.codehaus.jackson.map.ObjectMapper;

public class CoefficientsDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long coefficientLang;

	private Long coefficientJob;

	private Long coefficientQI;

	public CoefficientsDTO(Long coefficientLang, Long coefficientJob, Long coefficientQI) {
		this.coefficientLang = coefficientLang;
		this.coefficientJob = coefficientJob;
		this.coefficientQI = coefficientQI;
	}

	public CoefficientsDTO() {
	}

	public static CoefficientsDTO fromString(String jsonRepresentation) {
		ObjectMapper mapper = new ObjectMapper();
		CoefficientsDTO requiredDTO = null;
		try {
			requiredDTO = mapper.readValue(jsonRepresentation, CoefficientsDTO.class);
		} catch (IOException e) {
			throw new WebApplicationException();
		}
		return requiredDTO;
	}

	public Long getCoefficientLang() {
		return coefficientLang;
	}

	public void setCoefficientLang(Long coefficientLang) {
		this.coefficientLang = coefficientLang;
	}

	public Long getCoefficientJob() {
		return coefficientJob;
	}

	public void setCoefficientJob(Long coefficientJob) {
		this.coefficientJob = coefficientJob;
	}

	public Long getCoefficientQI() {
		return coefficientQI;
	}

	public void setCoefficientQI(Long coefficientQI) {
		this.coefficientQI = coefficientQI;
	}

}
