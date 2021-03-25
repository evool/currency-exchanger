package strategies.load;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import exceptions.FutureDateException;

public class LoadingUtils {	
	public static LocalDate verifyItIsNotWeekend(LocalDate date) {
		if(LocalTime.now().isBefore(LocalTime.NOON)) {
			date = date.minusDays(1);
		}
		if(date.getDayOfWeek() == DayOfWeek.SATURDAY) {
			date = date.minusDays(1);
			return date;
		}
		if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.minusDays(2);
			return date;
		}
		return date;
	}
	
	public static void checkIsDateAfterToday(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			throw new FutureDateException(
					String.format("The requested rate is from %s, today is %s.", date.toString(), LocalDate.now()));
		}
	}
}