package com.vitonjob.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IIndexationJobyerDAO;
import com.vitonjob.dto.RechercheJobyerDTO;
import com.vitonjob.entities.IndexationJobyer;
import com.vitonjob.enums.TableIndexationEnum;

@Repository("indexationJobyerDAO")
public class IndexationJobyerDAO extends GenericDAOImpl<IndexationJobyer>implements IIndexationJobyerDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<RechercheJobyerDTO> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table) {
		Query q = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.RechercheJobyerDTO(jobyer.id, jobyer.titre, jobyer.nom, jobyer.prenom, jobyer.cin) from IndexationJobyer indexation INNER JOIN indexation.jobyer jobyer WHERE indexation.tableIndexation = :table AND indexation.index in (:indexes)");
		q.setString("table", table.name());
		q.setParameterList("indexes", ids);
		return q.list();
	}
}
