package service;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import service.parser.Parsing;
import service.provider.Providing;

public interface Loading {
	CurrencyEntity load(CurrencyCode code, LocalDate date);
	void setProvider(Providing provider);
	void setParser(Parsing parser);
}
