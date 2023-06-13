package com.mulesoft.connector;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

@Operations(CurrencyExchangeOperations.class)
public class CurrencyExchangeConfiguration {
	@Parameter
	@DisplayName("Currency API URL")
	@Placement(order=1)
	@Example("https://api.currencyfreaks.com")
	private String CurrencyAPIUrl;
	@Parameter
	@DisplayName("Currency API Key")
	@Placement(order=2)
	@Example("b94ccb9fd73e49daa1dad10a1e29d788")
	private String CurrencyAPIKey;
	public String getCurrencyAPIUrl() {
		return CurrencyAPIUrl;
	}
	public String getCurrencyAPIKey() {
		return CurrencyAPIKey;
	}

}
