package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.MotCle;

public interface IMotCleDAO extends IGenericDao<MotCle> {

	/**
	 * r�cup�re tous les mots cl� � ne pas consid�rer dans la recherche
	 * s�mantique.
	 * 
	 * @return {@link List} of {@link String}.
	 */
	List<String> getAllMotsCle();
}
