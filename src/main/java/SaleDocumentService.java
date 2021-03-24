import java.math.BigDecimal;
import java.time.LocalDate;

import currency.Currency;
import currency.CurrencyCode;
import strategies.load.LoadFromNbp;
import strategies.load.Loading;
import strategies.parse.NbpJsonParser;
import strategies.parse.Parsing;

public class SaleDocumentService {
	public static void insert() {
		
		BigDecimal money = new BigDecimal(20.0);
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2021, 3, 20);
		
		Loading loadStrategy = new LoadFromNbp();
		Parsing parseStrategy = new NbpJsonParser();
		String input = loadStrategy.load(code, date);
		Currency currency = parseStrategy.parse(input);

		System.out.println(currency.toString());
		System.out.println(currency.exchangeFromPLN(money));
		
	}
}