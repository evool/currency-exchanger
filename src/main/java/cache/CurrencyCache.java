package cache;

import java.time.LocalDate;
import java.util.Iterator;

import entity.CurrencyEntity;
import model.CurrencyCode;
import repository.CurrencyRepository;

public class CurrencyCache extends Cache<CurrencyEntity> implements CurrencyRepository {

	@Override
	public CurrencyEntity getCurrencyById(Long id) {
		Iterator<CurrencyEntity> i = cache.iterator();
		CurrencyEntity temp;
		while(i.hasNext()) {
			temp = i.next();
			if(temp.getId().equals(id)) {
				return temp;
			}
		}
		return null;
	}
	
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		Iterator<CurrencyEntity> i = cache.iterator();
		CurrencyEntity temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getCode() == code) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public void saveCurrency(CurrencyEntity currency) {
		super.save(currency);
	}

	@Override
	public CurrencyEntity getCurrencyByCode(CurrencyCode code) {
		Iterator<CurrencyEntity> i = cache.iterator();
		CurrencyEntity temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getCode() == code) {
				return temp;
			}
		}
		return null;
	}
}