package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.IndexationJobyer;
import com.vitonjob.enums.TableIndexationEnum;

public interface IIndexationJobyerDAO extends IGenericDao<IndexationJobyer>  { 
	List<IndexationJobyer> findIndexationsByIndexes(List<Long> ids, TableIndexationEnum table);
}
