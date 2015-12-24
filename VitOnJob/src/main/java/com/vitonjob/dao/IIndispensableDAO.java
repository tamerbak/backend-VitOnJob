package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.Indispensable;

public interface IIndispensableDAO extends IGenericDao<Indispensable> {

	public Long countIndispensablesByJobyer(Long jobyerId, List<Long> listIndispensableIds);

}
