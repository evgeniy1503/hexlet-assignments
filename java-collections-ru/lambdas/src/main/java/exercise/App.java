package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String [][] image) {
        return Arrays.stream(image)
                .flatMap(element -> Stream.of(element,element))
                .map(element -> Arrays.stream(element)
                        .flatMap(e -> Stream.of(e,e))
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
// END
