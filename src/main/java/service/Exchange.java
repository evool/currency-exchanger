package service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import model.Currency;
import model.CurrencyCode;

public class Exchange {
	private Loading loader;
	
	public Exchange() {
		loader = new Loader(new NbpProvider(), new NbpJsonToCurrencyParser());
	}
	
	public Exchange(Loader loader) {
		this.loader = loader;
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Optional<Currency> currency = loader.load(code, date);
		return amount.multiply(currency.get().getRate());
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		Optional<Currency> currency = loader.load(code, date);
		return amount.divide(currency.get().getRate(), 4, RoundingMode.HALF_UP);
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
		return fromPLN(amount, code, LocalDate.now());
	}
}