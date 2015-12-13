package com.vitonjob.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitonjob.dao.IRueDAO;
import com.vitonjob.dto.RueDTO;
import com.vitonjob.entities.Rue;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component
@Path("/rue")
public class RueRestService {

	@Autowired
	private IRueDAO rueDAO;

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RueDTO> getAllRues() {
		List<RueDTO> allRues = null;
		try {
			allRues = rueDAO.getAllRue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allRues;
	}

	public IRueDAO getRueDAO() {
		return rueDAO;
	}

	@Autowired
	public void setRueDAO(IRueDAO rueDAO) {
		this.rueDAO.setClazz(Rue.class);
		this.rueDAO = rueDAO;
	}

}
