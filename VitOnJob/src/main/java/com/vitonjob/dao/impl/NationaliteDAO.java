package com.vitonjob.dao.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.INationaliteDAO;
import com.vitonjob.dto.NationaliteDTO;
import com.vitonjob.entities.Nationalite;


@Repository("nationaliteDAO")
public class NationaliteDAO extends GenericDAOImpl<Nationalite>implements INationaliteDAO {
	@Override
	public NationaliteDTO getNationaliteById(Long idNationalite) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.NationaliteDTO(nat.nationaliteId) FROM Nationalite nat  WHERE nat.nationaliteId = :idNationalite");
		query.setParameter("idNationalite", idNationalite);
		return (NationaliteDTO) query.uniqueResult();
	}

}
