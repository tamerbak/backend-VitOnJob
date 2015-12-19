package com.vitonjob.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vitonjob.dao.IEntrepriseDAO;
import com.vitonjob.dao.IJobyerOfferDAO;
import com.vitonjob.dao.ITimePerTransportDAO;
import com.vitonjob.dto.EntrepriseDTO;
import com.vitonjob.dto.JobyerOfferDTO;
import com.vitonjob.entities.Entreprise;
import com.vitonjob.entities.JobyerOffer;
import com.vitonjob.entities.TimePerTransport;
import com.vitonjob.enums.ModeTransportEnum;
import com.vitonjob.utils.StringUtils;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component
@Path("/common/jobyerOffer")
public class JobyerOfferRestService {

	@Autowired
	private IJobyerOfferDAO jobyerOfferDAO;

	@Autowired
	private IEntrepriseDAO entrepriseDAO;

	@Autowired
	private ITimePerTransportDAO timePerTransportDAO;

	@Autowired
	private GoogleApiService googleApiService;

	@GET
	@Path("/getByLibelleJob")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JobyerOfferDTO> getJobyersOffersByLibelleJob(@QueryParam("libelleJob") String libelleJob,
			@QueryParam("idEntreprise") String idEntreprise, @QueryParam("idModeTransport") String idModeTransport) {

		List<JobyerOfferDTO> jobyersOffers = null;
		try {
			if (StringUtils.isEmpty(libelleJob) || StringUtils.isEmpty(idEntreprise)
					|| StringUtils.isEmpty(idModeTransport)) {
				return null;
			}

			ModeTransportEnum modeTransport = ModeTransportEnum.getById(Long.valueOf(idModeTransport));

			// Récupération de la longitude et la latitude de l'adresse
			// l'entreprise.
			EntrepriseDTO entrprise = entrepriseDAO.geoLocateEntreprise(Long.valueOf(idEntreprise));

			if (entrprise == null) {
				return null;
			}

			// Récupération de 200 jobyer offers correspondant au libellé job.
			jobyersOffers = jobyerOfferDAO.getListJobyerOfferByLibelleJob(libelleJob, 200);

			// Calcul de time per transport entre l'adresse du jobyer offer et
			// celle de l'entreprise pour chaque jobyer offer.
			Double timePerTransport;
			for (JobyerOfferDTO jobyerOffer : jobyersOffers) {
				timePerTransport = timePerTransportDAO.getTimePerTansportBetweenTwoAddresses(
						jobyerOffer.getJobyerAddressId(), entrprise.getEntrepriseAddressId(),
						Long.valueOf(idModeTransport));

				// si pas de time per transport dans la base, on appelle le web
				// service
				// Google
				// https://maps.googleapis.com/maps/api/distancematrix/json?origins=latitude,longitude&destinations=latitude,longitude&mode=driving
				if (timePerTransport == null) {
					timePerTransport = googleApiService.getDurationBetweenTwoAddresses(
							jobyerOffer.getLatitude() + "," + jobyerOffer.getLongitude(),
							entrprise.getLatitude() + "," + entrprise.getLongitude(), modeTransport.getLibelle());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobyersOffers;
	}

	public static void main(String[] args) {
		Client client = Client.create();

		// WebResource webResource = client
		// .resource("http://localhost:8080/VitOnJob/rest/common/jobyerOffer/getByLibelleJob");
		//
		// ClientResponse response = webResource.queryParam("libelleJob",
		// "job").queryParam("idEntreprise", "1")
		// .header("Authorization", "Basic " +
		// StringUtils.encode64("rachid@test.com:123456"))
		// .get(ClientResponse.class);

		WebResource webResource = client.resource("https://maps.googleapis.com/maps/api/distancematrix/json");

		ClientResponse response = webResource
				.queryParam("origins", "IMM%20A12%20R%C3%A9s%20Soufiane%20Sidi%20ma%C3%A2rouf%20Casablanca")
				.queryParam("destinations", "maarif").queryParam("mode", "driving").get(ClientResponse.class);

		InputStream inputStream = response.getEntityInputStream();

		try {
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();

			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);

			org.codehaus.jettison.json.JSONObject jsonObject = new org.codehaus.jettison.json.JSONObject(
					responseStrBuilder.toString());

			System.out.println(response.getStatus());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public IJobyerOfferDAO getJobyerOfferDAO() {
		return jobyerOfferDAO;
	}

	@Autowired
	public void setJobyerOfferDAO(IJobyerOfferDAO jobyerOfferDAO) {
		this.jobyerOfferDAO.setClazz(JobyerOffer.class);
		this.jobyerOfferDAO = jobyerOfferDAO;
	}

	public IEntrepriseDAO getEntrepriseDAO() {
		return entrepriseDAO;
	}

	@Autowired
	public void setEntrepriseDAO(IEntrepriseDAO entrepriseDAO) {
		this.entrepriseDAO.setClazz(Entreprise.class);
		this.entrepriseDAO = entrepriseDAO;
	}

	public ITimePerTransportDAO getTimePerTransportDAO() {
		return timePerTransportDAO;
	}

	@Autowired
	public void setTimePerTransportDAO(ITimePerTransportDAO timePerTransportDAO) {
		this.timePerTransportDAO.setClazz(TimePerTransport.class);
		this.timePerTransportDAO = timePerTransportDAO;
	}

	public GoogleApiService getGoogleApiService() {
		return googleApiService;
	}

	public void setGoogleApiService(GoogleApiService googleApiService) {
		this.googleApiService = googleApiService;
	}

}
