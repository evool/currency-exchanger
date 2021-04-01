package service;

import java.time.LocalDate;
import java.util.Optional;

import model.Currency;
import model.CurrencyCode;

public interface Loading {
	Optional<Currency> load(CurrencyCode code, LocalDate date);
	void setProvider(Providing provider);
	void setParser(Parsing parser);
}
