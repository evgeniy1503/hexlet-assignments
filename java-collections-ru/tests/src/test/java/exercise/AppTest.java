package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import exercise.*;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        //case1
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> result1 = App.take(numbers1, 3);
        assertThat(result1).isEqualTo(expected1);

        //case2
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList());
        List<Integer> expected2 = new ArrayList<>(Arrays.asList());
        List<Integer> result2 = App.take(numbers2, 5);
        assertThat(result2).isEqualTo(expected2);

        //case3
        List<Integer> numbers3 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> result3 = App.take(numbers3, 0);
        assertThat(result3).isEmpty();
    }
    // END
}

