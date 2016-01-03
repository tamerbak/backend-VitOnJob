package com.vitonjob.rest;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.vitonjob.dao.IAgendaDAO;
import com.vitonjob.dao.IDistanceDAO;
import com.vitonjob.dao.IEntrepriseAddressDAO;
import com.vitonjob.dao.IEntrepriseDAO;
import com.vitonjob.dao.IIndispensableDAO;
import com.vitonjob.dao.IJobyerAddressDAO;
import com.vitonjob.dao.IJobyerOfferContactDAO;
import com.vitonjob.dao.IJobyerOfferDAO;
import com.vitonjob.dao.ILevelDAO;
import com.vitonjob.dao.ITimePerTransportDAO;
import com.vitonjob.dao.ITransportDAO;
import com.vitonjob.dto.AvailabilityDTO;
import com.vitonjob.dto.CoefficientsDTO;
import com.vitonjob.dto.JobyerOfferDTO;
import com.vitonjob.dto.RequiredDTO;
import com.vitonjob.entities.Agenda;
import com.vitonjob.entities.Disponibilite;
import com.vitonjob.entities.Distance;
import com.vitonjob.entities.Entreprise;
import com.vitonjob.entities.EntrepriseAddress;
import com.vitonjob.entities.Indispensable;
import com.vitonjob.entities.JobyerAddress;
import com.vitonjob.entities.JobyerOffer;
import com.vitonjob.entities.JobyerOfferContact;
import com.vitonjob.entities.Level;
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
	private IDistanceDAO distanceDAO;

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

	@Autowired
	private ILevelDAO levelDAO;

	@Autowired
	private IIndispensableDAO indispensableDAO;

	@GET
	@Path("/getByLibelleJobAndAvailability")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JobyerOfferDTO> getJobyersOffersByLibelleJobAndAvailability(@QueryParam("libelleJob") String libelleJob,
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
			for (JobyerOfferDTO jobyerOffer : jobyersOffers) {
				setAvailabilityMark(jobyerOffer, entrepriseAddress, modeTransport, idEntreprise);
			}

			Collections.sort(jobyersOffers);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobyersOffers;
	}

	private void setAvailabilityMark(JobyerOfferDTO jobyerOffer, EntrepriseAddress entrepriseAddress,
			Transport modeTransport, String idEntreprise) {
		Double timePerTransport = timePerTransportDAO.getTimePerTansportBetweenTwoAddresses(
				jobyerOffer.getJobyerAddressId(), entrepriseAddress.getId(), Long.valueOf(modeTransport.getId()));

		boolean skipAgenda = false;

		// si pas de time per transport dans la base, on appelle le web
		// service
		// Google
		// https://maps.googleapis.com/maps/api/distancematrix/json?origins=latitude,longitude&destinations=latitude,longitude&mode=driving
		if (timePerTransport == null) {
			Map<String, Double> durantionAndDistanceMap = googleApiService.getDurationAndDistanceBetweenTwoAddresses(
					jobyerOffer.getLatitude() + "," + jobyerOffer.getLongitude(),
					entrepriseAddress.getAdresse().getLatitude() + "," + entrepriseAddress.getAdresse().getLongitude(),
					modeTransport.getLibelle());
			timePerTransport = durantionAndDistanceMap.get(GoogleApiService.DURATION_KEY);

			// On enregistre le time per transport dans la base de
			// données
			JobyerAddress jobyerAddress = null;
			if (timePerTransport != null) {
				TimePerTransport timePerTransportToSave = new TimePerTransport();
				timePerTransportToSave.setValeur(timePerTransport);
				timePerTransportToSave.setEntrepriseAddress(entrepriseAddress);
				jobyerAddress = jobyerAddressDAO.findOne(jobyerOffer.getJobyerAddressId());
				timePerTransportToSave.setJobyerAddress(jobyerAddress);
				timePerTransportToSave.setTransport(modeTransport);
				timePerTransportDAO.create(timePerTransportToSave);
			} else {
				jobyerOffer.setAvailability(null);
				skipAgenda = true;
			}

			Double distance = durantionAndDistanceMap.get(GoogleApiService.DISTANCE_KEY);

			// On enregistre la distance dans la base de
			// données
			if (distance != null) {
				Distance distanceToSave = new Distance();
				distanceToSave.setValeur(distance);
				distanceToSave.setEntrepriseAddress(entrepriseAddress);
				if (jobyerAddress == null) {
					jobyerAddress = jobyerAddressDAO.findOne(jobyerOffer.getJobyerAddressId());
				}
				distanceToSave.setJobyerAddress(jobyerAddress);
				distanceDAO.create(distanceToSave);
			}
		}

		// Calcul de la disponibilité du jobyer : la diffrence entre la
		// datetime de début de la plus proche disponibilité (agenda du
		// jobyer) du jobyer et la datetime courante (now)
		if (!skipAgenda) {
			Agenda agenda = agendaDAO.getAgendaByJobyer(jobyerOffer.getJobyerId());

			Long dureeAvantDisponibilite = getDureeAvantDisponibilite(agenda);

			if (dureeAvantDisponibilite != null) {
				jobyerOffer.setAvailability(new AvailabilityDTO(timePerTransport.intValue() + dureeAvantDisponibilite));
			} else {
				jobyerOffer.setAvailability(null);
			}
		}

		// Set la valeur de on : est ce que l'employeur a déjà consulté
		// le jobyer offer
		jobyerOffer.setOn(jobyerOfferContactDAO.countContactByEntreprise(jobyerOffer.getJobyerOfferId(),
				Long.valueOf(idEntreprise)) > 0);
	}

	private Long getDureeAvantDisponibilite(Agenda agenda) {
		Long duree = null;
		Date now = new Date();
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
									&& now.before(DateUtils.getDateTime(date, plageHoraire.getHeureDeFin()))) {
								dateDebutPossible = DateUtils.getDateTime(date, plageHoraire.getHeureDeDebut());
								if (now.before(dateDebutPossible)) {
									return DateUtils.getDuration(now, dateDebutPossible);
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

	@GET
	@Path("/getByLibelleJobAndMatching")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JobyerOfferDTO> getJobyersOffersByLibelleJobAndMatching(@QueryParam("libelleJob") String libelleJob,
			@QueryParam("requiredLang") List<RequiredDTO> requiredLanguages,
			@QueryParam("requiredJob") List<RequiredDTO> requiredJobs,
			@QueryParam("requiredQI") List<Long> requiredQualitesIndispensables,
			@QueryParam("coefficients") CoefficientsDTO coefficients) {

		List<JobyerOfferDTO> jobyersOffers = null;
		try {
			if (StringUtils.isEmpty(libelleJob) || coefficients == null
					|| (CollectionUtils.isEmpty(requiredJobs) && CollectionUtils.isEmpty(requiredLanguages)
							&& CollectionUtils.isEmpty(requiredQualitesIndispensables))) {
				return null;
			}

			// Récupération de 200 jobyer offers correspondant au libellé job.
			jobyersOffers = jobyerOfferDAO.getListJobyerOfferByLibelleJob(libelleJob, 200);

			// Calcul du matching pour chaque jobyer
			for (JobyerOfferDTO jobyerOffer : jobyersOffers) {
				setMatchingMark(jobyerOffer, requiredJobs, requiredLanguages, requiredQualitesIndispensables,
						coefficients);
			}

			Collections.sort(jobyersOffers, new Comparator<JobyerOfferDTO>() {

				@Override
				public int compare(JobyerOfferDTO jOffer1, JobyerOfferDTO jOffer2) {
					if (jOffer1.getMatching() == jOffer2.getMatching()) {
						return 0;
					}
					if (jOffer1.getMatching() == null) {
						return 1;
					}
					if (jOffer2.getMatching() == null) {
						return -1;
					}
					return jOffer2.getMatching().compareTo(jOffer1.getMatching());
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobyersOffers;
	}

	private void setMatchingMark(JobyerOfferDTO jobyerOffer, List<RequiredDTO> requiredJobs,
			List<RequiredDTO> requiredLanguages, List<Long> requiredQualitesIndispensables,
			CoefficientsDTO coefficients) {
		Level level;
		Level requiredLevel;
		Double languagesMark = 0D;
		Double jobsMark = 0D;
		Double qualitiesMark = 0D;

		// Required jobs
		if (CollectionUtils.isNotEmpty(requiredJobs)) {
			for (RequiredDTO requiredJob : requiredJobs) {
				level = levelDAO.getLevelByJobAndJobyer(requiredJob.getRequiredId(), jobyerOffer.getJobyerId());
				requiredLevel = levelDAO.findOne(requiredJob.getRequiredLevelId());
				if (level != null) {
					jobsMark += getJobyerMark(requiredLevel, level);
				}
			}
			jobsMark = jobsMark / requiredJobs.size();
		}

		// Required languages
		if (CollectionUtils.isNotEmpty(requiredLanguages)) {
			for (RequiredDTO requiredLanguage : requiredLanguages) {
				level = levelDAO.getLevelByLanguageAndJobyer(requiredLanguage.getRequiredId(),
						jobyerOffer.getJobyerId());
				requiredLevel = levelDAO.findOne(requiredLanguage.getRequiredLevelId());
				if (level != null) {
					languagesMark += getJobyerMark(requiredLevel, level);
				}
			}
			languagesMark = languagesMark / requiredLanguages.size();
		}

		// Required Qualities
		if (CollectionUtils.isNotEmpty(requiredQualitesIndispensables)) {
			qualitiesMark = (double) 100 * indispensableDAO.countIndispensablesByJobyer(jobyerOffer.getJobyerId(),
					requiredQualitesIndispensables) / requiredQualitesIndispensables.size();
		}

		jobyerOffer.setMatching(
				((jobsMark * coefficients.getCoefficientJob()) + (languagesMark * coefficients.getCoefficientLang())
						+ (qualitiesMark * coefficients.getCoefficientQI())) / 100);
	}

	private static Double getJobyerMark(Level requiredLevel, Level level) {
		if (requiredLevel.getId() == requiredLevel.getId()) {
			return 100D;
		} else if (requiredLevel.getId() == 1) {
			return level.getId() == 2 ? 133d : level.getId() == 3 ? 166d : 200d;
		} else if (requiredLevel.getId() == 2) {
			return level.getId() == 1 ? 50d : level.getId() == 3 ? 150d : 200d;
		} else if (requiredLevel.getId() == 3) {
			return level.getId() == 1 ? 33d : level.getId() == 2 ? 66d : 200d;
		}
		return 0d;
	}

	@GET
	@Path("/getByLibelleJobAndAvailabilityAndMatching")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<JobyerOfferDTO> getJobyersOffersByLibelleJobAndAvailabilityAndMatching(
			@QueryParam("libelleJob") String libelleJob, @QueryParam("idEntreprise") String idEntreprise,
			@QueryParam("idModeTransport") String idModeTransport,
			@QueryParam("requiredLang") List<RequiredDTO> requiredLanguages,
			@QueryParam("requiredJob") List<RequiredDTO> requiredJobs,
			@QueryParam("requiredQI") List<Long> requiredQualitesIndispensables,
			@QueryParam("coefficients") CoefficientsDTO coefficients) {

		List<JobyerOfferDTO> jobyersOffers = null;
		try {
			if (StringUtils.isEmpty(libelleJob) || StringUtils.isEmpty(idEntreprise)
					|| StringUtils.isEmpty(idModeTransport) || coefficients == null
					|| (CollectionUtils.isEmpty(requiredJobs) && CollectionUtils.isEmpty(requiredLanguages)
							&& CollectionUtils.isEmpty(requiredQualitesIndispensables))) {
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

			// Calcul du matching pour chaque jobyer
			for (JobyerOfferDTO jobyerOffer : jobyersOffers) {
				setAvailabilityMark(jobyerOffer, entrepriseAddress, modeTransport, idEntreprise);
				setMatchingMark(jobyerOffer, requiredJobs, requiredLanguages, requiredQualitesIndispensables,
						coefficients);
			}

			Collections.sort(jobyersOffers, new Comparator<JobyerOfferDTO>() {

				@Override
				public int compare(JobyerOfferDTO jOffer1, JobyerOfferDTO jOffer2) {
					if (jOffer1.getAvailability() == jOffer2.getAvailability()) {
						if (jOffer1.getMatching() == jOffer2.getMatching()) {
							return 0;
						}
						if (jOffer1.getMatching() == null) {
							return 1;
						}
						if (jOffer2.getMatching() == null) {
							return -1;
						}
						return jOffer2.getMatching().compareTo(jOffer1.getMatching());
					}
					if (jOffer1.getAvailability() == null) {
						return -1;
					}
					if (jOffer2.getAvailability() == null) {
						return 1;
					}
					return jOffer1.getAvailability().getValue().compareTo(jOffer2.getAvailability().getValue());
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobyersOffers;
	}

	public static void main(String[] args) throws UniformInterfaceException, ClientHandlerException, JSONException {
		// Level requiredLevel = new Level();
		// requiredLevel.setId(3L);
		//
		// Level level = new Level();
		// level.setId(4L);
		//
		// Double d = getJobyerMark(requiredLevel, level);
		// System.out.println(d);
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/VitOnJob/rest/common/jobyerOffer/getByLibelleJobMatching");

		String requiredLanguages = "{\"requiredId\":\"1\",\"requiredLevelId\":\"1\",\"requiredPerCent\":\"100\"}";

		ClientResponse response = webResource.queryParam("requiredLanguages", requiredLanguages)
				.header("Authorization", "Basic " + StringUtils.encode64("rachid@test.com:123456"))
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		System.out.println(response.getStatus());

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

	public ILevelDAO getLevelDAO() {
		return levelDAO;
	}

	@Autowired
	public void setLevelDAO(ILevelDAO levelDAO) {
		this.levelDAO.setClazz(Level.class);
		this.levelDAO = levelDAO;
	}

	public IIndispensableDAO getIndispensableDAO() {
		return indispensableDAO;
	}

	@Autowired
	public void setIndispensableDAO(IIndispensableDAO indispensableDAO) {
		this.indispensableDAO.setClazz(Indispensable.class);
		this.indispensableDAO = indispensableDAO;
	}

	public IDistanceDAO getDistanceDAO() {
		return distanceDAO;
	}

	@Autowired
	public void setDistanceDAO(IDistanceDAO distanceDAO) {
		this.distanceDAO = distanceDAO;
		this.distanceDAO.setClazz(Distance.class);
	}

}
