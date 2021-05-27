import entity.CurrencyEntity;
import model.CurrencyCode;
import repository.CurrencyRepository;
import repository.CurrencyRepositoryImpl;
import repository.EntityManagerUtil;

public class Main {
	
	public static void main(String[] args) {
		EntityManagerUtil.startEntityManager();
		
		CurrencyRepository cr = new CurrencyRepositoryImpl();
		CurrencyEntity currency = new CurrencyEntity();
		currency.setCode(CurrencyCode.CZK);
		currency.setName("Korona");
		
		cr.saveCurrency(currency);
		
		EntityManagerUtil.closeEntityManager();
	}
}