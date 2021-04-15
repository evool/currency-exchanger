package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter @Setter
@NoArgsConstructor

public class CountryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@ManyToMany
	private Set<CurrencyEntity> currencies;

	public CountryEntity(String name) {
		this.name = name;
	}
	
	public CountryEntity(String name, Set<CurrencyEntity> currencies) {
		this.name = name;
		this.currencies = currencies;
	}
}