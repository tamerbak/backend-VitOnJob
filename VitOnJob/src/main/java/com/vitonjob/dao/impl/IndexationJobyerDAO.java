package com.vitonjob.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IIndexationJobyerDAO;
import com.vitonjob.entities.IndexationJobyer;
import com.vitonjob.enums.TableIndexationEnum;

@Repository("indexationJobyerDAO")
public class IndexationJobyerDAO extends GenericDAOImpl<IndexationJobyer>implements IIndexationJobyerDAO {

	@Override
	public List<IndexationJobyer> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table) {
		Query q = getCurrentSession().createQuery("from IndexationJobyer where tableIndexation=:table and index in (:indexes)");
		q.setString("table", table.name());
		q.setParameterList("indexes", ids);
		if(q.list()!=null)
			return q.list();
		return new ArrayList<IndexationJobyer>();
	}
}
