package strategies;

import java.time.LocalDate;

import entities.Currency;
import entities.CurrencyCode;

public interface LoadStrategy {
	Currency load(CurrencyCode code, LocalDate date);
	Currency load(CurrencyCode code);
}