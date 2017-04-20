import java.time.Instant;

public class SetableClock implements Clock {
    private Instant clock;

    public SetableClock(Instant clock) {
        this.clock = clock;
    }

    public void set(Instant clock) {
        this.clock = clock;
    }

    @Override
    public long millis() {
        return clock.toEpochMilli();
    }
}
