package service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import entity.CurrencyEntity;
import exceptions.CurrencyException;
import model.CurrencyCode;
import parser.NbpJsonToCurrencyParser;
import provider.CacheProvider;
import provider.DatabaseProvider;
import provider.FileProvider;
import provider.NbpProvider;
import saver.CacheSaver;
import saver.DatabaseSaver;

public class Exchange {
	private Loading[] loaders;
	private Sending[] senders;
	
	public Exchange() {
		Loading cacheLoader = new Loader(new CacheProvider());
		Loading dbLoader = new Loader(new DatabaseProvider());
		Loading nbpLoader = new Loader(new NbpProvider(), new NbpJsonToCurrencyParser());
		Loading fileLoader = new Loader(new FileProvider());
		
		Sending cacheSender = new Sender(new CacheSaver());
		Sending dbSender = new Sender(new DatabaseSaver());
		
		setLoaders(cacheLoader, dbLoader, nbpLoader, fileLoader);
		setSenders(cacheSender, dbSender);
	}
	
	public void setLoaders(Loading...loaders) {
		this.loaders = loaders;
	}
	
	public void setSenders(Sending...senders) {
		this.senders = senders;
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		CurrencyEntity currency = load(code, date);
		save(currency);
		return amount.multiply(currency.getRate(date));
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		CurrencyEntity currency = load(code, date);
		save(currency);
		return amount.divide(currency.getRate(date), 4, RoundingMode.HALF_UP);
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
		return fromPLN(amount, code, LocalDate.now());
	}

	private CurrencyEntity load(CurrencyCode code, LocalDate date) {
		CurrencyEntity currency;
		for(int i = 0; i < loaders.length; i++) {
			currency = loaders[i].load(code, date);
			if(currency != null) {
				return currency;
			}
		}
		throw new CurrencyException("None of the providers delivered data.");
	}
	
	private void save(CurrencyEntity currency) {
		for(int i = 0; i < senders.length; i++) {
			senders[i].send(currency);
		}
	}
}