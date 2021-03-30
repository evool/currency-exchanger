package service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.CurrencyException;
import model.Currency;
import model.CurrencyCode;

public class NbpJsonParser implements Parsing {

	@Override
	public Currency parse(String jsonAsString) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(jsonAsString);
		} catch (JsonMappingException e) {
			throw new CurrencyException(e);
		} catch (JsonProcessingException e) {
			throw new CurrencyException(e);
		}
		// Mozna by zaimplementowac builder czy cos
		String name = jsonNode.path("currency").asText();
		CurrencyCode code = CurrencyCode.valueOf(jsonNode.path("code").asText());
		jsonNode = jsonNode.path("rates").path(0);
		BigDecimal rate = new BigDecimal(jsonNode.path("mid").asText());
		LocalDate effectiveDate = LocalDate.parse(jsonNode.path("effectiveDate").asText());
		return new Currency(name, code, rate, effectiveDate);
	}
}
