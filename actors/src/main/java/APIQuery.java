import java.util.List;

/**
 * Created by dmitry on 25.03.17.
 */
public interface APIQuery {
    List<Result> search();
    String getAPIName();
}
