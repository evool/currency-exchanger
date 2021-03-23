package entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Currency {
	private String table;
	private String currency;
	private CurrencyCode code;
	private List<Rate> rates;
	
	
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public CurrencyCode getCode() {
		return code;
	}

	public void setCode(CurrencyCode code) {
		this.code = code;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
	
	public BigDecimal getRate() {
		return rates.get(0).getMid();
	}
	
	public BigDecimal exchangeToPLN(BigDecimal value) {
		return value.multiply(getRate());
	}
	
	public BigDecimal exchangeFromPLN(BigDecimal value) {
		return value.divide(getRate(), 4, RoundingMode.HALF_UP);
	}
}
