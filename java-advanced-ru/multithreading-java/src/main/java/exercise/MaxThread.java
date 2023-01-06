package exercise;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

// BEGIN
public class MaxThread extends Thread {

    private int[] array;
    private int max;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        IntStream intStream = Arrays.stream(array);
        OptionalInt optionalInt = intStream.max();
        this.max = optionalInt.getAsInt();
    }

    public int getMax() {
        return this.max;
    }
}
// END
