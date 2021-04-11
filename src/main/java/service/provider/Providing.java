package service.provider;

import java.time.LocalDate;

import model.CurrencyCode;

public interface Providing <T> {
	T find(CurrencyCode code, LocalDate date);
}