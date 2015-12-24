package com.vitonjob.dto;

import java.io.IOException;
import java.io.Serializable;

import javax.ws.rs.WebApplicationException;

import org.codehaus.jackson.map.ObjectMapper;

public class RequiredDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long requiredId;

	private Long requiredLevelId;

	public RequiredDTO(Long requiredId, Long requiredLevelId) {
		this.requiredId = requiredId;
		this.requiredLevelId = requiredLevelId;
	}

	public RequiredDTO() {
	}

	public static RequiredDTO fromString(String jsonRepresentation) {
		ObjectMapper mapper = new ObjectMapper();
		RequiredDTO requiredDTO = null;
		try {
			requiredDTO = mapper.readValue(jsonRepresentation, RequiredDTO.class);
		} catch (IOException e) {
			throw new WebApplicationException();
		}
		return requiredDTO;
	}

	public Long getRequiredId() {
		return requiredId;
	}

	public void setRequiredId(Long requiredId) {
		this.requiredId = requiredId;
	}

	public Long getRequiredLevelId() {
		return requiredLevelId;
	}

	public void setRequiredLevelId(Long requiredLevelId) {
		this.requiredLevelId = requiredLevelId;
	}

}
