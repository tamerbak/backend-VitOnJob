package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.EntrepriseDTO;
import com.vitonjob.entities.Entreprise;

public interface IEntrepriseDAO extends IGenericDao<Entreprise> {

	/**
	 * Gets the entreprises by employeur.
	 *
	 * @param employerId
	 *            the employer id
	 * @return the entreprises by employeur
	 */
	List<EntrepriseDTO> getEntreprisesByEmployeur(Long employerId);

	/**
	 * Geo locate entreprise.
	 *
	 * @param entrepriseId
	 *            the entreprise id
	 * @return the entreprise dto
	 */
	EntrepriseDTO geoLocateEntreprise(Long entrepriseId);

	/**
	 * Gets the entreprise id by offer id.
	 *
	 * @param entrepriseOfferId
	 *            the entreprise offer id
	 * @return the entreprise id by offer id
	 */
	Long getEntrepriseIdByOfferId(Long entrepriseOfferId);

}
