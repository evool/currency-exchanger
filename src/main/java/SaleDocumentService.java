import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.CountryEntity;
import entity.CurrencyEntity;
import model.CurrencyCode;
import parser.NbpJsonToCurrencyParser;
import provider.CacheProvider;
import provider.DatabaseProvider;
import provider.FileProvider;
import provider.NbpProvider;
import repository.CountryRepository;
import repository.CountryRepositoryImpl;
import repository.CurrencyRepository;
import repository.CurrencyRepositoryImpl;
import saver.CacheSaver;
import saver.DatabaseSaver;
import service.Exchange;
import service.Loader;
import service.Loading;
import service.Sender;
import service.Sending;

public class SaleDocumentService {
	public static void insert() {
				
//		BigDecimal money = new BigDecimal(20.0);
//		CurrencyCode code = CurrencyCode.EUR;
//		LocalDate date = LocalDate.of(2021, 3, 18);
//		
//		
//		// Po prostu zamiana walut
//		BigDecimal value = new Exchange().toPLN(money, code, date);
//				
//				
//		// Ustawianie kolejnosci pobierania i wysylania
//		Loading cacheLoader = new Loader(new CacheProvider());
//		Loading dbLoader = new Loader(new DatabaseProvider());
//		Loading nbpLoader = new Loader(new NbpProvider(), new NbpJsonToCurrencyParser());
//		Loading fileLoader = new Loader(new FileProvider());
//		
//		Sending cacheSender = new Sender(new CacheSaver());
//		Sending dbSender = new Sender(new DatabaseSaver());
//		
//		Exchange ex = new Exchange();
//		ex.setLoaders(dbLoader, nbpLoader, fileLoader);
//		ex.setSenders(dbSender);
//		
//		BigDecimal value2 = ex.toPLN(money, code);
		
		
		// Kraje
		CurrencyRepository currencyRepo = new CurrencyRepositoryImpl();
		CountryRepository countryRepo = new CountryRepositoryImpl();
		
		CurrencyEntity currency1 = new CurrencyEntity("Dolar", CurrencyCode.USD);
		CurrencyEntity currency2 = new CurrencyEntity("Euro", CurrencyCode.EUR);
		CurrencyEntity currency3 = new CurrencyEntity("Korona czeska", CurrencyCode.CZK);
		CurrencyEntity currency4 = new CurrencyEntity("Franki szwajcarskie", CurrencyCode.CHF);
		CurrencyEntity currency5 = new CurrencyEntity("Funty brytyjskie", CurrencyCode.GBP);
		
		CountryEntity country1 = new CountryEntity("Ameryka");
		CountryEntity country2 = new CountryEntity("Europa");
		CountryEntity country3 = new CountryEntity("Wielka Brytania");
		
		currency1.setCountries(Set.of(country1));
		currency2.setCountries(Set.of(country2, country1));
		currency3.setCountries(Set.of(country2));
		currencyRepo.saveCurrency(currency1);
		currencyRepo.saveCurrency(currency2);
		currencyRepo.saveCurrency(currency3);
		
//		country3.setCurrencies(Set.of(currency4, currency5));
//		countryRepo.saveCountry(country3);
	}
}






