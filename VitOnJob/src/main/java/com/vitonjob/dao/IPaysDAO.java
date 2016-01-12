package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.PaysDTO;
import com.vitonjob.entities.Pays;

public interface IPaysDAO extends IGenericDao<Pays>  { 
	
	/**
	 * récupére toutes les pays.
	 * 
	 * @return List des pays.
	 */
	List<PaysDTO> getAllPays();
	
	Pays findPaysByNom(String nom);
}
