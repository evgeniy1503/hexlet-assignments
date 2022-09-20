package exercise;

import java.util.Map;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static KeyValueStorage swapKeyValue(KeyValueStorage storage) {

        Map<String, String> data = storage.toMap();

        for (Map.Entry<String, String> keyValue : data.entrySet()) {
            String newKey = keyValue.getValue();
            String newValue = keyValue.getKey();
            storage.set(newKey, newValue);
            storage.unset(newValue);
        }

        return storage;
    }
}
// END
