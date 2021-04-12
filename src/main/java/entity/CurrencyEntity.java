package entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import model.CurrencyCode;

@Entity
@Table(name = "currencies")
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String currency;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private CurrencyCode code;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Set<RateEntity> rates;
	
//	@ManyToMany
//	private Set<CountryEntity> countries;
	
	public void addRates(Set<RateEntity> rates) {
		this.rates.addAll(rates);
	}
	
	public BigDecimal getRate(LocalDate date) {
		RateEntity temp;
		Iterator<RateEntity> i;
		for(int attempts = 10; attempts > 0; attempts--) {
			i = rates.iterator();
			while(i.hasNext()) {
				temp = i.next();
				if(temp.getEffectiveDate().equals(date)) {
					return temp.getMid();
				}
			}		
			date = date.minusDays(1);
		}
		return null;
	}
}
