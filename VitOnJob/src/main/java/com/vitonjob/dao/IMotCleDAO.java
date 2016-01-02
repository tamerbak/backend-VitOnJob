package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.MotCle;

public interface IMotCleDAO extends IGenericDao<MotCle> {

	/**
	 * récupére tous les mots clé à ne pas considérer dans la recherche
	 * sémantique.
	 * 
	 * @return {@link List} of {@link String}.
	 */
	List<String> getAllMotsCle();
}
