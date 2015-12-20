package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IEntrepriseAddressDAO;
import com.vitonjob.entities.EntrepriseAddress;

@Repository("entrepriseAddressDAO")
public class EntrepriseAddressDAOImpl extends GenericDAOImpl<EntrepriseAddress>implements IEntrepriseAddressDAO {

	@Override
	public EntrepriseAddress findWorkAddressByEntrepriseId(Long idEntreprise) {
		Query query = getCurrentSession().createQuery(
				"SELECT entrAddress FROM EntrepriseAddress entrAddress INNER JOIN entrAddress.listEntreprise entreprise WHERE entreprise.id = :entrepriseId AND entrAddress.isWorkAddress = :isWorkAddress");
		query.setParameter("entrepriseId", idEntreprise);
		query.setParameter("isWorkAddress", true);
		return (EntrepriseAddress) query.uniqueResult();
	}

}
