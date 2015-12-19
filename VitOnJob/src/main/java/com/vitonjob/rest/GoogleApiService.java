package com.vitonjob.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Classe implementant les services rest pour les objets Rue.
 */
@Component("googleApiService")
public class GoogleApiService {

	public Double getDurationBetweenTwoAddresses(String origin, String destination, String modeTransport) {
		Double duration = null;
		Client client = Client.create();

		WebResource webResource = client.resource("https://maps.googleapis.com/maps/api/distancematrix/json");

		ClientResponse response = webResource.queryParam("origins", origin).queryParam("destinations", destination)
				.queryParam("mode", modeTransport).get(ClientResponse.class);

		InputStream inputStream = response.getEntityInputStream();

		try {
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();

			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);

			JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

			if (jsonObject != null) {
				JSONArray jsonArray = jsonObject.getJSONArray("rows");
				if (jsonArray != null && jsonArray.length() > 0) {
					jsonArray = ((JSONObject) jsonArray.get(0)).getJSONArray("elements");
					if (jsonArray != null && jsonArray.length() > 0) {
						jsonObject = ((JSONObject) jsonArray.get(0)).getJSONObject("duration");
						if (jsonObject != null) {
							return jsonObject.getDouble("value");
						}
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return duration;
	}

}
