package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.entities.Ville;

public interface IVilleDAO extends IGenericDao<Ville>  { 
	List<Ville> findVillesByNom(String nom);
}
