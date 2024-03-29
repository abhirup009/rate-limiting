package slidingWindowCounter;

import commons.RandomString;

class AddRequestThread extends Thread {
    SlidingWindowCounter slidingWindowCounter;
    int requestsPerSecond;
    private final int sleepDuration;

    AddRequestThread(SlidingWindowCounter slidingWindowCounter, int requestsPerSecond) {
        this.slidingWindowCounter = slidingWindowCounter;
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

            slidingWindowCounter.isAllowed(requestId);
        }
    }
}
