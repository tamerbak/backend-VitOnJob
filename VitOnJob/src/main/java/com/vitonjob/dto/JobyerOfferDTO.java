package com.vitonjob.dto;

import java.io.Serializable;

public class JobyerOfferDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private String jobyerName;

	private Integer longitude;

	private Integer latitude;

	private Integer longitudeAddress;

	private Integer latitudeAddress;

	private Integer longitudeCurrentPosition;

	private Integer latitudeCurrentPosition;

	private boolean on;

	private Long jobyerAddressId;

	public JobyerOfferDTO(String jobyerName, Long jobyerAddressId, Integer longitudeAddress, Integer latitudeAddress,
			Integer longitudeCurrentPosition, Integer latitudeCurrentPosition) {
		this.jobyerName = jobyerName;
		this.jobyerAddressId = jobyerAddressId;
		this.longitudeAddress = longitudeAddress;
		this.latitudeAddress = latitudeAddress;
		this.longitudeCurrentPosition = longitudeCurrentPosition;
		this.latitudeCurrentPosition = latitudeCurrentPosition;

		if (longitudeCurrentPosition != null) {
			this.longitude = longitudeCurrentPosition;
		} else if (longitudeAddress != null) {
			this.longitude = longitudeAddress;
		}

		if (latitudeCurrentPosition != null) {
			this.latitude = latitudeCurrentPosition;
		} else if (latitudeAddress != null) {
			this.latitude = latitudeAddress;
		}
	}

	public String getJobyerName() {
		return jobyerName;
	}

	public void setJobyerName(String jobyerName) {
		this.jobyerName = jobyerName;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public Integer getLongitudeAddress() {
		return longitudeAddress;
	}

	public void setLongitudeAddress(Integer longitudeAddress) {
		this.longitudeAddress = longitudeAddress;
	}

	public Integer getLatitudeAddress() {
		return latitudeAddress;
	}

	public void setLatitudeAddress(Integer latitudeAddress) {
		this.latitudeAddress = latitudeAddress;
	}

	public Integer getLongitudeCurrentPosition() {
		return longitudeCurrentPosition;
	}

	public void setLongitudeCurrentPosition(Integer longitudeCurrentPosition) {
		this.longitudeCurrentPosition = longitudeCurrentPosition;
	}

	public Integer getLatitudeCurrentPosition() {
		return latitudeCurrentPosition;
	}

	public void setLatitudeCurrentPosition(Integer latitudeCurrentPosition) {
		this.latitudeCurrentPosition = latitudeCurrentPosition;
	}

	public Long getJobyerAddressId() {
		return jobyerAddressId;
	}

	public void setJobyerAddressId(Long jobyerAddressId) {
		this.jobyerAddressId = jobyerAddressId;
	}

}
