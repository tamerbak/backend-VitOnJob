package com.vitonjob.dao;

import com.vitonjob.entities.Pays;

public interface IPaysDAO extends IGenericDao<Pays>  { 
	Pays findPaysByNom(String nom);
}
