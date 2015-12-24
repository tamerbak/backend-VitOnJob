package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.ILevelDAO;
import com.vitonjob.entities.Level;

@Repository("levelDAO")
public class LevelDAOImpl extends GenericDAOImpl<Level>implements ILevelDAO {

	@Override
	public Level getLevelByLanguageAndJobyer(Long languageId, Long jobyerId) {
		Query query = getCurrentSession().createQuery(
				"SELECT level FROM PracticeLanguage pLang INNER JOIN pLang.language lang INNER JOIN pLang.level level INNER JOIN pLang.jobyer jobyer WHERE lang.id = :languageId AND jobyer.id = :jobyerId");
		query.setParameter("languageId", languageId);
		query.setParameter("jobyerId", jobyerId);
		query.setMaxResults(1);
		return (Level) query.uniqueResult();
	}

	@Override
	public Level getLevelByJobAndJobyer(Long jobId, Long jobyerId) {
		Query query = getCurrentSession().createQuery(
				"SELECT level FROM PracticeJob pJob INNER JOIN pJob.job job INNER JOIN pJob.level level INNER JOIN pJob.jobyer jobyer WHERE job.id = :jobId AND jobyer.id = :jobyerId");
		query.setParameter("jobId", jobId);
		query.setParameter("jobyerId", jobyerId);
		query.setMaxResults(1);
		return (Level) query.uniqueResult();
	}

}
