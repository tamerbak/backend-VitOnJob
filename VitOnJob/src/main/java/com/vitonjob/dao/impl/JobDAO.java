package com.vitonjob.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IJobDAO;
import com.vitonjob.entities.Job;

@Repository("jobDAO")
public class JobDAO extends GenericDAOImpl<Job>implements IJobDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findJobsByLibelle(String Libelle) {
		Query q = getCurrentSession().createQuery("from Job where lower(libelle) like '%"+Libelle+"%'");
		if(q.list()!=null)
			return q.list();
		return new ArrayList<Job>();
	}

	

}
