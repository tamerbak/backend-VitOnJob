package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IIndexationEmployeurDAO;
import com.vitonjob.dto.RechercheEmployeurDTO;
import com.vitonjob.entities.IndexationEmployeur;
import com.vitonjob.enums.TableIndexationEnum;

@Repository("indexationEmployeurDAO")
public class IndexationEmployeurDAO extends GenericDAOImpl<IndexationEmployeur>implements IIndexationEmployeurDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<RechercheEmployeurDTO> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table) {
		Query q = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.RechercheEmployeurDTO(employeur.id, employeur.titre, employeur.nom, employeur.prenom) from IndexationEmployeur indexation INNER JOIN indexation.employeur employeur WHERE indexation.tableIndexation = :table AND indexation.index in (:indexes)");

		q.setString("table", table.name());
		q.setParameterList("indexes", ids);
		return q.list();
	}

}
