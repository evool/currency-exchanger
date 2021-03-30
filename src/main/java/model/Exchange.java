package model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import service.Loading;
import service.LoadingUtils;
import service.NbpJsonParser;
import service.NbpLoader;
import service.Parsing;

public class Exchange {
	private static Cache<Currency> cache = new Cache<>();
	private Loading provider;
	private Parsing parser;
	
	public Exchange() {
		provider = new NbpLoader();
		parser = new NbpJsonParser();
	}
	
	public Exchange(Loading provider, Parsing parser) {
		this.provider = provider;
		this.parser = parser;
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Currency currency = verifyAndLoadData(code, date);
		return amount.multiply(currency.getRate());
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Currency currency = verifyAndLoadData(code, date);
		return amount.divide(currency.getRate(), 4, RoundingMode.HALF_UP);
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
		return fromPLN(amount, code, LocalDate.now());
	}
	
	private Currency verifyAndLoadData(CurrencyCode code, LocalDate date) {
		LoadingUtils.checkIsDateAfterToday(date);
		date = LoadingUtils.verifyAndSkipIfItIsWeekend(date);
		Currency template = new Currency(null, code, null, date);
		Optional<Currency> currency = cache.getIfFound(template);
		if(currency.isEmpty()) {
			String data = provider.load(code, date);
			currency = Optional.of(parser.parse(data));
			cache.add(currency.get());
		}
		return currency.get();
	}
}