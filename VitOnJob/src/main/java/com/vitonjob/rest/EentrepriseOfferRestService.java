package com.vitonjob.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitonjob.dao.IEntrepriseOfferDAO;
import com.vitonjob.entities.EntrepriseOffer;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component
@Path("/entrepriseOffer")
public class EentrepriseOfferRestService {

	@Autowired
	private IEntrepriseOfferDAO entrepriseOfferDAO;

	@GET
	@Path("/checkIfEntrepriseAOffrePourJob")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean checkIfEntrepriseAOffrePourJob(@QueryParam("idEmployeur") String idEmployeur,
			@QueryParam("libelleJob") String libelleJob) {
		Boolean entrepriseAOffrePourJob = null;
		try {
			Long idEmployeurLong = Long.parseLong(idEmployeur);
			Long nombreOffres = entrepriseOfferDAO.checkIfEntrepriseAOffrePourJob(idEmployeurLong, libelleJob);
			entrepriseAOffrePourJob = nombreOffres != null && nombreOffres > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrepriseAOffrePourJob;
	}

	public IEntrepriseOfferDAO getEntrepriseOfferDAO() {
		return entrepriseOfferDAO;
	}

	@Autowired
	public void setEntrepriseOfferDAO(IEntrepriseOfferDAO entrepriseOfferDAO) {
		this.entrepriseOfferDAO.setClazz(EntrepriseOffer.class);
		this.entrepriseOfferDAO = entrepriseOfferDAO;
	}

}
