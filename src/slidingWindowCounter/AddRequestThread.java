package slidingWindowCounter;

import commons.RandomString;

class AddRequestThread extends Thread {
    SlidingWindow slidingWindow;
    int requestsPerSecond;
    private final int sleepDuration;

    AddRequestThread(SlidingWindow slidingWindow, int requestsPerSecond) {
        this.slidingWindow = slidingWindow;
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

            slidingWindow.isAllowed(requestId);
        }
    }
}
