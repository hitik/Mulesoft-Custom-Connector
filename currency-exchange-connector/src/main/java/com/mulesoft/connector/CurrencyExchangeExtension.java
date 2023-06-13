package com.mulesoft.connector;

import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

@Xml(prefix = "currency-exchange")
@Extension(name = "Currency Exchange")
@Configurations(CurrencyExchangeConfiguration.class)
public class CurrencyExchangeExtension {

}
