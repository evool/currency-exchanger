import java.time.LocalDate;

import model.Currency;
import model.CurrencyCache;
import model.CurrencyCode;
import service.CacheSaver;
import service.Loader;
import service.NbpJsonToCurrencyParser;
import service.NbpProvider;
import service.Parsing;
import service.Providing;
import service.Saving;

public class Main {
//	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
//	private static EntityManager em = factory.createEntityManager();
	public static void main(String[] args) {
		
		Providing provider = new NbpProvider();
		Parsing parser = new NbpJsonToCurrencyParser();
		Loader loader = new Loader(provider, parser);
		Saving saver = new CacheSaver();
		Currency c;
		
		c = loader.load(CurrencyCode.EUR, LocalDate.now()).get();
		System.out.println(c.getRate());
		
		saver.save(c);
		System.out.println("Saved to cache");

		c = CurrencyCache.find(CurrencyCode.EUR, LocalDate.now());
		System.out.println(c.getRate());
	}
}