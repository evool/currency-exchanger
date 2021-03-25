import java.math.BigDecimal;
import java.time.LocalDate;

import currency.Currency;
import currency.CurrencyCode;
import strategies.load.LoadFromNbp;
import strategies.load.Loading;
import strategies.load.LoadingUtils;
import strategies.parse.NbpJsonParser;
import strategies.parse.Parsing;

public class Exchange {
	private Loading provider;
	private Parsing parser;
	private int attempts = 5;
	
	public Exchange() {
		provider = new LoadFromNbp();
		parser = new NbpJsonParser();
	}
	
	public Exchange(Loading provider, Parsing parser, int attempts) {
		this.provider = provider;
		this.parser = parser;
		this.attempts = attempts;
	}
	
	BigDecimal toPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		String data = LoadingUtils.tryToLoadData(provider, code, date, attempts);
		Currency currency = parser.parse(data);
		return currency.exchangeToPLN(amount);
	}
	
	BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		String data = LoadingUtils.tryToLoadData(provider, code, date, attempts);
		Currency currency = parser.parse(data);
		return currency.exchangeFromPLN(amount);
	}
	
	BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
		return fromPLN(amount, code, LocalDate.now());
	}
}