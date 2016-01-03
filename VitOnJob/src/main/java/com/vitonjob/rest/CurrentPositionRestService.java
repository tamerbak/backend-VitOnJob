package com.vitonjob.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitonjob.dao.ICurrentPositionDAO;
import com.vitonjob.dao.IJobyerDAO;
import com.vitonjob.entities.CurrentPosition;
import com.vitonjob.entities.Jobyer;
import com.vitonjob.utils.StringUtils;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component
@Path("/public/position")
public class CurrentPositionRestService {

	@Autowired
	private IJobyerDAO jobyerDAO;

	@Autowired
	private ICurrentPositionDAO currentPositionDAO;

	@POST
	@Path("/setCurrentPosition")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setCurrentPosition(String data) {
		if (StringUtils.isEmpty(data)) {
			throw new IllegalArgumentException("Il faut que le jobyer, la longitude et la latitude soient spécifiés");
		}
		JSONObject jsonLogin;
		try {
			jsonLogin = (JSONObject) new JSONParser().parse(data);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Les données en entrée ne sont pas valides.");
		}
		Long jobyerId = (Long) jsonLogin.get("jobyerId");
		Double longitude = jsonLogin.get("longitude") instanceof Double ? (Double) jsonLogin.get("longitude")
				: (Long) jsonLogin.get("longitude");
		Double latitude = jsonLogin.get("latitude") instanceof Double ? (Double) jsonLogin.get("latitude")
				: (Long) jsonLogin.get("latitude");
		Jobyer jobyer = jobyerDAO.findOne(jobyerId);

		if (jobyer == null) {
			throw new IllegalArgumentException("Aucun jobyer ne correspond à l'id " + jobyerId);
		}

		jobyer.setCurrentPosition(new CurrentPosition(longitude, latitude, new Date()));
		jobyerDAO.update(jobyer);
	}

	public IJobyerDAO getJobyerDAO() {
		return jobyerDAO;
	}

	@Autowired
	public void setJobyerDAO(IJobyerDAO jobyerDAO) {
		this.jobyerDAO = jobyerDAO;
		this.jobyerDAO.setClazz(Jobyer.class);
	}

	public ICurrentPositionDAO getCurrentPositionDAO() {
		return currentPositionDAO;
	}

	@Autowired
	public void setCurrentPositionDAO(ICurrentPositionDAO currentPositionDAO) {
		this.currentPositionDAO = currentPositionDAO;
		this.currentPositionDAO.setClazz(CurrentPosition.class);
	}

}
