package com.vitonjob.dao.impl;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.ICurrentPositionDAO;
import com.vitonjob.entities.CurrentPosition;

@Repository("currentPositionDAO")
public class CurrentPositionDAOImpl extends GenericDAOImpl<CurrentPosition>implements ICurrentPositionDAO {

}
