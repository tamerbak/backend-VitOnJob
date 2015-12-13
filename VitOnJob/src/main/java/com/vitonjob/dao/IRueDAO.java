package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.RueDTO;
import com.vitonjob.entities.Rue;

public interface IRueDAO extends IGenericDao<Rue> {

	/**
	 * récupère toutes les rues.
	 * 
	 * @return List des rues.
	 */
	List<RueDTO> getAllRue();

}
