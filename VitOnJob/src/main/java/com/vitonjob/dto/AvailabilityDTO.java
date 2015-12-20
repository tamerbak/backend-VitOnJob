package com.vitonjob.dto;

import java.io.Serializable;

import com.vitonjob.utils.DateUtils;

public class AvailabilityDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long value;

	private String text;

	public AvailabilityDTO(Long valueInSecondes) {
		this.value = valueInSecondes / 60;
		text = DateUtils.getDurationTextFromSecondes(valueInSecondes);
	}

	public AvailabilityDTO() {
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
