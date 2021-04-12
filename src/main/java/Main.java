import java.math.BigDecimal;
import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import parser.NbpJsonToCurrencyParser;
import parser.Parsing;
import provider.NbpProvider;
import provider.Providing;
import repository.CurrencyRepository;
import repository.CurrencyRepositoryImpl;
import service.Exchange;
import service.LoadingUtils;

public class Main {
	
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2021, 4, 7);
		date = LoadingUtils.verifyAndCorrectDate(date);
		CurrencyCode code = CurrencyCode.EUR;
		CurrencyRepository cr = new CurrencyRepositoryImpl();
		
		Providing<String> provider = new NbpProvider();
		Parsing<String, CurrencyEntity> parser = new NbpJsonToCurrencyParser();
		
		CurrencyEntity ce;
		
		String s = provider.find(code, date);
		ce = parser.parse(s);		
		cr.save(ce);
		s = provider.find(code, LocalDate.of(2021, 4, 8));
		ce = parser.parse(s);
		cr.save(ce);
		
		code = CurrencyCode.AUD;
		s = provider.find(code, date);
		ce = parser.parse(s);
		cr.save(ce);
		
		BigDecimal b = new Exchange().toPLN(BigDecimal.TEN, CurrencyCode.CZK, date);
		System.out.println(b);
	}
}