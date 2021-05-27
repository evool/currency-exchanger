package entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.CurrencyCode;

@Entity
@Table(name = "currencies")
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonAlias("currency")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true, length = 3)
	private CurrencyCode code;
	
	@OneToMany(mappedBy = "currency")  //(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Set<RateEntity> rates;
	
	@ManyToMany(mappedBy = "currencies")
	private Set<CountryEntity> countries;
	
	public CurrencyEntity(String name, CurrencyCode code) {
		this.name = name;
		this.code = code;
	}
	
	public void addRates(Set<RateEntity> rates) {
		this.rates.addAll(rates);
	}
	
	public void addRate(RateEntity rate) {
		this.rates.add(rate);
	}
	
	public BigDecimal getRate(LocalDate date) {
		RateEntity temp;
		Iterator<RateEntity> i;
		for(int attempts = 5; attempts > 0; attempts--) {
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
