package service;

import java.time.LocalDate;

import currency.CurrencyCode;

public interface Loading {
	String load(CurrencyCode code, LocalDate date);
}