import java.util.Arrays;

class ActionCounter {

    private static final int TIME_BEFORE = 300;
    private final int[] times;
    private int count;
    private int lastTime;
    private int pointer;

    public ActionCounter() {
        this.count = 0;
        this.lastTime = 0;
        this.pointer = 0;
        times = new int[TIME_BEFORE];
    }

    public void call(int timestamp) {
        update(timestamp);
        count++;
        times[pointer]++;
    }

    public int getActions(int timestamp) {
        update(timestamp);
        return count;
    }

    private void update(int timestamp) {
        if (lastTime == -1) {
            lastTime = timestamp;
            return;
        }

        int difference = timestamp - lastTime;
        lastTime = timestamp;
        if (difference > TIME_BEFORE) {
            Arrays.fill(times, 0);
            pointer = 0;
            count = 0;
            return;
        }

        for (int i = 0; i < difference; i++) {
            int next = (pointer + 1) % TIME_BEFORE;
            count -= times[next];
            times[next] = 0;
            pointer = next;
        }

    }
}