package com.vitonjob.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VILLE")
public class Ville implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VILLE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NOM")
	private String Nom;

	@ManyToOne
	@JoinColumn(name = "PAYS_ID")
	private Pays pays;

	@ManyToMany
	@JoinTable(name = "VILLE_CODE_POSTAL", joinColumns = {
			@JoinColumn(name = "VILLE_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CODE_POSTAL_ID", nullable = false) })
	private Set<CodePostal> listCodePostal;

}