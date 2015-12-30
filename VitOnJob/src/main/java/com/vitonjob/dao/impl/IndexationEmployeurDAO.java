package com.vitonjob.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IIndexationEmployeurDAO;
import com.vitonjob.entities.IndexationEmployeur;
import com.vitonjob.enums.TableIndexationEnum;

@Repository("indexationEmployeurDAO")
public class IndexationEmployeurDAO extends GenericDAOImpl<IndexationEmployeur>implements IIndexationEmployeurDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<IndexationEmployeur> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table) {
		Query q = getCurrentSession().createQuery("from IndexationEmployeur where tableIndexation=:table and index in (:indexes)");
		q.setString("table", table.name());
		q.setParameterList("indexes", ids);
		if(q.list()!=null)
			return q.list();
		return new ArrayList<IndexationEmployeur>();
	}

	

}
