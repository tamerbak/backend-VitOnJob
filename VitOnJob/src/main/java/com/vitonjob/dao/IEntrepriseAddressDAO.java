package com.vitonjob.dao;

import com.vitonjob.entities.EntrepriseAddress;

public interface IEntrepriseAddressDAO extends IGenericDao<EntrepriseAddress> {

	EntrepriseAddress findWorkAddressByEntrepriseId(Long idEntreprise);

}
