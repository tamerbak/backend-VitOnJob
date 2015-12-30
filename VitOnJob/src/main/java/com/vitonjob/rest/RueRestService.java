package com.vitonjob.rest;

import java.util.Base64;
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
import com.vitonjob.dao.IRueDAO;
import com.vitonjob.dto.RueDTO;
import com.vitonjob.entities.Rue;
import com.vitonjob.utils.StringUtils;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component
@Path("/common/rue")
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

	public static void main(String[] args) {
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/VitOnJob/rest/public/account/login");

		// byte[] bytesEncoded =
		// Base64.getEncoder().encode("rachid@rachid.com:rachid".getBytes());
		// System.out.println("ecncoded value is " + new String(bytesEncoded));

		// ClientResponse responseMsg = webResource.path("/common/rue/getAll")
		// .header("Authorization", "Basic " + new
		// String(bytesEncoded)).get(ClientResponse.class);

		// ClientResponse responseMsg =
		// webResource.path("/public/account/login").post(ClientResponse.class);
		// String login = "{\"email\":\"" +
		// StringUtils.encode64("rachid3@test.com") + "\",\"telephone\":\""
		// + StringUtils.encode64("0636985471") + "\",\"password\":\"" +
		// StringUtils.encode64("12345678")
		// + "\",\"role\":\"" + StringUtils.encode64("employeur") + "\"}";
		String login = "{\"email\":\"" + StringUtils.encode64("rachid@test.com") + "\",\"password\":\""
				+ StringUtils.encode64("123456") + "\",\"role\":\"" + StringUtils.encode64("employeur") + "\"}";

		ClientResponse response = webResource.header("login", login).type("application/json").post(ClientResponse.class,
				null);

		System.out.println(response.getStatus());
	}

}
