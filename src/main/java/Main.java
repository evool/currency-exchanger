import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import model.Currency;
import model.CurrencyCode;
import repository.CurrencyRepository;
import repository.CurrencyRepositoryImpl;

public class Main {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
	private static EntityManager em = factory.createEntityManager();
	public static void main(String[] args) {
		String name = "Atatatata";
		CurrencyCode code = CurrencyCode.EUR;
		BigDecimal rate = new BigDecimal(4.5);
		LocalDate date = LocalDate.now();
		Currency c = new Currency(name, code, rate, date);
		CurrencyRepository cr = new CurrencyRepositoryImpl(em);
		
		em.getTransaction().begin();
		cr.saveCurrency(c);
		em.getTransaction().commit();
	}
}