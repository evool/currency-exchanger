package currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public final class Currency {
	private final String name;
	private final CurrencyCode code;
	private final BigDecimal rate;
	private final LocalDate effectiveDate;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (code != other.code)
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}
	
}
