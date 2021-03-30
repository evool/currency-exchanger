package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String currencyName;
	private CurrencyCode currencyCode;
	private BigDecimal currencyRate;
	private LocalDate effectiveDate;
	
	public Currency(String name, CurrencyCode code, BigDecimal rate, LocalDate effectiveDate) {
		this.currencyName = name;
		this.currencyCode = code;
		this.currencyRate = rate;
		this.effectiveDate = effectiveDate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return currencyName;
	}

	public CurrencyCode getCode() {
		return currencyCode;
	}

	public BigDecimal getRate() {
		return currencyRate;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	@Override
	public String toString() {
		return "Currency [Id=" + id + ", name=" + currencyName + ", code=" + currencyCode + ", rate=" + currencyRate + ", effectiveDate="
				+ effectiveDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
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
		if (currencyCode != other.currencyCode)
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		return true;
	}
}