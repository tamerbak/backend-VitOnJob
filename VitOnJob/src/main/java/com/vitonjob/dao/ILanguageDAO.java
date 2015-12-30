package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.Language;

public interface ILanguageDAO extends IGenericDao<Language> {

	List<Language> findLanguesByLibelle(String libelle);

}
