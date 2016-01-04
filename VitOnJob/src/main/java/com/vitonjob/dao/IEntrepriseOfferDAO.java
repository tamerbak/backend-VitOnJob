package com.vitonjob.dao;

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
}
