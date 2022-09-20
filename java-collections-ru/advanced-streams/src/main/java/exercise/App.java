package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {

        String prefix = "X_FORWARDED_";
        String command = "environment=";

        List<String> contentList = List.of(content.split("\n"));

        String result = contentList.stream()
                .filter(x -> x.startsWith(command))
                .map(x -> x.replaceAll(command, ""))
                .flatMap(x -> Stream.of(x.split(",")))
                .filter(x -> x.contains(prefix))
                .map(x -> x.replaceAll("\"", ""))
                .map(x -> x.replaceAll(prefix, ""))
                .collect(Collectors.joining(","));
        return result;

    }
}
//END
