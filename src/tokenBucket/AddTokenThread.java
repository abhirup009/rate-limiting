package tokenBucket;

class AddTokenThread extends Thread {
    TokenBucket tokenBucket;
    int tokenFillRatePerSecond;
    private final int sleepDuration;

    AddTokenThread(TokenBucket tokenBucket, int tokenFillRatePerSecond) {
        this.tokenBucket = tokenBucket;
        this.tokenFillRatePerSecond = tokenFillRatePerSecond;
        this.sleepDuration = 1000 / tokenFillRatePerSecond;
    }

    public void run() {
        while (true) {
            tokenBucket.addToken();

            try {
                Thread.sleep(sleepDuration);
            } catch (Exception e) {
            }
        }
    }
}
