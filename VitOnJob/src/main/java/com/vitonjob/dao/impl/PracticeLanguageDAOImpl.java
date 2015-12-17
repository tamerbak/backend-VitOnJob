package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IPracticeLanguageDAO;
import com.vitonjob.dto.PracticeLanguageDTO;
import com.vitonjob.entities.PracticeLanguage;

@Repository("practiceLanguageDAO")
public class PracticeLanguageDAOImpl extends GenericDAOImpl<PracticeLanguage>implements IPracticeLanguageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PracticeLanguageDTO> getPracticeLanguageByOffer(Long offerId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.PracticeLanguageDTO(pLanguage.id, pLanguage.language.libelle, pLanguage.level.libelle) FROM PracticeLanguage pLanguage INNER JOIN pLanguage.entrepriseOffer entrOffer WHERE entrOffer.id = :offerId");
		query.setParameter("offerId", offerId);
		return query.list();
	}

}
