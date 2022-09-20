package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ReversedSequenceTest {

    CharSequence text = new ReversedSequence("evgeniy");

    @Test
    void testLength() {

        var actual = text.length();
        var exception = 7;
        assertThat(actual).isEqualTo(exception);

    }

    @Test
    void testCharAt() {

        var actual = text.charAt(4);
        var exception = 'g';
        assertThat(actual).isEqualTo(exception);
    }

    @Test
    void testSubSequence() {

        var actual = text.subSequence(1, 3);
        var exception = "in";
        assertThat(actual).isEqualTo(exception);

    }
}