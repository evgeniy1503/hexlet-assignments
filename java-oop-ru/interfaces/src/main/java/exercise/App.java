package exercise;

// BEGIN
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static List<String> buildAppartmentsList(List<Home> appartments, int n) {

        List<String> result = new ArrayList<>();
        if (appartments.size() == 0) {
            return result;
        }

        return appartments.stream().sorted(Home::compareTo).map(Home::toString).limit(n).collect(Collectors.toList());

    }
}
// END
