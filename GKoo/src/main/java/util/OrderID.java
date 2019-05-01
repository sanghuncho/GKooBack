package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OrderID {

	public static String generateOrderID() {
		ZoneId zone = ZoneId.of("Asia/Seoul");
		LocalDate currentDate = LocalDate.now(zone);
		LocalTime currentTime = LocalTime.now(zone);
		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formattedDate = currentDate.format(formatterDate);
		
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HHmmss");
		String formattedTime = currentTime.format(formatterTime);
				
		System.out.println(formattedDate.concat(formattedTime));
		return formattedDate.concat(formattedTime);
	}
}
