package com.vitonjob.dto;

import java.util.List;

public class EmployeurDTO extends UtilisateurDTO {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Long employerId;

	private List<EntrepriseDTO> entreprises;

	public EmployeurDTO(Long employerId, String employeEmail) {
		super(employeEmail);
		this.employerId = employerId;
	}

	public EmployeurDTO(Long employerId, String email, String telephone, boolean isNew) {
		super(email, telephone, isNew);
		this.employerId = employerId;
	}

	public EmployeurDTO() {
	}

	public List<EntrepriseDTO> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(List<EntrepriseDTO> entreprises) {
		this.entreprises = entreprises;
	}

	public Long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}
}
