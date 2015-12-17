package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IPracticeJobDAO;
import com.vitonjob.dto.PracticeJobDTO;
import com.vitonjob.entities.PracticeJob;

@Repository("practiceJobDAO")
public class PracticeJobDAOImpl extends GenericDAOImpl<PracticeJob>implements IPracticeJobDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PracticeJobDTO> getPracticesJobByOffer(Long offerId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.PracticeJobDTO(pJob.id, pJob.job.libelle, pJob.level.libelle) FROM PracticeJob pJob INNER JOIN pJob.entrepriseOffer entrOffer WHERE entrOffer.id = :offerId");
		query.setParameter("offerId", offerId);
		return query.list();
	}

}
