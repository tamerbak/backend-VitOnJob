package com.vitonjob.dao;

import com.vitonjob.entities.Agenda;

public interface IAgendaDAO extends IGenericDao<Agenda> {

	Agenda getAgendaByJobyer(Long jobyerId);

}
