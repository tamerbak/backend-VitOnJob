package com.vitonjob.dto;

import java.io.IOException;
import java.io.Serializable;

import javax.ws.rs.WebApplicationException;

import org.codehaus.jackson.map.ObjectMapper;

public class IndispensableDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private String[] requiredId;

	private Integer requiredPerCent;

	public IndispensableDTO() {
	}

	public static IndispensableDTO fromString(String jsonRepresentation) {
		ObjectMapper mapper = new ObjectMapper(); // Jackson's JSON marshaller
		IndispensableDTO o = null;
		try {
			o = mapper.readValue(jsonRepresentation, IndispensableDTO.class);
		} catch (IOException e) {
			throw new WebApplicationException();
		}
		return o;
	}

	public String[] getRequiredId() {
		return requiredId;
	}

	public void setRequiredId(String[] requiredId) {
		this.requiredId = requiredId;
	}

	public Integer getRequiredPerCent() {
		return requiredPerCent;
	}

	public void setRequiredPerCent(Integer requiredPerCent) {
		this.requiredPerCent = requiredPerCent;
	}

}
