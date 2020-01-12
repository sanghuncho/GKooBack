package util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	public static final LocalDate getRequestDate() {
	    ZoneId zone = ZoneId.of("Asia/Seoul");
        LocalDate currentOrderDate = LocalDate.now(zone);
        return currentOrderDate;
	}
	
	public static final Timestamp getTimestampKorea() {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		Timestamp timestamp = Timestamp.valueOf(now);
		return timestamp;
	}
}
