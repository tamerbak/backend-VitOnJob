package com.vitonjob.dao.impl;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IDistanceDAO;
import com.vitonjob.entities.Distance;

@Repository("distanceDAO")
public class DistanceDAOImpl extends GenericDAOImpl<Distance>implements IDistanceDAO {

}
