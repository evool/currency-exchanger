import java.math.BigDecimal;
import java.time.LocalDate;

import entities.Currency;
import entities.CurrencyCode;
import strategies.LoadFromNBP;
import strategies.LoadStrategy;

public class SaleDocumentService {
	public static void insert() {
		
		BigDecimal money = new BigDecimal(20.0);
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2013, 12, 11);
		
		
//		
//		LoadStrategy loadStrategy = new LoadFromNBP();
//		String input = loadStrategy.load(code, date);
//		ParseStrategy parseStrategy = new JsonParser();
//		
//		
//		
//		
//		
//		Currency currency = strategy.load(code, date);
//		
//		BigDecimal value = currency.exchangeFromPLN(money);
		
	}
}