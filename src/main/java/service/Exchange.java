package service;
import java.math.BigDecimal;
import java.time.LocalDate;

import entity.RateEntity;
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
		Loading cacheLoader = new Loader(new CacheProvider(), null);
		Loading dbLoader = new Loader(new DatabaseProvider(), null);
		Loading nbpLoader = new Loader(new NbpProvider(), new NbpJsonToCurrencyParser());
		Loading fileLoader = new Loader(new FileProvider(), null);
		
		Sending cacheSender = new Sender(new CacheSaver(), null);
		Sending dbSender = new Sender(new DatabaseSaver(), null);
		
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
		RateEntity rate = load(code, date);
		save(rate);
		return amount.multiply(rate.getMid());
	}
	
//	public BigDecimal toPLN(BigDecimal amount, CurrencyCode code) {
//		return toPLN(amount, code, LocalDate.now());
//	}
//	
//	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code, LocalDate date) {
//		CurrencyEntity currency = load(code, date);
//		save(currency);
//		return amount.divide(currency.getRate(date), 4, RoundingMode.HALF_UP);
//	}
//	
//	public BigDecimal fromPLN(BigDecimal amount, CurrencyCode code) {
//		return fromPLN(amount, code, LocalDate.now());
//	}

	private RateEntity load(CurrencyCode code, LocalDate date) {
		RateEntity rate;
		date = LoadingUtils.verifyAndCorrectDate(date);
		for (Loading loader : loaders) {
			rate = loader.load(code, date);
			if(rate != null) {
				return rate;
			}
		}
		throw new CurrencyException("None of the providers delivered data.");
	}
	
	private void save(RateEntity rate) {
		for (Sending sender : senders) {
			sender.send(rate);
		}
	}
}