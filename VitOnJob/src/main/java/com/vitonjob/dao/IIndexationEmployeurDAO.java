package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.RechercheEmployeurDTO;
import com.vitonjob.entities.IndexationEmployeur;
import com.vitonjob.enums.TableIndexationEnum;

public interface IIndexationEmployeurDAO extends IGenericDao<IndexationEmployeur> {

	List<RechercheEmployeurDTO> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table);
}
