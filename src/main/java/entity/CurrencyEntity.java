package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import model.CurrencyCode;

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String currency;
	
	@Enumerated(EnumType.STRING)
	private CurrencyCode code;
	
	@OneToMany(mappedBy = "currency")
	private Set<RateEntity> rates = new HashSet<>();
	
//	@ManyToMany
//	private Set<CountryEntity> countries;
}
