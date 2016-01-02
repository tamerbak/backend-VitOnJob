package com.vitonjob.enums;

/**
 * Enumération contenant les valeurs possibles du mode de transport.
 *
 */
public enum ModeTransportEnum {

	DRIVING(1L, "driving"), WALKING(2L, "walking");

	private Long id;

	private String libelle;

	private ModeTransportEnum(Long id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public static ModeTransportEnum getById(Long id) {
		for (ModeTransportEnum modeTransportEnum : values()) {
			if (modeTransportEnum.getId().equals(id)) {
				return modeTransportEnum;
			}
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
