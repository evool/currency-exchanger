package strategies;

import java.time.LocalDate;

import entities.Currency;
import entities.CurrencyCode;

public class LoadFromFile implements LoadStrategy {

	@Override
	public Currency load(CurrencyCode code, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	public Currency load(CurrencyCode code) {
		return load(code, LocalDate.now());
	}
}
