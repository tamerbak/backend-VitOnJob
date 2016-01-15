package com.vitonjob.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
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
import com.vitonjob.dao.IJobyerDAO;
import com.vitonjob.dao.INationaliteDAO;
import com.vitonjob.dto.EmployeurDTO;
import com.vitonjob.dto.EntrepriseDTO;
import com.vitonjob.dto.JobyerDTO;
import com.vitonjob.dto.LoginDTO;
import com.vitonjob.dao.IEntrepriseDAO;
import com.vitonjob.entities.Employeur;
import com.vitonjob.entities.Entreprise;
import com.vitonjob.entities.Jobyer;
import com.vitonjob.entities.Nationalite;
import com.vitonjob.utils.CollectionUtils;
import com.vitonjob.utils.StringUtils;

/**
 * Classe implementant les services rest pour la validation de la page saisie de civilité.
 */
@Component
@Path("/public/validation")
public class ValidateCiviliteRestService {
	
	@Autowired
	private IJobyerDAO jobyerDAO;
	@Autowired
	private IEmployeurDAO employeurDAO;
	@Autowired
	private IEntrepriseDAO entrepriseDAO;
	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private INationaliteDAO nationaliteDAO;
	
	@POST
	@Path("/civilite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void setCivilite(@Context HttpHeaders headers) {
		try {
			//IAccountDAO accountDAO = new AccountDAOImpl();
			
			//Verify session informations
			if (CollectionUtils.isNotEmpty(headers.getRequestHeader("login"))) {
				JSONObject jsonLogin = (JSONObject) new JSONParser().parse(headers.getRequestHeader("login").get(0));
				LoginDTO loginDTO = new LoginDTO(jsonLogin);
				if (StringUtils.isEmpty(loginDTO.getPassword())
						|| (StringUtils.isEmpty(loginDTO.getEmail()) && StringUtils.isEmpty(loginDTO.getTelephone()))) {
					throw new IllegalArgumentException("Les données du login sont invalides.");
				}				
				//Login was successful, now verify data that will be validated.
				if (CollectionUtils.isNotEmpty(headers.getRequestHeader("data"))) {
					JSONObject jsonData = (JSONObject) new JSONParser().parse(headers.getRequestHeader("data").get(0));
					
					//If employeur exist : 
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
							//Update Employer informations
							Employeur empEntity = employeurDAO.findOne(employeur.getEmployerId());
							empEntity.setNom((String) jsonData.get("nom"));
							empEntity.setTitre((String) jsonData.get("titre"));
							empEntity.setPrenom((String) jsonData.get("prenom"));
							employeurDAO.update(empEntity);
							
							//Update entreprise informations
							List<EntrepriseDTO> entreprises = employeur.getEntreprises();
							if (CollectionUtils.isNotEmpty(entreprises)) {
								Entreprise entreprise = entrepriseDAO.findOne(entreprises.get(0).getEntrepriseId());
								entreprise.setNomOuRaisonSociale((String) jsonData.get("raisonSocial"));
								entreprise.setSiret((String) jsonData.get("siret"));
								entreprise.setCodeApeOuNaf((String) jsonData.get("codeNaf"));
								entreprise.setUrssaf((String) jsonData.get("urssaf"));
								entrepriseDAO.update(entreprise);
							}
						}
						//If jobyer then (from login informations) 
					} else if ("jobyer".equals(loginDTO.getRole())){
						
						JobyerDTO jobyerDTO = null;
						if (StringUtils.isNotEmpty(loginDTO.getEmail())) {
							jobyerDTO = accountDAO.findJobyerByEmailAndPassword(loginDTO.getEmail(),
									loginDTO.getPassword());
						} else if (StringUtils.isNotEmpty(loginDTO.getTelephone())) {
							jobyerDTO = accountDAO.findJobyerByTelephoneAndPassword(loginDTO.getTelephone(),
									loginDTO.getPassword());
						}
						
						//Update Employer informations
						if (jobyerDTO != null) {
							Jobyer jobyer = jobyerDAO.findOne(jobyerDTO.getJobyerId());
							jobyer.setNom((String) jsonData.get("nom")); 
							jobyer.setTitre((String) jsonData.get("titre"));
							jobyer.setPrenom((String) jsonData.get("prenom"));
							jobyer.setCin((String) jsonData.get("cin"));
							//jobyer.setnss
							Nationalite nationalite = nationaliteDAO.findOne((long) jsonData.get("nationalite"));
							jobyer.setNationalite(nationalite);
							jobyerDAO.update(jobyer);
						}
						
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
