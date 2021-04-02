import java.math.BigDecimal;
import java.time.LocalDate;

import model.CurrencyCode;
import service.Exchange;
import service.Loader;
import service.Loading;
import service.Sender;
import service.Sending;
import service.parser.NbpJsonToCurrencyParser;
import service.provider.CacheProvider;
import service.provider.DatabaseProvider;
import service.provider.FileProvider;
import service.provider.NbpProvider;
import service.saver.CacheSaver;
import service.saver.DatabaseSaver;

public class SaleDocumentService {
	public static void insert() {
		
		BigDecimal money = new BigDecimal(20.0);
		CurrencyCode code = CurrencyCode.EUR;
		LocalDate date = LocalDate.of(2021, 3, 20);
		
		BigDecimal value = new Exchange().toPLN(money, code, date);
		new Exchange().toPLN(money, code, date.minusDays(1));
		
		
		
		
		
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