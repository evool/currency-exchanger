package strategies;

import java.io.FileNotFoundException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Currency;
import entities.CurrencyCode;
import exceptions.ConnectionException;
import exceptions.CurrencyException;
import exceptions.FutureDateException;
import exceptions.InvalidDateException;

public class LoadFromNBP implements LoadStrategy {

	final String DATA_PATH = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json";
	final int ATTEMPTS = 10;

	@Override
	public Currency load(CurrencyCode code, LocalDate date) {
		checkDate(date);
		Currency currency;
		for (int i = ATTEMPTS; i > 0; i--) {
			currency = getCurrencyFromApi(code, date.toString());
			if (currency != null) {
				return currency;
			}
			date = date.minusDays(1);
		}
		throw new InvalidDateException(
				"Cannot find valid rate " + code + " for " + date.toString() + " in " + DATA_PATH);
	}

	@Override
	public Currency load(CurrencyCode code) {
		return load(code, LocalDate.now());
	}

	private void checkDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			throw new FutureDateException(
					String.format("The requested rate is from %s, today is %s.", date.toString(), LocalDate.now()));
		}
	}

	private Currency getCurrencyFromApi(CurrencyCode code, String date) {
		try {
			URL apiUrl = new URL(String.format(DATA_PATH, code, date));
			return new ObjectMapper().readValue(apiUrl, Currency.class);
		} catch (FileNotFoundException e) {
			return null;
		} catch (UnknownHostException e) {
			throw new ConnectionException("Unable to connect to the API: " + DATA_PATH);
		} catch (Exception e) {
			throw new CurrencyException(e);
		}
	}
}