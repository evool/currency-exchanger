import java.math.BigDecimal;

import cache.CurrencyCacheUtils;
import model.CurrencyCode;
import service.Exchange;

public class Main {
	public static void main(String[] args) {

//		for(CurrencyCode c : CurrencyCode.values()) {
//			new Exchange().toPLN(BigDecimal.TEN, c);
//		}
		
		SaleDocumentService.insert();
		
//		CurrencyCacheUtils.get().printAll();
	}
}