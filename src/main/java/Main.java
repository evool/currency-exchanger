import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import service.parser.NbpJsonToCurrencyParser;
import service.parser.Parsing;
import service.provider.NbpProvider;
import service.provider.Providing;

public class Main {
	
	public static void main(String[] args) {

		Providing<String> provider = new NbpProvider();
		Parsing parser = new NbpJsonToCurrencyParser();
		
		CurrencyEntity ce;
		
		String s = provider.find(CurrencyCode.EUR, LocalDate.now());
		ce = parser.parse(s);
		
		
	}
}