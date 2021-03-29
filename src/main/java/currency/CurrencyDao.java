package currency;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CurrencyDao {
	public Currency get(Long id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Currency currency = session.get(Currency.class, id);
        session.close();
        return currency;
	}
	public void save() {
		Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.save(new Currency("euro", CurrencyCode.EUR, new BigDecimal("12.34"), LocalDate.now()));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}
}
