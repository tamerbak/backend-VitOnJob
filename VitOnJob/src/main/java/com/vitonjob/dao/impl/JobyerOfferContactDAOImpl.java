package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IJobyerOfferContactDAO;
import com.vitonjob.entities.JobyerOfferContact;

@Repository("jobyerOfferContactDAO")
public class JobyerOfferContactDAOImpl extends GenericDAOImpl<JobyerOfferContact>implements IJobyerOfferContactDAO {

	@Override
	public Long countContactByEntreprise(Long idJobyerOffer, Long idEntreprise) {
		Query query = getCurrentSession().createQuery(
				"SELECT count(1) FROM Entreprise entreprise INNER JOIN entreprise.employeur employer1, JobyerOfferContact jOfferContact INNER JOIN jOfferContact.employeur employer2 WHERE jOfferContact.jobyerOffer.id = :idJobyerOffer AND entreprise.id = :idEntreprise AND employer1.id = employer2.id");
		query.setParameter("idEntreprise", idEntreprise);
		query.setParameter("idJobyerOffer", idJobyerOffer);
		return (Long) query.uniqueResult();
	}

}
