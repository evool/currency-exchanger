package service;

import java.time.LocalDate;
import java.util.Optional;

import model.Currency;
import model.CurrencyCode;

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

	public Optional<Currency> load(CurrencyCode code, LocalDate sourceDate) {
		LocalDate date = LoadingUtils.verifyAndCorrectDate(sourceDate);
		for(int i = 0; i < attempts; i++) {
			Object data = provider.find(code, date);
			if(data != null) {
				return parser == null ? Optional.ofNullable((Currency) data) : Optional.of(parser.parse(data));
			}
			date = date.minusDays(1);
		}
		return Optional.empty();
//		throw new CurrencyException("Cannot find currency " + code + " from " + sourceDate);
	}
}
