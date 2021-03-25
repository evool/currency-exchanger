import java.math.BigDecimal;
import java.time.LocalDate;

import currency.CurrencyCode;

public class Main {
	public static void main(String[] args) {
//		SaleDocumentService.insert();
		
		BigDecimal money = new BigDecimal(20.0);
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2021, 3, 21);
		
		new Exchange().toPLN(money, code, date);
		new Exchange().toPLN(money, code, date);
		new Exchange().toPLN(money, CurrencyCode.AUD, date);
		new Exchange().toPLN(money, code, date);
		new Exchange().toPLN(money, CurrencyCode.AUD, date);
	}
}