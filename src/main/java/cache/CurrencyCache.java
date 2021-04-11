package cache;

import java.time.LocalDate;
import java.util.Iterator;

import model.Currency;
import model.CurrencyCode;
import repository.CurrencyRepository;
import service.LoadingUtils;

public class CurrencyCache extends Cache<Currency> implements CurrencyRepository {
	
	public Currency find(CurrencyCode code, LocalDate date) {
		date = LoadingUtils.verifyAndCorrectDate(date);
		Iterator<Currency> i = cache.iterator();
		Currency temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getCode() == code && temp.getEffectiveDate().equals(date)) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public Currency get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency save(Currency currency) {
		// TODO Auto-generated method stub
		return null;
	}
}