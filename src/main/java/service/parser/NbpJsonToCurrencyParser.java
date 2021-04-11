package service.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import entity.CurrencyEntity;
import exceptions.CurrencyException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpJsonToCurrencyParser implements Parsing {

	@Override
	public CurrencyEntity parse(String jsonAsString) {
		if (jsonAsString instanceof String == false) {
			throw new CurrencyException("NbpJsonParser needs String, but got " + jsonAsString.getClass());
		}
		ObjectMapper mapper = JsonMapper.builder()
		        .findAndAddModules()
		        .build();
		try {
			return mapper.readValue((String)jsonAsString, CurrencyEntity.class);
		} catch (JsonMappingException e) {
			throw new CurrencyException(e);
		} catch (JsonProcessingException e) {
			throw new CurrencyException(e);
		}
//		String name = jsonNode.path("currency").asText();
//		CurrencyCode code = CurrencyCode.valueOf(jsonNode.path("code").asText());
//		jsonNode = jsonNode.path("rates").path(0);
//		BigDecimal rate = new BigDecimal(jsonNode.path("mid").asText());
//		LocalDate effectiveDate = LocalDate.parse(jsonNode.path("effectiveDate").asText());
//		return new Currency(name, code, rate, effectiveDate);
	}
}