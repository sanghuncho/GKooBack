package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TimeStamp {

	public static final String getCurrentTimeStampKorea() {
		ZoneId zone = ZoneId.of("Asia/Seoul");
		LocalDate currentDate = LocalDate.now(zone);
		LocalTime currentTime = LocalTime.now(zone);
		ZonedDateTime zoDaTi = ZonedDateTime.of(currentDate, currentTime, zone);
		String timeStamp = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zoDaTi);
		return timeStamp;
	}
}
