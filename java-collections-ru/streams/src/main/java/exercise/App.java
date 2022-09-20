package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    static List<String> freeDomins = Arrays.asList("hotmail.com", "gmail.com", "yandex.ru");
    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .map(email -> email.split("@")[1])
                .filter(email -> freeDomins.contains(email))
                .count();

    }
}
// END
