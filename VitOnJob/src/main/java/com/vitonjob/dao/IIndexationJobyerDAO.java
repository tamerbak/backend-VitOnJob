package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.RechercheJobyerDTO;
import com.vitonjob.entities.IndexationJobyer;
import com.vitonjob.enums.TableIndexationEnum;

public interface IIndexationJobyerDAO extends IGenericDao<IndexationJobyer> {

	List<RechercheJobyerDTO> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table);
}
