package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JOBYER_ADDRESS")
public class JobyerAddress implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "JOBYER_ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "IS_PERSONAL_ADDRESS")
	private Boolean isPersonalAddress;

	@Column(name = "IS_DEPART_TO_WORK_ADDRESS")
	private Boolean isDepartToWorkAddress;

	@ManyToMany(mappedBy = "listJobyerAddress", fetch = FetchType.LAZY)
	private Set<Jobyer> listJobyer;

	@ManyToOne
	@JoinColumn(name = "ADRESSE_ID")
	private Adresse adresse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsPersonalAddress() {
		return isPersonalAddress;
	}

	public void setIsPersonalAddress(Boolean isPersonalAddress) {
		this.isPersonalAddress = isPersonalAddress;
	}

	public Boolean getIsDepartToWorkAddress() {
		return isDepartToWorkAddress;
	}

	public void setIsDepartToWorkAddress(Boolean isDepartToWorkAddress) {
		this.isDepartToWorkAddress = isDepartToWorkAddress;
	}

	public Set<Jobyer> getListJobyer() {
		return listJobyer;
	}

	public void setListJobyer(Set<Jobyer> listJobyer) {
		this.listJobyer = listJobyer;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}