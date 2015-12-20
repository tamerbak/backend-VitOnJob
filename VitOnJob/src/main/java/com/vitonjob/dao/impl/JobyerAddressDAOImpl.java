package com.vitonjob.dao.impl;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IJobyerAddressDAO;
import com.vitonjob.entities.JobyerAddress;

@Repository("jobyerAddressDAO")
public class JobyerAddressDAOImpl extends GenericDAOImpl<JobyerAddress>implements IJobyerAddressDAO {

}
