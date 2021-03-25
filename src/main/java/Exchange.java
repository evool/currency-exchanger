import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

import currency.Currency;
import currency.CurrencyCode;
import strategies.load.Loading;
import strategies.load.LoadingUtils;
import strategies.load.NbpLoader;
import strategies.parse.NbpJsonParser;
import strategies.parse.Parsing;

public class Exchange {
	private Loading provider;
	private Parsing parser;
	private static HashSet<Currency> cache = new HashSet<>();
	
	public Exchange() {
		provider = new NbpLoader();
		parser = new NbpJsonParser();
	}
	
	public Exchange(Loading provider, Parsing parser, int attempts) {
		this.provider = provider;
		this.parser = parser;
	}
	
	BigDecimal toPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Currency currency = verifyAndLoadData(code, date);
		return currency.exchangeToPLN(amount);
	}
	
	BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Currency currency = verifyAndLoadData(code, date);
		return currency.exchangeFromPLN(amount);
	}
	
	BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
		return fromPLN(amount, code, LocalDate.now());
	}

	private Currency checkCache(CurrencyCode code, LocalDate date) {
		Iterator<Currency> i = cache.iterator();
		Currency temp;
		while(i.hasNext()) {
			temp = i.next();
			if(temp.getCode() == code && temp.getEffectiveDate().equals(date)) {
				System.out.println("Znaleziono");
				return temp;
			}
		}
		System.out.println("Nie znaleziono");
		return null;
	}
	
	private Currency verifyAndLoadData(CurrencyCode code, LocalDate date) {
		LoadingUtils.checkIsDateAfterToday(date);
		date = LoadingUtils.verifyItIsNotWeekend(date);
		Currency currency = checkCache(code, date);
		if(currency == null) {
			String data = provider.load(code, date);
			currency = parser.parse(data);
			cache.add(currency);
		}
		return currency;
	}

	
}