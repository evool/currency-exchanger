import currency.CurrencyDao;

public class Main {
	public static void main(String[] args) {
//		SaleDocumentService.insert();
		
		CurrencyDao currencyDao = new CurrencyDao();
		
		currencyDao.save();
		System.out.println(currencyDao.get(1L));
	}
}