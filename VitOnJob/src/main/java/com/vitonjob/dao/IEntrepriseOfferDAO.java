package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.RequiredDTO;
import com.vitonjob.entities.EntrepriseOffer;

public interface IEntrepriseOfferDAO extends IGenericDao<EntrepriseOffer> {

	/**
	 * vérifie si un employeur a une entreprise qui a une offre pour un job.
	 * 
	 * @param idEmployeur
	 *            l'id de l'employeur.
	 * @param libelleJob
	 *            le libellé du job.
	 * @return le nombre des resultats correspondant à la recherche.
	 */
	Long checkIfEntrepriseAOffrePourJob(Long idEmployeur, String libelleJob);

	/**
	 * récupére l'id de l'offre entreprise avec le libellé du job et l'id de
	 * l'employeur .
	 *
	 * @param idEmployeur
	 *            the id employeur
	 * @param libelleJob
	 *            the libelle job
	 * @return l'id de l'entreprise offer correspondante.
	 */
	Long getEntrepriseAOfferIdByLibelleJobAndIdEmployeur(Long idEmployeur, String libelleJob);

	/**
	 * Gets the required languages by entreprise offer.
	 *
	 * @param entrepriseOfferId
	 *            the entreprise offer id
	 * @return the required languages by entreprise offer
	 */
	List<RequiredDTO> getRequiredLanguagesByEntrepriseOffer(Long entrepriseOfferId);

	/**
	 * Gets the required jobs by entreprise offer.
	 *
	 * @param entrepriseOfferId
	 *            the entreprise offer id
	 * @return the required jobs by entreprise offer
	 */
	List<RequiredDTO> getRequiredJobsByEntrepriseOffer(Long entrepriseOfferId);

	/**
	 * Gets the required qualities by entreprise offer.
	 *
	 * @param entrepriseOfferId
	 *            the entreprise offer id
	 * @return the required qualities by entreprise offer
	 */
	List<Long> getRequiredQualitiesByEntrepriseOffer(Long entrepriseOfferId);
}
