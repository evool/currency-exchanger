package parser;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import entity.CurrencyEntity;
import entity.RateEntity;
import exceptions.CurrencyException;
import model.CurrencyCode;

public class NbpJsonToCurrencyParser implements Parsing<String, RateEntity> {

	@Override
	public RateEntity parse(String jsonAsString) {
		ObjectMapper mapper = JsonMapper.builder()
		        .findAndAddModules()
		        .build();
		try {
			JsonNode json = mapper.readTree(jsonAsString);
			String codeAsText = json.get("code").asText();
			CurrencyCode code = CurrencyCode.valueOf(codeAsText);
			json = json.get("rates");
			Set<RateEntity> rates = mapper.convertValue(json, new TypeReference<Set<RateEntity>>(){});
			
			CurrencyEntity currency;
			if(isCurrency)
			
			
			return mapper.readValue(jsonAsString, CurrencyEntity.class);
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