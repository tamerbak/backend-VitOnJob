package com.vitonjob.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IMotCleDAO;
import com.vitonjob.entities.MotCle;

@Repository("motCleDAO")
public class MotCleDAOImpl extends GenericDAOImpl<MotCle>implements IMotCleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllMotsCle() {
		return getCurrentSession().createQuery("SELECT motcle.valeur FROM MotCle motcle").list();
	}

}
