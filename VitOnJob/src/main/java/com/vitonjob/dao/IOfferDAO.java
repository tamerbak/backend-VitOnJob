package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.OfferDTO;
import com.vitonjob.entities.EntrepriseOffer;

public interface IOfferDAO extends IGenericDao<EntrepriseOffer> {

	List<OfferDTO> getOffresByEntreprise(Long entrepriseId);

}
