package exercise;

// BEGIN
public class ListThread extends Thread {

    private SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 1000) {
            try {
                sleep(1);
                safetyList.add(i);
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
// END
