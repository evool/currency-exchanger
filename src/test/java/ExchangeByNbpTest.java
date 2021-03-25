import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.Test;

import currency.CurrencyCode;
import exceptions.FutureDateException;

public class ExchangeByNbpTest {

	final BigDecimal MONEY = new BigDecimal(20);

	@Test
	public void should_exchangeToPLN() {
		// given
		CurrencyCode code = CurrencyCode.ISK;
		Exchange exchange = new Exchange();
		
		// when
		exchange.toPLN(MONEY, code);
		
		// then
	}

	@Test
	public void should_exchangeToPLN_when_specificDateWasGiven() {
		// given
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2006, 4, 26);
		BigDecimal currencyValueFromPast = new BigDecimal("77.6960");
		Exchange exchange = new Exchange();
		
		// when
		BigDecimal currencyValueFromNBP = exchange.toPLN(MONEY, code, date);
		
		// then
		assertThat(currencyValueFromPast).isEqualTo(currencyValueFromNBP);
	}

	@Test
	public void should_exchangeToPLN_when_thereWasNoRateThatDate() {
		// given
		LocalDate sunday = LocalDate.of(2021, 3, 7);
		CurrencyCode code = CurrencyCode.EUR;
		BigDecimal currencyValueFromSunday = new BigDecimal("91.5860");
		Exchange exchange = new Exchange();
		
		// when
		BigDecimal currencyValueFromNBP = exchange.toPLN(MONEY, code, sunday);
		
		// then
		assertThat(currencyValueFromSunday).isEqualTo(currencyValueFromNBP);
	}

	@Test
	public void should_throwCurrencyException_when_dateIsAfterToday() {
		// given
		LocalDate date = LocalDate.now().plusDays(1);
		CurrencyCode code = CurrencyCode.EUR;
		Exchange exchange = new Exchange();
		
		// when
		Throwable thrown = catchThrowable(() -> {exchange.toPLN(MONEY, code, date);});
		
		// then
		assertThat(thrown).isInstanceOf(FutureDateException.class);
	}

	@Test
	public void should_exchangeFromPLN() {
		// given
		CurrencyCode code = CurrencyCode.CHF;
		Exchange exchange = new Exchange();
		
		// when
		exchange.fromPLN(MONEY, code);
		
		// then
	}
}