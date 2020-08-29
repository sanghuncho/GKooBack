package util;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author sanghuncho
 *
 */
public class DateUtil {

    public static Date toSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf( localDate );
    }
    
    public static LocalDate toLocalDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
}