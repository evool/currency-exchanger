package service.provider;

import java.time.LocalDate;

import model.CurrencyCode;

public interface Providing {
	Object find(CurrencyCode code, LocalDate date);
}