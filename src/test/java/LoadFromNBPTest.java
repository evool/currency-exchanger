import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.Test;

import entities.Currency;
import entities.CurrencyCode;
import exceptions.FutureDateException;
import strategies.LoadFromNBP;
import strategies.LoadStrategy;

public class LoadFromNBPTest {

	final BigDecimal MONEY = new BigDecimal(20);
	final LoadStrategy strategy = new LoadFromNBP();
	Currency currency;

	@Test
	public void should_exchangeToPLN() {
		// given
		CurrencyCode code = CurrencyCode.ISK;
		
		// when
		currency = strategy.load(code);
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
		currency = strategy.load(code, date);
		BigDecimal val2 = currency.exchangeToPLN(MONEY);
		
		// then
		assertThat(currencyValueFromNBP).isEqualTo(val2);
	}

	@Test
	public void should_exchangeToPLN_when_thereWasNoRateThatDate() {
		// given
		LocalDate weekend = LocalDate.of(2021, 3, 7);
		CurrencyCode code = CurrencyCode.EUR;
		BigDecimal currencyValueFromSunday = new BigDecimal("91.5860");
		
		// when
		currency = strategy.load(code, weekend);
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
		Throwable thrown = catchThrowable(() -> { currency = strategy.load(code, date); });
		
		// then
		assertThat(thrown).isInstanceOf(FutureDateException.class);
	}

	@Test
	public void should_exchangeFromPLN() {
		// given
		CurrencyCode code = CurrencyCode.CHF;
		
		// when
		currency = strategy.load(code);
		currency.exchangeFromPLN(MONEY);
		
		// then
	}
}