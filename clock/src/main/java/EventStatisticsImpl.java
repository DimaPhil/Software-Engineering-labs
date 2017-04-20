import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class EventStatisticsImpl implements EventStatistics {
    private static final int HOUR_MILLIS = 60 * 60 * 1000;

    private Map<String, Queue<Long>> events;
    private Clock clock;

    public EventStatisticsImpl() {
        this.events = new HashMap<>();
        this.clock = new SetableClock(Instant.now());
    }

    public EventStatisticsImpl(Clock clock) {
        this.events = new HashMap<>();
        this.clock = clock;
    }

    public void setClock() {
        this.clock = clock;
    }

    @Override
    public void incEvent(String name) {
        if (!events.containsKey(name)) {
            events.put(name, new LinkedList<>());
        }
        events.get(name).add(clock.millis());
    }

    @Override
    public double getEventStatisticByName(String name) {
        if (!events.containsKey(name)) {
            return 0.0;
        }
        Queue<Long> queue = events.get(name);
        long currentTime = clock.millis();
        while (!queue.isEmpty() && currentTime - queue.peek() > HOUR_MILLIS) {
            queue.poll();
        }
        return queue.size() / 60.0;
    }

    @Override
    public List<Pair<String, Double>> getAllEventStatistic() {
        return events.keySet().stream()
                     .map(name -> new Pair<>(name, getEventStatisticByName(name)))
                     .collect(Collectors.toList());
    }

    @Override
    public void printStatistic() {
        List<Pair<String, Double>> result = getAllEventStatistic();
        System.out.println("Events statistic:");
        for (Pair<String, Double> entry : result) {
            System.out.println(String.format("Got %.3f rpm for event %s", entry.getSecond(), entry.getFirst()));
        }
        System.out.println();
    }
}
