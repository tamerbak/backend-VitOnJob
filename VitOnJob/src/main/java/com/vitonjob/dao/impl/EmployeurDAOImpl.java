package com.vitonjob.dao.impl;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IEmployeurDAO;
import com.vitonjob.entities.Employeur;

@Repository("employeurDAO")
public class EmployeurDAOImpl extends GenericDAOImpl<Employeur>implements IEmployeurDAO {

}
