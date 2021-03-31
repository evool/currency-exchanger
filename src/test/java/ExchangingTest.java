//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import org.testng.annotations.Test;
//
//import exceptions.FutureDateException;
//import exceptions.InvalidDateException;
//import model.CurrencyCode;
//import service.Exchange;
//import service.NbpJsonParser;
//import service.NbpProvider;
//
//public class ExchangingTest {
//
//	final BigDecimal MONEY = new BigDecimal(20);
//
//	@Test
//	public void should_exchangeToPLN() {
//		// given
//		CurrencyCode code = CurrencyCode.ISK;
//		Exchange exchange = new Exchange();
//		
//		// when
//		exchange.toPLN(MONEY, code);
//		
//		// then
//	}
//
//	@Test
//	public void should_exchangeToPLN_when_specificDateWasGiven() {
//		// given
//		CurrencyCode code = CurrencyCode.EUR;
//		LocalDate date = LocalDate.of(2006, 4, 26);
//		BigDecimal currencyValueFromPast = new BigDecimal("77.6960");
//		Exchange exchange = new Exchange();
//		
//		// when
//		BigDecimal currencyValueFromNBP = exchange.toPLN(MONEY, code, date);
//		
//		// then
//		assertThat(currencyValueFromPast).isEqualTo(currencyValueFromNBP);
//	}
//
//	@Test
//	public void should_exchangeToPLN_when_thereWasNoRateThatDate() {
//		// given
//		LocalDate sunday = LocalDate.of(2021, 3, 7);
//		CurrencyCode code = CurrencyCode.EUR;
//		BigDecimal currencyValueFromSunday = new BigDecimal("91.5860");
//		Exchange exchange = new Exchange();
//		
//		// when
//		BigDecimal currencyValueFromNBP = exchange.toPLN(MONEY, code, sunday);
//		
//		// then
//		assertThat(currencyValueFromSunday).isEqualTo(currencyValueFromNBP);
//	}
//
//	@Test
//	public void should_throwCurrencyException_when_exchangeToDateIsAfterToday() {
//		// given
//		LocalDate date = LocalDate.now().plusDays(1);
//		CurrencyCode code = CurrencyCode.EUR;
//		Exchange exchange = new Exchange();
//		
//		// when
//		Throwable thrown = catchThrowable(() -> {exchange.toPLN(MONEY, code, date);});
//		
//		// then
//		assertThat(thrown).isInstanceOf(FutureDateException.class)
//						  .hasMessageContaining("The requested rate is from");;
//	}
//	
//	@Test
//	public void should_throwInvalidDateException_when_exchangeToDateIsBeforeLoggingStarted() {
//		// given
//		LocalDate veryOldDate = LocalDate.of(1800, 1, 1);
//		CurrencyCode code = CurrencyCode.HKD;
//		Exchange exchange = new Exchange();		
//		
//		// when
//		Throwable thrown = catchThrowable(() -> {exchange.toPLN(MONEY, code, veryOldDate);});
//		
//		// then
//		assertThat(thrown).isInstanceOf(InvalidDateException.class)
//						  .hasMessageContaining("There is no data for currency");
//	}
//
//	@Test
//	public void should_exchangeFromPLN() {
//		// given
//		CurrencyCode code = CurrencyCode.CHF;
//		Exchange exchange = new Exchange();
//		
//		// when
//		exchange.fromPLN(MONEY, code);
//		
//		// then
//	}
//	
//	@Test
//	public void should_exchangeFromPLN_when_specificDateWasGiven() {
//		// given
//		CurrencyCode code = CurrencyCode.EUR;
//		LocalDate date = LocalDate.of(2006, 4, 26);
//		BigDecimal currencyValueFromPast = new BigDecimal("5.1483");
//		Exchange exchange = new Exchange();
//		
//		// when
//		BigDecimal currencyValueFromNBP = exchange.fromPLN(MONEY, code, date);
//		
//		// then
//		assertThat(currencyValueFromPast).isEqualTo(currencyValueFromNBP);
//	}
//
//	@Test
//	public void should_exchangeFromPLN_when_thereWasNoRateThatDate() {
//		// given
//		LocalDate sunday = LocalDate.of(2021, 3, 7);
//		CurrencyCode code = CurrencyCode.EUR;
//		BigDecimal currencyValueFromSunday = new BigDecimal("4.3675");
//		Exchange exchange = new Exchange();
//		
//		// when
//		BigDecimal currencyValueFromNBP = exchange.fromPLN(MONEY, code, sunday);
//		
//		// then
//		assertThat(currencyValueFromSunday).isEqualTo(currencyValueFromNBP);
//	}
//
//	@Test
//	public void should_throwCurrencyException_when_exchangeFromDateIsAfterToday() {
//		// given
//		LocalDate date = LocalDate.now().plusDays(1);
//		CurrencyCode code = CurrencyCode.EUR;
//		Exchange exchange = new Exchange();
//		
//		// when
//		Throwable thrown = catchThrowable(() -> {exchange.fromPLN(MONEY, code, date);});
//		
//		// then
//		assertThat(thrown).isInstanceOf(FutureDateException.class)
//						  .hasMessageContaining("The requested rate is from");;
//	}
//	
//	@Test
//	public void should_throwInvalidDateException_when_exchangeFromDateIsBeforeLoggingStarted() {
//		// given
//		LocalDate veryOldDate = LocalDate.of(1800, 1, 1);
//		CurrencyCode code = CurrencyCode.HKD;
//		Exchange exchange = new Exchange();		
//		
//		// when
//		Throwable thrown = catchThrowable(() -> {exchange.fromPLN(MONEY, code, veryOldDate);});
//		
//		// then
//		assertThat(thrown).isInstanceOf(InvalidDateException.class)
//						  .hasMessageContaining("There is no data for currency");
//	}
//	
//	@Test
//	public void should_getCurrencyFromCache_when_currencyIsAlreadyThere() {
//		// given
//		Exchange exchangeFromNbp = new Exchange(new NbpProvider(), new NbpJsonParser());
//		Exchange exchangeFromCache = new Exchange(null, null);
//		
//		// when
//		exchangeFromNbp.fromPLN(MONEY, CurrencyCode.EUR);
//		exchangeFromCache.fromPLN(MONEY, CurrencyCode.EUR);
//		
//		// then
//	}
//}