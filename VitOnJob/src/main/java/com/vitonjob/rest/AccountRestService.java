package com.vitonjob.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitonjob.dao.IAccountDAO;
import com.vitonjob.dao.IEmployeurDAO;
import com.vitonjob.dao.IEntrepriseDAO;
import com.vitonjob.dao.IOfferDAO;
import com.vitonjob.dao.IPracticeJobDAO;
import com.vitonjob.dao.IPracticeLanguageDAO;
import com.vitonjob.dto.EmployeurDTO;
import com.vitonjob.dto.EntrepriseDTO;
import com.vitonjob.dto.LoginDTO;
import com.vitonjob.dto.OfferDTO;
import com.vitonjob.dto.UtilisateurDTO;
import com.vitonjob.entities.Account;
import com.vitonjob.entities.Employeur;
import com.vitonjob.entities.Entreprise;
import com.vitonjob.utils.CollectionUtils;
import com.vitonjob.utils.StringUtils;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component
@Path("/public/account")
public class AccountRestService {

	@Autowired
	private IAccountDAO accountDAO;

	@Autowired
	private IEntrepriseDAO entrepriseDAO;

	@Autowired
	private IOfferDAO offerDAO;

	@Autowired
	private IPracticeJobDAO practiceJobDAO;

	@Autowired
	private IPracticeLanguageDAO practiceLangueDAO;

	@Autowired
	private IEmployeurDAO employeurDAO;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UtilisateurDTO login(@Context HttpHeaders headers, @HeaderParam("email") String email) {
		UtilisateurDTO utilisateurDTO = null;
		try {
			if (CollectionUtils.isNotEmpty(headers.getRequestHeader("login"))) {
				JSONObject jsonLogin = (JSONObject) new JSONParser().parse(headers.getRequestHeader("login").get(0));
				LoginDTO loginDTO = new LoginDTO(jsonLogin);
				if (StringUtils.isEmpty(loginDTO.getPassword())
						|| (StringUtils.isEmpty(loginDTO.getEmail()) && StringUtils.isEmpty(loginDTO.getTelephone()))) {
					throw new IllegalArgumentException("Les données du login sont invalides.");
				}

				if ("employeur".equals(loginDTO.getRole())) {
					EmployeurDTO employeur = null;

					if (StringUtils.isNotEmpty(loginDTO.getEmail())) {
						employeur = accountDAO.findEmployeurByEmailAndPassword(loginDTO.getEmail(),
								loginDTO.getPassword());
					} else if (StringUtils.isNotEmpty(loginDTO.getTelephone())) {
						employeur = accountDAO.findEmployeurByTelephoneAndPassword(loginDTO.getTelephone(),
								loginDTO.getPassword());
					}

					if (employeur != null) {
						// Récupération de la liste des entreprises
						List<EntrepriseDTO> entreprises = entrepriseDAO
								.getEntreprisesByEmployeur(employeur.getEmployerId());

						if (CollectionUtils.isNotEmpty(entreprises)) {
							// Récupération de la liste des offres pour chaque
							// entreprise
							List<OfferDTO> offers;

							for (EntrepriseDTO entreprise : entreprises) {
								offers = offerDAO.getOffresByEntreprise(entreprise.getEntrepriseId());

								if (CollectionUtils.isNotEmpty(offers)) {
									// Récupération de la liste des practice job
									// et
									// practice language pour chaque offre
									for (OfferDTO offer : offers) {
										offer.setPricticesJob(
												practiceJobDAO.getPracticesJobByOffer(offer.getOfferId()));
										offer.setPricticesLanguage(
												practiceLangueDAO.getPracticeLanguageByOffer(offer.getOfferId()));
									}

									entreprise.setOffers(offers);
								}
							}

							employeur.setEntreprises(entreprises);
						}
					}
					// Si aucun compte n'existe pas avec l'email ou telephone,
					// on en crée un.
					else {
						Long countExistingAccounts = 0L;
						if (StringUtils.isNotEmpty(loginDTO.getEmail())) {
							countExistingAccounts = accountDAO.countUsersWithEmail(loginDTO.getEmail());
						} else if (StringUtils.isNotEmpty(loginDTO.getTelephone())) {
							countExistingAccounts = accountDAO.countUsersWithTelephone(loginDTO.getTelephone());
						}
						if (countExistingAccounts == 0) {
							Long employeurSavedId = employeurDAO.create(new Employeur());
							entrepriseDAO
									.create(new Entreprise(
											new Account(loginDTO.getTelephone(), loginDTO.getEmail(),
													loginDTO.getPassword(), "employeur"),
											employeurDAO.findOne(employeurSavedId)));
							employeur = new EmployeurDTO(employeurSavedId, loginDTO.getEmail(), loginDTO.getTelephone(),
									true);
						}
					}

					return employeur;
				} else if ("jobyer".equals(loginDTO.getRole())) {
					// TODO
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utilisateurDTO;
	}

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO.setClazz(Account.class);
		this.accountDAO = accountDAO;
	}

	public IEntrepriseDAO getEntrepriseDAO() {
		return entrepriseDAO;
	}

	public void setEntrepriseDAO(IEntrepriseDAO entrepriseDAO) {
		this.entrepriseDAO = entrepriseDAO;
	}

	public IOfferDAO getOfferDAO() {
		return offerDAO;
	}

	public void setOfferDAO(IOfferDAO offerDAO) {
		this.offerDAO = offerDAO;
	}

	public IPracticeJobDAO getPracticeJobDAO() {
		return practiceJobDAO;
	}

	public void setPracticeJobDAO(IPracticeJobDAO practiceJobDAO) {
		this.practiceJobDAO = practiceJobDAO;
	}

	public IPracticeLanguageDAO getPracticeLangueDAO() {
		return practiceLangueDAO;
	}

	public void setPracticeLangueDAO(IPracticeLanguageDAO practiceLangueDAO) {
		this.practiceLangueDAO = practiceLangueDAO;
	}

	public IEmployeurDAO getEmployeurDAO() {
		return employeurDAO;
	}

	@Autowired
	public void setEmployeurDAO(IEmployeurDAO employeurDAO) {
		this.employeurDAO.setClazz(Employeur.class);
		this.employeurDAO = employeurDAO;
	}

}
