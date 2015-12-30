package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.Job;

public interface IJobDAO extends IGenericDao<Job>  { 
	List<Job> findJobsByLibelle(String libelle);
}
