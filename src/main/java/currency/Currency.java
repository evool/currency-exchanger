package currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Currency {
	private String name;
	private CurrencyCode code;
	private BigDecimal rate;
	private LocalDate effectiveDate;
	
	public Currency(String name, CurrencyCode code, BigDecimal rate, LocalDate effectiveDate) {
		super();
		this.name = name;
		this.code = code;
		this.rate = rate;
		this.effectiveDate = effectiveDate;
	}

	public String getName() {
		return name;
	}

	public CurrencyCode getCode() {
		return code;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	@Override
	public String toString() {
		return "Currency [name=" + name + ", code=" + code + ", rate=" + rate + ", effectiveDate=" + effectiveDate
				+ "]";
	}
	
	public BigDecimal exchangeToPLN(BigDecimal value) {
		return value.multiply(getRate());
	}
	
	public BigDecimal exchangeFromPLN(BigDecimal value) {
		return value.divide(getRate(), 4, RoundingMode.HALF_UP);
	}
}
