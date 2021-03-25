package strategies.load;

import java.time.LocalDate;

import currency.CurrencyCode;
import exceptions.FutureDateException;
import exceptions.InvalidDateException;

public class LoadingUtils {
	public static String tryToLoadData(Loading dataProvider, CurrencyCode code, LocalDate date, int attempts) {
		for(int i = attempts; i > 0; i--) {
			String result = dataProvider.load(code, date);
			if(result != null) {
				return result;
			}
			date = date.minusDays(1);
		}
		throw new InvalidDateException(String.format("Cannot find valid rate %s for %s.", code, date.toString()));
	}
	public static void checkIsDateAfterToday(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			throw new FutureDateException(
					String.format("The requested rate is from %s, today is %s.", date.toString(), LocalDate.now()));
		}
	}
}