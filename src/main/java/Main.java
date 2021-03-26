import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main {
	public static void main(String[] args) {
//		SaleDocumentService.insert();
		
//		BigDecimal money = new BigDecimal(20.0);
//		CurrencyCode code = CurrencyCode.EUR;
//		LocalDate date = LocalDate.of(2021, 3, 21);
//		
//		new Exchange().toPLN(money, code, date);
//		new Exchange().toPLN(money, code, date);
//		new Exchange().toPLN(money, CurrencyCode.AUD, date);
//		new Exchange().toPLN(money, code, date);
//		new Exchange().toPLN(money, CurrencyCode.AUD, date);
		
		HashSet<String> set = new LinkedHashSet<>();
		
		set.add("Czesc");
		set.add("Jestem");
		set.add("Krystian");
		set.add("M");
		
		set.remove(set.iterator().next());
		Iterator<String> i = set.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
}