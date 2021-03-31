import java.math.BigDecimal;
import java.time.LocalDate;

import model.CurrencyCode;
import service.Exchange;

public class SaleDocumentService {
	public static void insert() {
		BigDecimal money = new BigDecimal(20.0);
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2021, 3, 20);
		
		BigDecimal value = new Exchange().toPLN(money, code, date);
	}
}