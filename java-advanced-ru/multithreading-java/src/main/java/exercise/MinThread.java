package exercise;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

// BEGIN
public class MinThread extends Thread {

    private int[] array;
    private int min;

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        IntStream intStream = Arrays.stream(array);
        OptionalInt optionalInt = intStream.min();
        this.min = optionalInt.getAsInt();
    }

    public int getMin() {
        return this.min;
    }
}
// END
