import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.Test;

import currency.Currency;
import currency.CurrencyCode;
import exceptions.FutureDateException;
import strategies.load.LoadFromNbp;
import strategies.load.Loading;
import strategies.parse.NbpJsonParser;
import strategies.parse.Parsing;

public class LoadFromNBPTest {

	final BigDecimal MONEY = new BigDecimal(20);
	Loading loadStrategy = new LoadFromNbp();
	Parsing parseStrategy = new NbpJsonParser();


	@Test
	public void should_exchangeToPLN() {
		// given
		CurrencyCode code = CurrencyCode.ISK;
		
		// when
		String jsonAsString = loadStrategy.load(code);
		Currency currency = parseStrategy.parse(jsonAsString);
		currency.exchangeToPLN(MONEY);
		
		// then
	}

	@Test
	public void should_exchangeToPLN_when_specificDateWasGiven() {
		// given
		LocalDate date = LocalDate.of(2006, 4, 26);
		CurrencyCode code = CurrencyCode.EUR;
		BigDecimal currencyValueFromNBP = new BigDecimal("77.6960");
		
		// when
		String jsonAsString = loadStrategy.load(code, date);
		Currency currency = parseStrategy.parse(jsonAsString);
		BigDecimal val2 = currency.exchangeToPLN(MONEY);
		
		// then
		assertThat(currencyValueFromNBP).isEqualTo(val2);
	}

	@Test
	public void should_exchangeToPLN_when_thereWasNoRateThatDate() {
		// given
		LocalDate sunday = LocalDate.of(2021, 3, 7);
		CurrencyCode code = CurrencyCode.EUR;
		BigDecimal currencyValueFromSunday = new BigDecimal("91.5860");
		
		// when
		String jsonAsString = loadStrategy.load(code, sunday);
		Currency currency = parseStrategy.parse(jsonAsString);
		BigDecimal val2 = currency.exchangeToPLN(MONEY);
		
		// then
		assertThat(currencyValueFromSunday).isEqualTo(val2);
	}

	@Test
	public void should_throwCurrencyException_when_dateIsAfterToday() {
		// given
		LocalDate date = LocalDate.now().plusDays(1);
		CurrencyCode code = CurrencyCode.EUR;
		
		// when
		Throwable thrown = catchThrowable(() -> {loadStrategy.load(code, date);});
		
		// then
		assertThat(thrown).isInstanceOf(FutureDateException.class);
	}

	@Test
	public void should_exchangeFromPLN() {
		// given
		CurrencyCode code = CurrencyCode.CHF;
		
		// when
		String jsonAsString = loadStrategy.load(code);
		Currency currency = parseStrategy.parse(jsonAsString);
		currency.exchangeFromPLN(MONEY);
		
		// then
	}
}