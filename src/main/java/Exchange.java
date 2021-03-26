import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import currency.Currency;
import currency.CurrencyCode;
import service.Loading;
import service.LoadingUtils;
import service.NbpJsonParser;
import service.NbpLoader;
import service.Parsing;

public class Exchange {
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
	
	BigDecimal toPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Currency currency = verifyAndLoadData(code, date);
		return amount.multiply(currency.getRate());
	}
	
	BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Currency currency = verifyAndLoadData(code, date);
		return amount.divide(currency.getRate(), 4, RoundingMode.HALF_UP);
	}
	
	BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
		return fromPLN(amount, code, LocalDate.now());
	}
	
	private Currency verifyAndLoadData(CurrencyCode code, LocalDate date) {
		LoadingUtils.checkIsDateAfterToday(date);
		date = LoadingUtils.verifyAndSkipIfItIsWeekend(date);
		Optional<Currency> currency = CurrencyCache.checkAndGetIfFound(code, date);
		if(currency.isEmpty()) {
			String data = provider.load(code, date);
			currency = Optional.of(parser.parse(data));
			CurrencyCache.add(currency.get());
		}
		return currency.get();
	}
}