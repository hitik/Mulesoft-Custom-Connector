package com.mulesoft.connector;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.mule.runtime.extension.api.annotation.param.Config;;

public class CurrencyExchangeOperations {
	private static String CURRENCY_API_URL = null;

	@DisplayName("INR to USD")
	@MediaType(value = ANY, strict = false)
	public Double inrToUsd(@Config CurrencyExchangeConfiguration config) {
		Double result = null;
		Double amount = 1000.00;
		CURRENCY_API_URL = config.getCurrencyAPIUrl() + "/latest?apikey=" + config.getCurrencyAPIKey();
		String currency = "INR";
		try {
			result = amount / getExchangeRate(currency);
		} catch (IOException e) {
			System.out.println("An error occurred while retrieving the " + currency + " value: " + e.getMessage());
		}
		return result;
	}

	private Double getExchangeRate(String currency) throws IOException {

		// Create URL object
		URL url = new URL(CURRENCY_API_URL);

		// Open connection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		// Get the response code
		int responseCode = conn.getResponseCode();
		System.out.println("Response Code: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			// Read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Parse the JSON response
			JSONObject jsonResponse = new JSONObject(response.toString());

			// Get the rates object
			JSONObject rates = jsonResponse.getJSONObject("rates");

			// Get the value corresponding to currency
			String currencyValueStr = rates.getString(currency);

			// Convert the string value to double
			Double currencyValue = Double.parseDouble(currencyValueStr);

			// Print the currency value
			System.out.println(currency + " Value: " + currencyValue);
			
			return currencyValue;
			
		} else {
			throw new IOException("Request failed. Response Code: " + responseCode);
		}
	}
}
