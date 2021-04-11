package cache;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Iterator;

import entity.CurrencyEntity;
import model.CurrencyCode;
import service.LoadingUtils;

public class CurrencyCache extends Cache<CurrencyEntity> {
	
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		date = LoadingUtils.verifyAndCorrectDate(date);
		Iterator<CurrencyEntity> i = cache.iterator();
		CurrencyEntity temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getCode() == code && temp.getEffectiveDate().equals(date)) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public CurrencyEntity get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyEntity save(Currency currency) {
		// TODO Auto-generated method stub
		return null;
	}
}