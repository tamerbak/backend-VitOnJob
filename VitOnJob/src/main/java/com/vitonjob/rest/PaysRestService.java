package com.vitonjob.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.vitonjob.dao.IPaysDAO;
import com.vitonjob.dto.PaysDTO;
import com.vitonjob.entities.Pays;

/**
 * Classe implementant les services rest pour les objets Pays.
 */
@Component
@Path("/common/pays")
public class PaysRestService {
	@Autowired
	private IPaysDAO paysDAO;

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaysDTO> getAllPays() {
		List<PaysDTO> allPays = null;
		try {
			allPays = paysDAO.getAllPays();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allPays ;
	}

	public IPaysDAO getPaysDAO() {
		return paysDAO;
	}

	@Autowired
	public void setPaysDAO(IPaysDAO paysDAO) {
		this.paysDAO.setClazz(Pays.class);
		this.paysDAO = paysDAO;
	}
	

}
