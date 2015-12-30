package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IPaysDAO;
import com.vitonjob.entities.Pays;

@Repository("paysDAO")
public class PaysDAO extends GenericDAOImpl<Pays>implements IPaysDAO {

	@Override
	public Pays findPaysByNom(String nom) {
		Query q = getCurrentSession().createQuery("from Pays where lower(nom) like '%"+nom+"%'");
		if(q.list()!= null && q.list().size()>0)
			return (Pays) q.list().get(0);
		return null;
	}

}
