

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import currency.Currency;
import currency.CurrencyCode;

public class Main {
	public static void main(String[] args) {
//		SaleDocumentService.insert();

//		Cache<Currency> cache = new Cache<>();
//		cache.add(new Currency("Dsds", CurrencyCode.AUD, BigDecimal.valueOf(10), LocalDate.of(2010, 10, 10)));
//		cache.add(new Currency("fdsfds", CurrencyCode.BGN, BigDecimal.valueOf(100), LocalDate.of(2010, 10, 10)));
//		Currency cur = new Currency("saaa", CurrencyCode.BRL, BigDecimal.valueOf(1000), LocalDate.of(2010, 10, 10));
//		System.out.println(cache.getIfFound(cur));

//		CurrencyDao currencyDao = new CurrencyDao();
//		currencyDao.save();
//		System.out.println(currencyDao.get(1L));

		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(currency.Currency.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Currency currency = new Currency("Ojro", CurrencyCode.EUR, BigDecimal.valueOf(10), LocalDate.now());
		session.save(currency);
		transaction.commit();
		session.close();
	}
}