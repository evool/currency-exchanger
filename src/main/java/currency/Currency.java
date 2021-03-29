package currency;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
public final class Currency {
	
	@Id
	private Long Id;
	private String name;
	private CurrencyCode code;
	private BigDecimal rate;
	private LocalDate effectiveDate;
	
	public Currency(String name, CurrencyCode code, BigDecimal rate, LocalDate effectiveDate) {
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
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCode(CurrencyCode code) {
		this.code = code;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public String toString() {
		return "Currency [name=" + name + ", code=" + code + ", rate=" + rate + ", effectiveDate=" + effectiveDate
				+ "]";
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