package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IIndispensableDAO;
import com.vitonjob.entities.Indispensable;

@Repository("indispensableDAO")
public class IndispensableDAOImpl extends GenericDAOImpl<Indispensable>implements IIndispensableDAO {

	@Override
	public Long countIndispensablesByJobyer(Long jobyerId, List<Long> listIndispensableIds) {
		Query query = getCurrentSession().createQuery(
				"SELECT count(1) FROM Indispensable indispensable INNER JOIN indispensable.jobyers jobyer WHERE jobyer.id = :jobyerId AND indispensable.id IN :listIndispensableIds");
		query.setParameter("jobyerId", jobyerId);
		query.setParameterList("listIndispensableIds", listIndispensableIds);
		return (Long) query.uniqueResult();
	}

}
