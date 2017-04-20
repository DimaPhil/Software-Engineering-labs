import java.util.List;

interface EventStatistics {
    void incEvent(String name);
    double getEventStatisticByName(String name);
    List<Pair<String, Double>> getAllEventStatistic();
    void printStatistic();
}