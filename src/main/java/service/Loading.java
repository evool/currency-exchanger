package service;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import parser.Parsing;
import provider.Providing;

public interface Loading {
	CurrencyEntity load(CurrencyCode code, LocalDate date);
	void setProvider(Providing provider);
	void setParser(Parsing parser);
}
