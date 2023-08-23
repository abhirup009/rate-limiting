package tokenBucket;

class TokenBucket {
    public static void main(String args[]) {
        Bucket bucket = new Bucket(Constants.MAX_BUCKET_SIZE, Constants.TOKEN_FILL_RATE_PER_SECOND);

        try {
            // This is present to provide some time for the token bucket to be filled up.
            Thread.sleep(Constants.INITIAL_DELAY_MS_FOR_TOKEN_BUCKET_FILL);
        } catch (Exception e) {
        }

        Thread requestThread = new AddRequestThread(bucket, Constants.REQUESTS_PER_SECOND);

        try {
            requestThread.start();
        } catch (Exception e) {
        }
    }
}