package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    public static final String added = "added";
    public static final String deleted = "deleted";
    public static final String changed = "changed";
    public static final String unchanged = "unchanged";

    public static <T> LinkedHashMap<String, String> genDiff(Map<String, T> data1, Map<String, T> data2) {

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            if (!data1.containsKey(key)) {
                result.put(key, added);
            } else if (!data2.containsKey(key)) {
                result.put(key, deleted);
            } else if (data1.get(key) != data2.get(key)) {
                result.put(key, changed);
            } else if (data1.get(key) == data2.get(key)) {
                result.put(key, unchanged);
            }
        }
        return result;
    }

}
//END
