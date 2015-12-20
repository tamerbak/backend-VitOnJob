package com.vitonjob.dao;

import com.vitonjob.entities.JobyerOfferContact;

public interface IJobyerOfferContactDAO extends IGenericDao<JobyerOfferContact> {

	Long countContactByEntreprise(Long idJobyerOffer, Long idEntreprise);
}
