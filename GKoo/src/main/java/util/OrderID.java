package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OrderID {

	public int generateOrderId() {
		ZoneId zone = ZoneId.of("Asia/Seoul");
		LocalDate currentDate = LocalDate.now(zone);
		LocalTime currentTime = LocalTime.now(zone);
		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("ddMMyy");
		String formattedDate = currentDate.format(formatterDate);
		
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("mmHH");
		String formattedTime = currentTime.format(formatterTime);
				
		return Integer.parseInt(formattedTime.concat(formattedDate));
	}
}
