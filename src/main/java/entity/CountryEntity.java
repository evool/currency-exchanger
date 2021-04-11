//package entity;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "countries")
//@NoArgsConstructor
//@Getter
//public class CountryEntity {
//	
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String name;
//	
//	@ManyToMany(mappedBy = "countries")
//	private Set<CurrencyEntity> currency = new HashSet<>();
//}