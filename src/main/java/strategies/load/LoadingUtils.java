package strategies.load;

import java.time.LocalDate;

import exceptions.FutureDateException;

public class LoadingUtils {
	static void checkDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			throw new FutureDateException(
					String.format("The requested rate is from %s, today is %s.", date.toString(), LocalDate.now()));
		}
	}
}
