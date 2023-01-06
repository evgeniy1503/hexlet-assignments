package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        MaxThread threadMax = new MaxThread(array);
        MinThread threadMin = new MinThread(array);
        threadMax.start();
        threadMin.start();
        LOGGER.info("Thread " + threadMax.getName() + " started");
        LOGGER.info("Thread " + threadMin.getName() + " started");
        try {
            threadMax.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("max", threadMax.getMax());
        result.put("min", threadMin.getMin());
        LOGGER.info("Thread " + threadMax.getName() + " finished");
        LOGGER.info("Thread " + threadMin.getName() + " finished");
        return result;
    }
    // END
}
