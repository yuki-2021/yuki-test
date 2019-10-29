import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class LocalDateTimeDemo {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek);

        System.out.println(ZoneId.systemDefault());
    }

    public static Instant toBeijingInstant(LocalDateTime ldt) {
        return ldt.toInstant(ZoneOffset.of("+08:00"));
    }

    public static ZonedDateTime toInstant(Instant instant){
        return instant.atZone(ZoneId.systemDefault());
    }
}
