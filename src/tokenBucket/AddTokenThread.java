package tokenBucket;

class AddTokenThread extends Thread {
    Bucket bucket;
    int bucketFillRatePerSecond;
    private final int sleepDuration;

    AddTokenThread(Bucket bucket, int bucketFillRatePerSecond) {
        this.bucket = bucket;
        this.bucketFillRatePerSecond = bucketFillRatePerSecond;
        this.sleepDuration = 1000/bucketFillRatePerSecond;
    }

    public void run() {
        while (true) {
            bucket.addToken();

            try {
                Thread.sleep(sleepDuration);
            } catch (Exception e) {
            }
        }
    }
}
