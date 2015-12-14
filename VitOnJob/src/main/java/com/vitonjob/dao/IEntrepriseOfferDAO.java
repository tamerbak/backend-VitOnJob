package com.vitonjob.dao;

import com.vitonjob.entities.EntrepriseOffer;

public interface IEntrepriseOfferDAO extends IGenericDao<EntrepriseOffer> {

	/**
	 * v�rifie si un employeur a une entreprise qui a une offre pour un job.
	 * 
	 * @param idEmployeur
	 *            l'id de l'employeur.
	 * @param libelleJob
	 *            le libell� du job.
	 * @return le nombre des resultats correspondant � la recherche.
	 */
	Long checkIfEntrepriseAOffrePourJob(Long idEmployeur, String libelleJob);
}
