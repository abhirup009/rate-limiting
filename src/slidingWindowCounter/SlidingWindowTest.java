package slidingWindowCounter;

import commons.Constants;

public class SlidingWindowTest {
    public static void main(String args[]) {
        SlidingWindow slidingWindow = new SlidingWindow(4);

        Thread requestThread = new AddRequestThread(slidingWindow, Constants.SlidingWindowCounter.REQUESTS_PER_SECOND);

        try {
            requestThread.start();
        } catch (Exception e) {
        }
    }
}
