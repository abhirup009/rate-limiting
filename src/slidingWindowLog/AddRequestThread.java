package slidingWindowLog;

import commons.RandomString;

class AddRequestThread extends Thread {
    SlidingWindowLog slidingWindowLog;
    int requestsPerSecond;
    private final int sleepDuration;

    AddRequestThread(SlidingWindowLog slidingWindowLog, int requestsPerSecond) {
        this.slidingWindowLog = slidingWindowLog;
        this.requestsPerSecond = requestsPerSecond;
        this.sleepDuration = 1000 / requestsPerSecond;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepDuration);
            } catch (Exception e) {
                // Silently ignore exception
            }

            String requestId = new RandomString().nextString();

            slidingWindowLog.isAllowed(requestId);
        }
    }
}
