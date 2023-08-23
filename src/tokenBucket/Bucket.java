package tokenBucket;

import java.time.Instant;

class Bucket {
    int tokens;
    int maximumSize;
    int tokenFillRatePerSecond;

    Bucket(int maximumSize, int tokenFillRatePerSecond) {
        tokens = 0;
        this.maximumSize = maximumSize;
        this.tokenFillRatePerSecond = tokenFillRatePerSecond;

        Thread addTokenThread = new AddTokenThread(this, this.tokenFillRatePerSecond);
        addTokenThread.start();
    }

    synchronized void addToken() {
        Instant currentTime = Instant.now();

        if (tokens >= maximumSize) {
            System.out.println(currentTime + "\tBucket overflow. Total Tokens:" + tokens);
            return;
        }
        tokens++;
        System.out.println(currentTime + "\tAdded a token. Total Tokens:" + tokens);
    }

    synchronized void acquireToken(String requestId) {
        Instant currentTime = Instant.now();

        if (tokens < 1) {
            System.out.println(currentTime + "\tDiscarding requestId: " + requestId + "\tAvailable tokens: " + tokens);
            throw new IllegalArgumentException("Too many requests...");
        } else {
            tokens--;
            System.out.println(currentTime + "\tForwarding requestId: " + requestId + "\tAvailable tokens: " + tokens);
        }
    }
}
