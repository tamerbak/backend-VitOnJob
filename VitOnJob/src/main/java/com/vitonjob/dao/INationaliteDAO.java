package com.vitonjob.dao;

import com.vitonjob.dto.NationaliteDTO;
import com.vitonjob.entities.Nationalite;


public interface INationaliteDAO extends IGenericDao<Nationalite>  { 
	
	NationaliteDTO getNationaliteById(Long idNationalite);
}
