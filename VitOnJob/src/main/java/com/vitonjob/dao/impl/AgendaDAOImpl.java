package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IAgendaDAO;
import com.vitonjob.entities.Agenda;

@Repository("agendaDAO")
public class AgendaDAOImpl extends GenericDAOImpl<Agenda>implements IAgendaDAO {

	@Override
	public Agenda getAgendaByJobyer(Long jobyerId) {
		Query query = getCurrentSession()
				.createQuery("SELECT jobyer.agenda FROM Jobyer jobyer WHERE jobyer.id = :jobyerId");
		query.setParameter("jobyerId", jobyerId);
		return (Agenda) query.uniqueResult();
	}

}
