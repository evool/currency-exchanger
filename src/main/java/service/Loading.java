package service;

import java.time.LocalDate;

import entity.RateEntity;
import model.CurrencyCode;
import parser.Parsing;
import provider.Providing;

public interface Loading {
	RateEntity load(CurrencyCode code, LocalDate date);
	void setProvider(Providing provider);
	void setParser(Parsing parser);
}
