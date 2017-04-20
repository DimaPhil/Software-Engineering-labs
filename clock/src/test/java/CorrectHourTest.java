import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class CorrectHourTest {
    private static final double EPS = 1e-9;

    @Test
    public void checkHour() {
        SetableClock clock = new SetableClock(Instant.now().minus(Duration.ofHours(1)).minus(Duration.ofSeconds(1)));
        EventStatistics statistics = new EventStatisticsImpl(clock);
        statistics.incEvent("lunch");
        clock.set(Instant.now().minus(Duration.ofHours(1)).plus(Duration.ofSeconds(10)));
        statistics.incEvent("sleep");
        statistics.incEvent("sleep");
        clock.set(Instant.now());
        statistics.incEvent("now");
        statistics.incEvent("now");
        statistics.incEvent("now");
        assert(Math.abs(statistics.getEventStatisticByName("lunch")) <= EPS);
        assert(Math.abs(statistics.getEventStatisticByName("wrong_name")) <= EPS);
        assert(Math.abs(statistics.getEventStatisticByName("sleep") - 2. / 60) <= EPS);
        assert(Math.abs(statistics.getEventStatisticByName("now") - 3. / 60) <= EPS);
    }
}
