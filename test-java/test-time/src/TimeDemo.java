import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Date;

public class TimeDemo {
    public static void main(String[] args) {

        Instant now = Instant.now();
        now = Instant.ofEpochMilli(System.currentTimeMillis());

        now.plus(1, ChronoUnit.MILLIS);
        System.out.println(now);
    }


    /**
     * Date转换Instant
     *
     * @param date
     * @return
     */
    public static Instant toInstant(Date date) {
        return Instant.ofEpochMilli(date.getTime());
    }


    public static Date toDate(Instant instant){
        return new Date(instant.toEpochMilli());
    }
}
