package com.vitonjob.rest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vitonjob.dao.IAgendaDAO;
import com.vitonjob.dao.IEntrepriseAddressDAO;
import com.vitonjob.dao.IEntrepriseDAO;
import com.vitonjob.dao.IJobyerAddressDAO;
import com.vitonjob.dao.IJobyerOfferContactDAO;
import com.vitonjob.dao.IJobyerOfferDAO;
import com.vitonjob.dao.ITimePerTransportDAO;
import com.vitonjob.dao.ITransportDAO;
import com.vitonjob.dto.AvailabilityDTO;
import com.vitonjob.dto.JobyerOfferDTO;
import com.vitonjob.entities.Agenda;
import com.vitonjob.entities.Disponibilite;
import com.vitonjob.entities.Entreprise;
import com.vitonjob.entities.EntrepriseAddress;
import com.vitonjob.entities.JobyerAddress;
import com.vitonjob.entities.JobyerOffer;
import com.vitonjob.entities.JobyerOfferContact;
import com.vitonjob.entities.PlageHoraire;
import com.vitonjob.entities.Repetition;
import com.vitonjob.entities.TimePerTransport;
import com.vitonjob.entities.Transport;
import com.vitonjob.utils.CollectionUtils;
import com.vitonjob.utils.DateUtils;
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

	@Autowired
	private IAgendaDAO agendaDAO;

	@Autowired
	private ITransportDAO transportDAO;

	@Autowired
	private IEntrepriseAddressDAO entrepriseAddressDAO;

	@Autowired
	private IJobyerAddressDAO jobyerAddressDAO;

	@Autowired
	private IJobyerOfferContactDAO jobyerOfferContactDAO;

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

			Transport modeTransport = transportDAO.findOne(Long.valueOf(idModeTransport));
			EntrepriseAddress entrepriseAddress = entrepriseAddressDAO
					.findWorkAddressByEntrepriseId(Long.valueOf(idEntreprise));

			if (modeTransport == null || entrepriseAddress == null) {
				return null;
			}

			// Récupération de 200 jobyer offers correspondant au libellé job.
			jobyersOffers = jobyerOfferDAO.getListJobyerOfferByLibelleJob(libelleJob, 200);

			// Calcul de time per transport entre l'adresse du jobyer offer et
			// celle de l'entreprise pour chaque jobyer offer.
			Double timePerTransport;
			Agenda agenda;
			Long dureeAvantDisponibilite;
			JobyerAddress jobyerAddress;
			TimePerTransport timePerTransportToSave;
			for (JobyerOfferDTO jobyerOffer : jobyersOffers) {
				timePerTransport = timePerTransportDAO.getTimePerTansportBetweenTwoAddresses(
						jobyerOffer.getJobyerAddressId(), entrepriseAddress.getId(), Long.valueOf(idModeTransport));

				// si pas de time per transport dans la base, on appelle le web
				// service
				// Google
				// https://maps.googleapis.com/maps/api/distancematrix/json?origins=latitude,longitude&destinations=latitude,longitude&mode=driving
				if (timePerTransport == null) {
					timePerTransport = googleApiService
							.getDurationBetweenTwoAddresses(
									jobyerOffer.getLatitude() + "," + jobyerOffer.getLongitude(),
									entrepriseAddress.getAdresse().getLatitude() + ","
											+ entrepriseAddress.getAdresse().getLongitude(),
							modeTransport.getLibelle());

					// On enregistre le time per transport dans la base de
					// données
					timePerTransportToSave = new TimePerTransport();
					timePerTransportToSave.setValeur(timePerTransport);
					timePerTransportToSave.setEntrepriseAddress(entrepriseAddress);
					jobyerAddress = jobyerAddressDAO.findOne(jobyerOffer.getJobyerAddressId());
					timePerTransportToSave.setJobyerAddress(jobyerAddress);
					timePerTransportToSave.setTransport(modeTransport);
					timePerTransportDAO.create(timePerTransportToSave);
				}

				// Calcul de la disponibilité du jobyer : la diffrence entre la
				// datetime de début de la plus prôche disponibilité (agenda du
				// jobyer) du jobyer et la datetime courante (now)
				agenda = agendaDAO.getAgendaByJobyer(jobyerOffer.getJobyerId());

				dureeAvantDisponibilite = getDureeAvantDisponibilite(agenda, new Date());

				jobyerOffer.setAvailability(new AvailabilityDTO(timePerTransport.intValue() + dureeAvantDisponibilite));

				// Set la valeur de on : est ce que l'employeur a déjà consulté
				// le jobyer offer
				jobyerOffer.setOn(jobyerOfferContactDAO.countContactByEntreprise(jobyerOffer.getJobyerOfferId(),
						Long.valueOf(idEntreprise)) > 0);
			}

			Collections.sort(jobyersOffers);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobyersOffers;
	}

	private Long getDureeAvantDisponibilite(Agenda agenda, Date now) {
		Long duree = null;
		Date toDay = new Date();
		if (CollectionUtils.isNotEmpty(agenda.getListDisponibilites())) {
			for (Disponibilite disponibilite : agenda.getListDisponibilites()) {
				if (disponibilite.getDateDeFin().before(now)) {
					continue;
				}
				if (CollectionUtils.isNotEmpty(disponibilite.getListPlageHoraire())) {
					Date date;
					String dayName;
					Date dateDebutPossible;
					for (PlageHoraire plageHoraire : disponibilite.getListPlageHoraire()) {
						date = now;
						while (date.before(disponibilite.getDateDeFin()) || date.equals(disponibilite.getDateDeFin())) {
							dayName = DateUtils.getDayName(date);
							if (isDayInRepetition(plageHoraire.getRepetition(), dayName)
									&& toDay.before(DateUtils.getDateTime(date, plageHoraire.getHeureDeFin()))) {
								dateDebutPossible = DateUtils.getDateTime(date, plageHoraire.getHeureDeDebut());
								if (toDay.before(dateDebutPossible)) {
									return DateUtils.getDuration(toDay, dateDebutPossible);
								} else {
									return 0L;
								}
							}
							date = DateUtils.addOrRemoveDays(date, 1);
						}
					}
				}
			}
		}
		return duree;
	}

	private boolean isDayInRepetition(Repetition repetition, String dayName) {
		switch (dayName) {
		case "Dimanche":
			return repetition.getEstDimanche() != null && repetition.getEstDimanche();

		case "Lundi":
			return repetition.getEstLundi() != null && repetition.getEstLundi();

		case "Mardi":
			return repetition.getEstMardi() != null && repetition.getEstMardi();

		case "Mercredi":
			return repetition.getEstMercredi() != null && repetition.getEstMercredi();

		case "Jeudi":
			return repetition.getEstJeudi() != null && repetition.getEstJeudi();

		case "Vendredi":
			return repetition.getEstVendredi() != null && repetition.getEstVendredi();

		case "Samedi":
			return repetition.getEstSamedi() != null && repetition.getEstSamedi();

		default:
			break;
		}
		return false;
	}

	public static void main(String[] args) {
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/VitOnJob/rest/common/jobyerOffer/getByLibelleJob");

		ClientResponse response = webResource.queryParam("libelleJob", "job").queryParam("idEntreprise", "1")
				.queryParam("idModeTransport", "1")
				.header("Authorization", "Basic " + StringUtils.encode64("rachid@test.com:123456"))
				.get(ClientResponse.class);
		System.out.println(response.getStatus());

		// WebResource webResource =
		// client.resource("https://maps.googleapis.com/maps/api/distancematrix/json");
		//
		// ClientResponse response = webResource
		// .queryParam("origins",
		// "IMM%20A12%20R%C3%A9s%20Soufiane%20Sidi%20ma%C3%A2rouf%20Casablanca")
		// .queryParam("destinations", "maarif").queryParam("mode",
		// "driving").get(ClientResponse.class);
		//
		// InputStream inputStream = response.getEntityInputStream();
		//
		// try {
		// BufferedReader streamReader = new BufferedReader(new
		// InputStreamReader(inputStream, "UTF-8"));
		// StringBuilder responseStrBuilder = new StringBuilder();
		//
		// String inputStr;
		// while ((inputStr = streamReader.readLine()) != null)
		// responseStrBuilder.append(inputStr);
		//
		// org.codehaus.jettison.json.JSONObject jsonObject = new
		// org.codehaus.jettison.json.JSONObject(
		// responseStrBuilder.toString());
		//
		// System.out.println(response.getStatus());
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
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

	public IAgendaDAO getAgendaDAO() {
		return agendaDAO;
	}

	@Autowired
	public void setAgendaDAO(IAgendaDAO agendaDAO) {
		this.agendaDAO.setClazz(Agenda.class);
		this.agendaDAO = agendaDAO;
	}

	public ITransportDAO getTransportDAO() {
		return transportDAO;
	}

	@Autowired
	public void setTransportDAO(ITransportDAO transportDAO) {
		this.transportDAO.setClazz(Transport.class);
		this.transportDAO = transportDAO;
	}

	public IEntrepriseAddressDAO getEntrepriseAddressDAO() {
		return entrepriseAddressDAO;
	}

	@Autowired
	public void setEntrepriseAddressDAO(IEntrepriseAddressDAO entrepriseAddressDAO) {
		this.entrepriseAddressDAO.setClazz(EntrepriseAddress.class);
		this.entrepriseAddressDAO = entrepriseAddressDAO;
	}

	public IJobyerAddressDAO getJobyerAddressDAO() {
		return jobyerAddressDAO;
	}

	@Autowired
	public void setJobyerAddressDAO(IJobyerAddressDAO jobyerAddressDAO) {
		this.jobyerAddressDAO.setClazz(JobyerAddress.class);
		this.jobyerAddressDAO = jobyerAddressDAO;
	}

	public IJobyerOfferContactDAO getJobyerOfferContactDAO() {
		return jobyerOfferContactDAO;
	}

	@Autowired
	public void setJobyerOfferContactDAO(IJobyerOfferContactDAO jobyerOfferContactDAO) {
		this.jobyerOfferContactDAO.setClazz(JobyerOfferContact.class);
		this.jobyerOfferContactDAO = jobyerOfferContactDAO;
	}

}
