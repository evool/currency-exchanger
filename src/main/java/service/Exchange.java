package service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import entity.CurrencyEntity;
import exceptions.CurrencyException;
import model.CurrencyCode;
import service.parser.NbpJsonToCurrencyParser;
import service.provider.CacheProvider;
import service.provider.DatabaseProvider;
import service.provider.FileProvider;
import service.provider.NbpProvider;
import service.saver.CacheSaver;
import service.saver.DatabaseSaver;

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
		return amount.multiply(currency.getRate());
	}
	
	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
		return toPLN(amount, code, LocalDate.now());
	}
	
	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
		CurrencyEntity currency = load(code, date);
		save(currency);
		return amount.divide(currency.getRate(), 4, RoundingMode.HALF_UP);
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
		throw new CurrencyException();
	}
	
	private void save(CurrencyEntity currency) {
		for(int i = 0; i < senders.length; i++) {
			senders[i].send(currency);
		}
	}
}