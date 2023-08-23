package tokenBucket;

import commons.RandomString;

import java.time.Instant;

class AddRequestThread extends Thread {
    Bucket bucket;
    int requestsPerSecond;
    private final int sleepDuration;

    AddRequestThread(Bucket bucket, int requestsPerSecond) {
        this.bucket = bucket;
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
            Instant currentTime = Instant.now();

            String requestId = new RandomString().nextString();

            try {
                bucket.acquireToken(requestId);
            } catch (Exception e) {
            }
        }
    }
}
