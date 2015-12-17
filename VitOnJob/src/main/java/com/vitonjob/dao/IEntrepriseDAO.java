package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.EntrepriseDTO;
import com.vitonjob.entities.Entreprise;

public interface IEntrepriseDAO extends IGenericDao<Entreprise> {

	List<EntrepriseDTO> getEntreprisesByEmployeur(Long employerId);

}
