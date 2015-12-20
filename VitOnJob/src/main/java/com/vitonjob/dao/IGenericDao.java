package com.vitonjob.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

	void setClazz(Class<T> clazzToSet);

	T findOne(final long id);

	List<T> findAll();

	Long create(final T entity);

	void update(final T entity);

	void delete(final T entity);

	void deleteById(final long entityId);
}