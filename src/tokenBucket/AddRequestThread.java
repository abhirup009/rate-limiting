package tokenBucket;

import commons.RandomString;

class AddRequestThread extends Thread {
    TokenBucket tokenBucket;
    int requestsPerSecond;
    private final int sleepDuration;

    AddRequestThread(TokenBucket tokenBucket, int requestsPerSecond) {
        this.tokenBucket = tokenBucket;
        this.requestsPerSecond = requestsPerSecond;
        this.sleepDuration = 1000/requestsPerSecond;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepDuration);
            } catch (Exception e) {
                // Silently ignore exception
            }

            String requestId = new RandomString().nextString();

            try {
                tokenBucket.acquireToken(requestId);
            } catch (Exception e) {
            }
        }
    }
}
