package com.vitonjob.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IVilleDAO;
import com.vitonjob.entities.Ville;

@Repository("villeDAO")
public class VilleDAO extends GenericDAOImpl<Ville>implements IVilleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ville> findVillesByNom(String nom) {
		Query q = getCurrentSession().createQuery("from Ville where lower(Nom) like '%"+nom+"%'");
		if(q.list()!=null)
			return q.list();
		return new ArrayList<Ville>();
	}

	

}
