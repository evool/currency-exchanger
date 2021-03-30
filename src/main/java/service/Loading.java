package service;

import java.time.LocalDate;

import model.CurrencyCode;

public interface Loading {
	String load(CurrencyCode code, LocalDate date);
}