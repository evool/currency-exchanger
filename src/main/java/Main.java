import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import repository.CurrencyRepository;
import repository.CurrencyRepositoryImpl;
import service.LoadingUtils;
import service.parser.NbpJsonToCurrencyParser;
import service.parser.Parsing;
import service.provider.NbpProvider;
import service.provider.Providing;

public class Main {
	
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2021, 4, 11);
		date = LoadingUtils.verifyAndCorrectDate(date);
		CurrencyCode code = CurrencyCode.EUR;
		
		Providing<String> provider = new NbpProvider();
		Parsing parser = new NbpJsonToCurrencyParser();
		
		CurrencyEntity ce;
		
		String s = provider.find(code, date);
		ce = parser.parse(s);
		
		CurrencyRepository cr = new CurrencyRepositoryImpl();
		
		cr.save(ce);
		try {
			System.in.read();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(ce.getRates());
	}
}