import java.math.BigDecimal;
import java.time.LocalDate;

import model.CurrencyCode;
import service.CacheProvider;
import service.CacheSaver;
import service.DatabaseProvider;
import service.DatabaseSaver;
import service.Exchange;
import service.FileProvider;
import service.Loader;
import service.Loading;
import service.NbpJsonToCurrencyParser;
import service.NbpProvider;
import service.Sender;
import service.Sending;

public class Main {
	public static void main(String[] args) {

		BigDecimal money = new BigDecimal(20.0);
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2021, 3, 20);
		
//		BigDecimal value = new Exchange().toPLN(money, code, date);
		
		
		
		
		
		
		Loading cacheLoader = new Loader(new CacheProvider());
		Loading dbLoader = new Loader(new DatabaseProvider());
		Loading nbpLoader = new Loader(new NbpProvider(), new NbpJsonToCurrencyParser());
		Loading fileLoader = new Loader(new FileProvider());
		
		Sending cacheSender = new Sender(new CacheSaver());
		Sending dbSender = new Sender(new DatabaseSaver());
		
		Exchange ex = new Exchange();
		ex.setLoaders(cacheLoader, dbLoader, nbpLoader, fileLoader);
		ex.setSenders(cacheSender, dbSender);
		
		BigDecimal value2 = ex.toPLN(money, code);

	}
}