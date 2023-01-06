package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] array = new int[10];
    private int size;

    public SafetyList() {
        this.size = 0;
    }

    public synchronized void add(int num) {
        if (size == array.length) {
            int[] oldArray = this.array;
            array = new int[size * 2];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }
        array[size++] = num;
    }

    public int get(int index) {
        return array[index];
    }
    public int getSize() {
        return size;
    }


    // END
}
