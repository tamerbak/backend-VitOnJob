package com.vitonjob.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.ILanguageDAO;
import com.vitonjob.entities.Language;

@Repository("languageDAO")
public class LanguageDAO extends GenericDAOImpl<Language>implements ILanguageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Language> findLanguesByLibelle(String Libelle) {
		Query q = getCurrentSession().createQuery("from Language where lower(libelle) like '%"+Libelle+"%'");
		if(q.list()!=null)
			return q.list();
		return new ArrayList<Language>();
	}

	

}
