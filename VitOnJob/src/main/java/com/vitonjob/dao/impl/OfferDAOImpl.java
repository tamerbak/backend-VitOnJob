package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IOfferDAO;
import com.vitonjob.dto.OfferDTO;
import com.vitonjob.entities.EntrepriseOffer;

@Repository("offerDAO")
public class OfferDAOImpl extends GenericDAOImpl<EntrepriseOffer>implements IOfferDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<OfferDTO> getOffresByEntreprise(Long entrepriseId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.OfferDTO(offr.id, offr.titre) FROM EntrepriseOffer offr INNER JOIN offr.entreprise entr WHERE entr.id = :entrepriseId");
		query.setParameter("entrepriseId", entrepriseId);
		return query.list();
	}

}
