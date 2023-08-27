package slidingWindowCounter;

import commons.Constants;

public class SlidingWindowCounterTest {
    public static void main(String args[]) {
        SlidingWindowCounter slidingWindowCounter = new SlidingWindowCounter(
                Constants.SlidingWindowCounter.MAXIMUM_ALLOWED_REQUESTS_PER_SECOND
        );

        Thread requestThread = new AddRequestThread(slidingWindowCounter, Constants.SlidingWindowCounter.REQUESTS_PER_SECOND);

        try {
            requestThread.start();
        } catch (Exception e) {
        }
    }
}
