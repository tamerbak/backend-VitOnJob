package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.JobyerOfferDTO;
import com.vitonjob.entities.JobyerOffer;

public interface IJobyerOfferDAO extends IGenericDao<JobyerOffer> {

	/**
	 * r�cup�re la liste des jobyer offer par job libelle.
	 *
	 * @param libelleJob
	 *            le libell� du job.
	 * @param maxResults
	 *            le nombre maximale des r�sultats � r�cup�rer.
	 * @return {@link List} des {@link JobyerOfferDTO}
	 */
	List<JobyerOfferDTO> getListJobyerOfferByLibelleJob(String libelleJob, int maxResults);
}
