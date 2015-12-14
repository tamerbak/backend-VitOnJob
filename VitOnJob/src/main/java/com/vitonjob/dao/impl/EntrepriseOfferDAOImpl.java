package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IEntrepriseOfferDAO;
import com.vitonjob.entities.EntrepriseOffer;

@Repository("entrepriseOfferDAO")
public class EntrepriseOfferDAOImpl extends GenericDAOImpl<EntrepriseOffer>implements IEntrepriseOfferDAO {

	@Override
	public Long checkIfEntrepriseAOffrePourJob(Long idEmployeur, String libelleJob) {
		Query query = getCurrentSession().createQuery(
				"SELECT COUNT(1) FROM EntrepriseOffer entOffer INNER JOIN entOffer.listPracticeJob pjob INNER JOIN entOffer.entreprise ent WHERE ent.employeur.id = :idEmployeur AND pjob.job.libelle = :libelleJob");
		query.setParameter("idEmployeur", idEmployeur);
		query.setParameter("libelleJob", libelleJob);
		return (Long) query.uniqueResult();
	}

}
