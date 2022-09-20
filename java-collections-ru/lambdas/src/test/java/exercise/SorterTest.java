package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import java.util.List;

// BEGIN
class SorterTest {
    @Test
    void testTakeOldestMans1() {
        List<Map<String, String>> users1 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        List<String> expect1 = List.of("John Smith", "Andrey Petrov", "Vladimir Nikolaev");
        List<String> result1 = Sorter.takeOldestMans(users1);
        assertThat(expect1).isEqualTo(result1);
    }
    @Test
    void testTakeOldestMans2() {
        List<Map<String, String>> users2 = List.of(
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        List<String> result2 = Sorter.takeOldestMans(users2);
        assertThat(result2).isEmpty();
    }

    @Test
    void testTakeOldestMans3() {
        List<Map<String, String>> users3 = List.of(
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male")
        );
        List<String> expect3 = List.of("John Smith");
        List<String> result3 = Sorter.takeOldestMans(users3);
        assertThat(expect3).isEqualTo(result3);
    }
}
// END


