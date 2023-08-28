package slidingWindowLog;

import commons.Constants;

public class SlidingWindowLogTest {
    public static void main(String args[]) {
        SlidingWindowLog slidingWindowLog = new SlidingWindowLog(
                Constants.SlidingWindowLog.MAXIMUM_ALLOWED_REQUESTS_PER_SECOND
        );

        Thread requestThread = new AddRequestThread(slidingWindowLog, Constants.SlidingWindowLog.REQUESTS_PER_SECOND);

        try {
            requestThread.start();
        } catch (Exception e) {
        }
    }
}
