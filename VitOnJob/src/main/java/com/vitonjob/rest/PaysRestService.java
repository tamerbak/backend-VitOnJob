package com.vitonjob.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vitonjob.dao.IPaysDAO;
import com.vitonjob.dao.IRueDAO;
import com.vitonjob.dto.PaysDTO;
import com.vitonjob.dto.RueDTO;
import com.vitonjob.entities.Pays;
import com.vitonjob.entities.Rue;
import com.vitonjob.utils.StringUtils;

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
