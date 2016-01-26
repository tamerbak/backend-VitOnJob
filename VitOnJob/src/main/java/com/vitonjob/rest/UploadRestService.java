package com.vitonjob.rest;

import javax.xml.bind.DatatypeConverter;

import java.io.File;
import java.io.FileOutputStream;

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
import com.vitonjob.dto.EmployeurDTO;
import com.vitonjob.dto.JobyerDTO;
import com.vitonjob.dto.LoginDTO;
import com.vitonjob.utils.CollectionUtils;
import com.vitonjob.utils.StringUtils;

/**
 * Classe implementant les services rest pour le rechargement des fichiers vers le serveur.
 */
@Component
@Path("/public/upload")
public class UploadRestService {
	
	@Autowired
	private IAccountDAO accountDAO;
	
	@POST
	@Path("/file")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void uploadFile(@Context HttpHeaders headers, String stData) {
		try {
			String fileName = null;
			String idUser = null;
			byte[] data = null;
			
			//Verify session informations
			if (CollectionUtils.isNotEmpty(headers.getRequestHeader("login"))) {
				JSONObject jsonLogin = (JSONObject) new JSONParser().parse(headers.getRequestHeader("login").get(0));
				LoginDTO loginDTO = new LoginDTO(jsonLogin);
				if (StringUtils.isEmpty(loginDTO.getPassword())
						|| (StringUtils.isEmpty(loginDTO.getEmail()) && StringUtils.isEmpty(loginDTO.getTelephone()))) {
					throw new IllegalArgumentException("Les données du login sont invalides.");
				}				
				
					//Get user id (employer/jobyer) if exist : 
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
							idUser = Long.toString(employeur.getEmployerId());
						}
					} else if ("jobyer".equals(loginDTO.getRole())) {
						JobyerDTO jobyer = null;
						if (StringUtils.isNotEmpty(loginDTO.getEmail())) {
							jobyer = accountDAO.findJobyerByEmailAndPassword(loginDTO.getEmail(),
									loginDTO.getPassword());
						} else if (StringUtils.isNotEmpty(loginDTO.getTelephone())) {
							jobyer = accountDAO.findJobyerByTelephoneAndPassword(loginDTO.getTelephone(),
									loginDTO.getPassword());
						}
	
						if (jobyer != null) {
							idUser = Long.toString(jobyer.getJobyerId());
						}
					}
					
					//Login was successful, now verify data that will be validated.
					if (CollectionUtils.isNotEmpty(headers.getRequestHeader("data"))) {
						JSONObject jsonData = (JSONObject) new JSONParser().parse(headers.getRequestHeader("data").get(0));
						
						//Get file name from header:
						fileName = (String) jsonData.get("fileName") + "@" + idUser ;
					
						//Get file content from header:
						String fileContent = (String) jsonData.get("fileContent");
						data = DatatypeConverter.parseBase64Binary(stData);
					}
					
					//Saving File to ./uploadedFiles
					FileOutputStream fos = null;
					File dir = new File("./uploadedFiles");
					if (dir.exists()) {
						fos = new FileOutputStream("./uploadedFiles/" + fileName);
						//fos = new FileOutputStream("/Users/tim/Documents/work/manaona/vitonjob/dev/github/backend-VitOnJob/VitOnJob/" + fileName);
					} else {
						dir.mkdir();
						fos = new FileOutputStream("./uploadedFiles/" + fileName);
					}
					try {
						fos.write(data);
					} catch (Exception e1){
						e1.printStackTrace();
					} finally {
						fos.close();
					}	
					
				} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
