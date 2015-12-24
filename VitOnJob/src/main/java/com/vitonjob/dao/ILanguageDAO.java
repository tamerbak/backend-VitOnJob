package com.vitonjob.dao;

import com.vitonjob.entities.Agenda;
import com.vitonjob.entities.Language;

public interface ILanguageDAO extends IGenericDao<Language> {

	Agenda getAgendaByJobyer(Long jobyerId);

}
