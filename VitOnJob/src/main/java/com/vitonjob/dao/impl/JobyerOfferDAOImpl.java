package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IJobyerOfferDAO;
import com.vitonjob.dto.JobyerOfferDTO;
import com.vitonjob.entities.JobyerOffer;
import com.vitonjob.utils.StringUtils;

@Repository("jobyerOfferDAO")
public class JobyerOfferDAOImpl extends GenericDAOImpl<JobyerOffer>implements IJobyerOfferDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JobyerOfferDTO> getListJobyerOfferByLibelleJob(String libelleJob, int maxResults) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.JobyerOfferDTO(jOffer.id, jobyer.prenom, jobyer.id, account.email, account.telephone, jAddress.id, address.longitude, address.latitude,curPosition.longitude, curPosition.latitude, curPosition.saveDate) FROM JobyerOffer jOffer INNER JOIN jOffer.job job INNER JOIN jOffer.jobyerAddress jAddress INNER JOIN jAddress.adresse address INNER JOIN jOffer.jobyer jobyer LEFT JOIN jobyer.currentPosition curPosition LEFT JOIN jobyer.account account WHERE upper(job.libelle) LIKE :libelleJob AND jAddress.isDepartToWorkAddress = :isDepartToWorkAddress ORDER BY jOffer.id DESC");
		query.setParameter("libelleJob",
				'%' + (StringUtils.isNotEmpty(libelleJob) ? libelleJob.toUpperCase() : "") + '%');
		query.setBoolean("isDepartToWorkAddress", true);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobyerOfferDTO> getListJobyerOfferByEntrepriseOfferId(Long entrepriseOfferId, int maxResults) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.JobyerOfferDTO(jOffer.id, jobyer.prenom, jobyer.id, account.email, account.telephone, jAddress.id, address.longitude, address.latitude,curPosition.longitude, curPosition.latitude, curPosition.saveDate) FROM JobyerOffer jOffer, EntrepriseOffer entrOffer INNER JOIN jOffer.job job INNER JOIN jOffer.jobyerAddress jAddress INNER JOIN jAddress.adresse address INNER JOIN jOffer.jobyer jobyer LEFT JOIN jobyer.currentPosition curPosition LEFT JOIN jobyer.account account INNER JOIN entrOffer.listPracticeJob pJob INNER JOIN pJob.job job2 WHERE entrOffer.id = :entrepriseOfferId AND job.id = job2.id AND jAddress.isDepartToWorkAddress = :isDepartToWorkAddress ORDER BY jOffer.id DESC");
		query.setParameter("entrepriseOfferId", entrepriseOfferId);
		query.setBoolean("isDepartToWorkAddress", true);
		query.setMaxResults(maxResults);
		return query.list();
	}

}
