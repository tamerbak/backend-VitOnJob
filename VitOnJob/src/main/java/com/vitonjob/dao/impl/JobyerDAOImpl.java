package com.vitonjob.dao.impl;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IJobyerDAO;
import com.vitonjob.entities.Jobyer;

@Repository("jobyerDAO")
public class JobyerDAOImpl extends GenericDAOImpl<Jobyer>implements IJobyerDAO {

}
