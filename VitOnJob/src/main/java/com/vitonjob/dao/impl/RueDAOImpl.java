package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IRueDAO;
import com.vitonjob.dto.RueDTO;
import com.vitonjob.entities.Rue;

@Repository("rueDAO")
public class RueDAOImpl extends GenericDAOImpl<Rue>implements IRueDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<RueDTO> getAllRue() {
		Query query = getCurrentSession()
				.createQuery("SELECT new com.vitonjob.dto.RueDTO(rue.nom) FROM Rue rue ORDER BY rue.nom");
		return query.list();
	}

}
