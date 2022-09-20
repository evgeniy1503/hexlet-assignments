package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        String gender = "male";

        List<String> oldestMans = users.stream()
                .filter(user -> user.containsValue(gender))
                .sorted(Comparator.comparing(user -> user.get("birthday")))
                .map(user -> user.get("name"))
                .toList();


        return oldestMans;
    }
}
// END
