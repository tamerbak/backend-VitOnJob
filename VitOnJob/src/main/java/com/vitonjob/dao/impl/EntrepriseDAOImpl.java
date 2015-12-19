package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IEntrepriseDAO;
import com.vitonjob.dto.EntrepriseDTO;
import com.vitonjob.entities.Entreprise;

@Repository("entrepriseDAO")
public class EntrepriseDAOImpl extends GenericDAOImpl<Entreprise>implements IEntrepriseDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<EntrepriseDTO> getEntreprisesByEmployeur(Long employerId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.EntrepriseDTO(entr.id, entr.nomOuRaisonSociale) FROM Entreprise entr INNER JOIN entr.employeur empl WHERE empl.id = :emplId ORDER BY entr.nomOuRaisonSociale");
		query.setParameter("emplId", employerId);
		return query.list();
	}

	@Override
	public EntrepriseDTO geoLocateEntreprise(Long entrepriseId) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.EntrepriseDTO(entrAddress.id, address.longitude, address.latitude) FROM Entreprise entr INNER JOIN entr.listEntrepriseAddress entrAddress INNER JOIN entrAddress.adresse address WHERE entr.id = :entrepriseId AND entrAddress.isWorkAddress = :isWorkAddress");
		query.setParameter("entrepriseId", entrepriseId);
		query.setParameter("isWorkAddress", true);
		return (EntrepriseDTO) query.uniqueResult();
	}

}
