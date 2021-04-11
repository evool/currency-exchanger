package service;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import service.parser.NbpJsonToCurrencyParser;
import service.parser.Parsing;
import service.provider.NbpProvider;
import service.provider.Providing;

public class Loader implements Loading {
	private Providing provider;
	private Parsing parser;
	private int attempts = 10;
	
	public Loader() {
		this.provider = new NbpProvider();
		this.parser = new NbpJsonToCurrencyParser();
	}
	
	public Loader(Providing provider) {
		this.provider = provider;
	}
	
	public Loader(Providing provider, Parsing parser) {
		this.provider = provider;
		this.parser = parser;
	}
	
	public Providing getProvider() {
		return provider;
	}
	
	public void setProvider(Providing provider) {
		this.provider = provider;
	}
	
	public Parsing getParser() {
		return parser;
	}

	public void setParser(Parsing parser) {
		this.parser = parser;
	}

	public CurrencyEntity load(CurrencyCode code, LocalDate sourceDate) {
		LocalDate date = LoadingUtils.verifyAndCorrectDate(sourceDate);
		for(int i = 0; i < attempts; i++) {
			Object data = provider.find(code, date);
			if(data != null) {
				return parser == null ? (CurrencyEntity) data : parser.parse((String) data);
			}
			date = date.minusDays(1);
		}
		return null;
	}
}