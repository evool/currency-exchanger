import java.math.BigDecimal;
import java.time.LocalDate;

import model.CurrencyCode;
import parser.NbpJsonToCurrencyParser;
import provider.NbpProvider;
import saver.DatabaseSaver;
import service.Exchange;
import service.Loader;
import service.Sender;

public class Main {
	
//	static final int ILOSC_DNI = 2;
//	static LocalDate data;
	
	public static void main(String[] args) {

		SaleDocumentService.insert();
		
//		Exchange ex = new Exchange();
//		ex.setLoaders(new Loader(new NbpProvider(), new NbpJsonToCurrencyParser()));
//		ex.setSenders(new Sender(new DatabaseSaver()));
//		
//		for (CurrencyCode code : CurrencyCode.values()) {
//			data = LocalDate.now();
//			for(int i = 0; i < ILOSC_DNI; i++) {
//				System.out.println(code + " " + data);
//				ex.toPLN(BigDecimal.ONE, code, data);
//				data = data.minusDays(1);
//			}
//		}

		
//		CurrencyEntity currency = new CurrencyEntity();
//		currency.setCode(CurrencyCode.USD);
//		currency.setName("Dolar amerykanski");
//
//		CountryEntity country = new CountryEntity("AMERYKA");
//		
//		Set<CountryEntity> set = new HashSet<>();
//		set.add(country);
//		
//		currency.setCountries(set);
//		new CurrencyRepositoryImpl().saveCurrency(currency);
	}
}