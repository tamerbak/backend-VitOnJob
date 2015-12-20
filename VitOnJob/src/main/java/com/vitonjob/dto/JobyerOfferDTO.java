package com.vitonjob.dto;

import java.io.Serializable;

public class JobyerOfferDTO implements Serializable, Comparable<JobyerOfferDTO> {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long jobyerOfferId;

	private String jobyerName;

	private Double longitude;

	private Double latitude;

	private Double longitudeAddress;

	private Double latitudeAddress;

	private Double longitudeCurrentPosition;

	private Double latitudeCurrentPosition;

	private boolean on;

	private Long jobyerAddressId;

	private Long jobyerId;

	private AvailabilityDTO availability;

	public JobyerOfferDTO(Long jobyerOfferId, String jobyerName, Long jobyerId, Long jobyerAddressId,
			Double longitudeAddress, Double latitudeAddress, Double longitudeCurrentPosition,
			Double latitudeCurrentPosition) {
		this.jobyerOfferId = jobyerOfferId;
		this.jobyerName = jobyerName;
		this.jobyerId = jobyerId;
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

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public Long getJobyerAddressId() {
		return jobyerAddressId;
	}

	public void setJobyerAddressId(Long jobyerAddressId) {
		this.jobyerAddressId = jobyerAddressId;
	}

	public Long getJobyerId() {
		return jobyerId;
	}

	public void setJobyerId(Long jobyerId) {
		this.jobyerId = jobyerId;
	}

	public AvailabilityDTO getAvailability() {
		return availability;
	}

	public void setAvailability(AvailabilityDTO availability) {
		this.availability = availability;
	}

	@Override
	public int compareTo(JobyerOfferDTO o) {
		return this.getAvailability().getValue().compareTo(o.getAvailability().getValue());
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

	public Double getLongitudeAddress() {
		return longitudeAddress;
	}

	public void setLongitudeAddress(Double longitudeAddress) {
		this.longitudeAddress = longitudeAddress;
	}

	public Double getLatitudeAddress() {
		return latitudeAddress;
	}

	public void setLatitudeAddress(Double latitudeAddress) {
		this.latitudeAddress = latitudeAddress;
	}

	public Double getLongitudeCurrentPosition() {
		return longitudeCurrentPosition;
	}

	public void setLongitudeCurrentPosition(Double longitudeCurrentPosition) {
		this.longitudeCurrentPosition = longitudeCurrentPosition;
	}

	public Double getLatitudeCurrentPosition() {
		return latitudeCurrentPosition;
	}

	public void setLatitudeCurrentPosition(Double latitudeCurrentPosition) {
		this.latitudeCurrentPosition = latitudeCurrentPosition;
	}

	public Long getJobyerOfferId() {
		return jobyerOfferId;
	}

	public void setJobyerOfferId(Long jobyerOfferId) {
		this.jobyerOfferId = jobyerOfferId;
	}

}
