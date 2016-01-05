package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IEntrepriseOfferDAO;
import com.vitonjob.dto.RequiredDTO;
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

	@Override
	public Long getEntrepriseAOfferIdByLibelleJobAndIdEmployeur(Long idEmployeur, String libelleJob) {
		Query query = getCurrentSession().createQuery(
				"SELECT entOffer.id FROM EntrepriseOffer entOffer INNER JOIN entOffer.listPracticeJob pjob INNER JOIN entOffer.entreprise ent WHERE ent.employeur.id = :idEmployeur AND pjob.job.libelle = :libelleJob");
		query.setParameter("idEmployeur", idEmployeur);
		query.setParameter("libelleJob", libelleJob);
		query.setMaxResults(1);
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequiredDTO> getRequiredLanguagesByEntrepriseOffer(Long entrepriseOfferId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.RequiredDTO(lang.id, level.id) FROM PracticeLanguage pLang INNER JOIN pLang.entrepriseOffer entrOffer INNER JOIN pLang.language lang INNER JOIN pLang.level level WHERE entrOffer.id = :entrepriseOfferId");
		query.setParameter("entrepriseOfferId", entrepriseOfferId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequiredDTO> getRequiredJobsByEntrepriseOffer(Long entrepriseOfferId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.RequiredDTO(job.id, level.id) FROM PracticeJob pJob INNER JOIN pJob.entrepriseOffer entrOffer INNER JOIN pJob.job job INNER JOIN pJob.level level WHERE entrOffer.id = :entrepriseOfferId");
		query.setParameter("entrepriseOfferId", entrepriseOfferId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getRequiredQualitiesByEntrepriseOffer(Long entrepriseOfferId) {
		Query query = getCurrentSession().createQuery(
				"SELECT indispensable.id FROM Indispensable indispensable INNER JOIN indispensable.entrepriseOffers entrOffer WHERE entrOffer.id = :entrepriseOfferId");
		query.setParameter("entrepriseOfferId", entrepriseOfferId);
		return query.list();
	}

}
