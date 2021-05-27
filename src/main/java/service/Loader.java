package service;

import java.time.LocalDate;

import entity.RateEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.CurrencyCode;
import parser.Parsing;
import provider.Providing;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Loader implements Loading {
	
	private Providing provider;
	private Parsing parser;
	
	private final int attempts = 10;

	public RateEntity load(CurrencyCode code, LocalDate sourceDate) {
		LocalDate date = LoadingUtils.verifyAndCorrectDate(sourceDate);
		for(int i = 0; i < attempts; i++) {
			Object data = provider.find(code, date);
			if(data != null) {
				if(parser == null) {
					return (RateEntity) data;
				}
				return (RateEntity) parser.parse((Object)data);
			}
			date = date.minusDays(1);
		}
		return null;
	}
}